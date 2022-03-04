package de.leuphana.cosa.eai.assemblyline.behaviour;


import de.leuphana.cosa.eai.assemblyline.behaviour.adapter.BookingDetailToDocumentableAdapter;
import de.leuphana.cosa.eai.assemblyline.behaviour.adapter.DocumentToPrintableAdapter;
import de.leuphana.cosa.eai.assemblyline.behaviour.adapter.PrintReportToSendableAdapter;
import de.leuphana.cosa.eai.assemblyline.behaviour.adapter.RouteToPricableAdapter;
import de.leuphana.cosa.eai.documentsystem.behaviour.DocumentServiceImpl;
import de.leuphana.cosa.eai.documentsystem.behaviour.service.DocumentService;
import de.leuphana.cosa.eai.messagingsystem.behaviour.MessagingServiceImpl;
import de.leuphana.cosa.eai.messagingsystem.behaviour.service.MessagingService;
import de.leuphana.cosa.eai.pricingsystem.behaviour.PricingServiceImpl;
import de.leuphana.cosa.eai.pricingsystem.behaviour.service.PricingService;
import de.leuphana.cosa.eai.printingsystem.behaviour.PrintingServiceImpl;
import de.leuphana.cosa.eai.printingsystem.behaviour.service.PrintingService;
import de.leuphana.cosa.eai.routesystem.behaviour.RouteServiceImpl;
import de.leuphana.cosa.eai.routesystem.behaviour.service.RouteService;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class AssemblyLine {

    // Context
    private CamelContext context;

    // Filter
    private RouteService routeService;
    private PricingService pricingService;
    private DocumentService documentService;
    private PrintingService printingService;
    private MessagingService messagingService;

    // Pipes
    private RouteToPricableAdapter routeToPricableAdapter;
    private BookingDetailToDocumentableAdapter bookingDetailToDocumentableAdapter;
    private DocumentToPrintableAdapter documentToPrintableAdapter;
    private PrintReportToSendableAdapter printReportToSendableAdapter;

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
                        .log("Selected Route: " + body().toString())
                        .to("direct:aggregateBookingDetails")
                        .to("direct:getPrice")
                        .to("direct:aggregateBookingDetails")
//                        .log("Documentable: " + body().toString())
//                        .to("direct:createDocument")
//                        .multicast().parallelProcessing()
//                            .to("direct:getPrice", "direct:aggregateBookingDetails")
//                            .end()
                        ;

                from("direct:aggregateBookingDetails")
                        .aggregate(constant(true), bookingDetailToDocumentableAdapter).completionSize(2)
                        .to("direct:createDocument")
                        .log("Documentable: " + body().toString())
                        ;

                from("direct:getPrice")
                        .bean(routeToPricableAdapter, "onRouteCreated")
                        .bean(pricingService, "selectPriceRate")
                        .log("Selected Price: " + body().toString())
//                        .to("direct:aggregateBookingDetails")
                        ;

                from("direct:createDocument")
                            .pipeline()
                                .bean(documentService, "createDocument") // TODO handle unsuccessful confirmation
                                .bean(documentToPrintableAdapter, "onDocumentCreated")
                                .bean(printingService, "printPrintable")
                                .log("Print report: " + body().toString())
                                .end()
//                            .bean(printReportToSendableAdapter, "")
//                            .bean(messagingService, "sendMessage")
                        ;

            }
        };

        try {
            context.addRoutes(builder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
