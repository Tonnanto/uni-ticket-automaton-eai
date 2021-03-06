package de.leuphana.cosa.eai.assemblyline.behaviour;


import de.leuphana.cosa.eai.assemblyline.behaviour.adapter.BookingDetailToDocumentableAdapter;
import de.leuphana.cosa.eai.assemblyline.behaviour.adapter.DocumentToPrintableAdapter;
import de.leuphana.cosa.eai.assemblyline.behaviour.adapter.PrintReportToSendableAdapter;
import de.leuphana.cosa.eai.assemblyline.behaviour.adapter.RouteToPricableAdapter;
import de.leuphana.cosa.eai.documentsystem.behaviour.DocumentServiceImpl;
import de.leuphana.cosa.eai.documentsystem.behaviour.service.DocumentService;
import de.leuphana.cosa.eai.documentsystem.structure.Documentable;
import de.leuphana.cosa.eai.messagingsystem.behaviour.MessagingServiceImpl;
import de.leuphana.cosa.eai.messagingsystem.behaviour.service.MessagingService;
import de.leuphana.cosa.eai.messagingsystem.structure.DeliveryReport;
import de.leuphana.cosa.eai.pricingsystem.behaviour.PricingServiceImpl;
import de.leuphana.cosa.eai.pricingsystem.behaviour.service.PricingService;
import de.leuphana.cosa.eai.printingsystem.behaviour.PrintingServiceImpl;
import de.leuphana.cosa.eai.printingsystem.behaviour.service.PrintingService;
import de.leuphana.cosa.eai.printingsystem.structure.PrintReport;
import de.leuphana.cosa.eai.routesystem.behaviour.RouteServiceImpl;
import de.leuphana.cosa.eai.routesystem.behaviour.service.RouteService;
import org.apache.camel.CamelContext;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AssemblyLine {

    // Context
    protected CamelContext context;

    // Filter
    protected RouteService routeService;
    protected PricingService pricingService;
    protected DocumentService documentService;
    protected PrintingService printingService;
    protected MessagingService messagingService;

    // Pipes
    protected RouteToPricableAdapter routeToPricableAdapter;
    protected BookingDetailToDocumentableAdapter bookingDetailToDocumentableAdapter;
    protected DocumentToPrintableAdapter documentToPrintableAdapter;
    protected PrintReportToSendableAdapter printReportToSendableAdapter;

    private String logLine;

    public AssemblyLine() {
        setUp();
    }

    public static void main(String[] args) {
        new AssemblyLine().start();
    }

    public void setUp() {
        context = new DefaultCamelContext();

        createFilters();
        createPipes();
        createRoutes();
    }

    public void start() {
        context.start();
        ProducerTemplate producerTemplate = context.createProducerTemplate();
        producerTemplate.sendBody("direct:start", "");
    }

    private void createFilters() {
        routeService = new RouteServiceImpl();
        pricingService = new PricingServiceImpl();
        documentService = new DocumentServiceImpl();
        printingService = new PrintingServiceImpl();
        messagingService = new MessagingServiceImpl();
    }

    private void createPipes() {
        routeToPricableAdapter = new RouteToPricableAdapter(pricingService);
        bookingDetailToDocumentableAdapter = new BookingDetailToDocumentableAdapter(documentService);
        documentToPrintableAdapter = new DocumentToPrintableAdapter(printingService);
        printReportToSendableAdapter = new PrintReportToSendableAdapter(messagingService);
    }

    private void createRoutes() {
        RouteBuilder builder = new RouteBuilder() {
            public void configure() {

                from("direct:start")
                        .bean(routeService, "selectRoute")
                        // RECIPIENT LIST
                        .multicast().parallelProcessing()
                            .bean(bookingDetailToDocumentableAdapter, "onRouteCreated") // (AGGREGATOR)
                            .to("direct:getPrice")
                        .end()
                        ;

                from("direct:getPrice")
                        // (PIPELINE)
                        .bean(routeToPricableAdapter, "onRouteCreated")
                        .bean(pricingService, "selectPriceRate")
                        .bean(bookingDetailToDocumentableAdapter, "onPriceDetermined") // (AGGREGATOR)
                        // ROUTER
                        .choice()
                            .when(body().isInstanceOf(Documentable.class))
                                .to("direct:createDocument")
                        .end()
                        ;

                from("direct:createDocument")
                        // PIPELINE
                        .pipeline()
                            .process(routeInfoLogger())
                            .bean(documentService, "createDocument")
                                // ROUTER
                                .choice()
                                .when(body().isNull()).to("direct:start")
                                .otherwise()
                                    // PIPELINE
                                    .pipeline()
                                        .bean(printReportToSendableAdapter, "onDocumentCreated")
                                        .bean(documentToPrintableAdapter, "onDocumentCreated")
                                        .bean(printingService, "printPrintable")
                                        .process(isPrintedLogger())
                                        .bean(printReportToSendableAdapter, "onDocumentPrinted")
                                        .bean(messagingService, "sendMessage")
                                        .process(isMessageSentLogger())
                                        .process(exchange -> exchange.getIn().setBody(logLine))
                                        .to("file:src/main/resources/?fileName=orders.log&fileExist=Append")
                            .end()
                        .end()
                        ;
            }
        };

        try {
            context.addRoutes(builder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Logging Processors
    private Processor routeInfoLogger() {
        return exchange -> {
            if (exchange.getIn().getBody() instanceof Documentable documentable) {
                logLine = "\n" + new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date()) +
                        " - " + documentable.getName() + "; " +
                        documentable.getBody().replace("\n", "; ");
            }
        };
    }

    private Processor isPrintedLogger() {
        return exchange -> {
            if (exchange.getIn().getBody() instanceof PrintReport printReport) {
                logLine += printReport.isPrinted() ? "; isPrinted = TRUE" : "; isPrinted = FALSE";
            }
        };
    }

    private Processor isMessageSentLogger() {
        return exchange -> {
            if (exchange.getIn().getBody() instanceof DeliveryReport deliveryReport &&
                    deliveryReport.getMessageType() != null &&
                    !deliveryReport.getMessageType().isEmpty()) {
                logLine += "; Message sent via " + deliveryReport.getMessageType();
                return;
            }
            logLine += "; No message sent";
        };
    }
}
