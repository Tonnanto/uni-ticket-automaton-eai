package de.leuphana.cosa.eai.assemblyline.behaviour;


import de.leuphana.cosa.eai.assemblyline.behaviour.adapter.BookingDetailToDocumentableAdapter;
import de.leuphana.cosa.eai.assemblyline.behaviour.adapter.DocumentToPrintableAdapter;
import de.leuphana.cosa.eai.assemblyline.behaviour.adapter.PrintReportToSendableAdapter;
import de.leuphana.cosa.eai.assemblyline.behaviour.adapter.RouteToPricableAdapter;
import de.leuphana.cosa.eai.documentsystem.behaviour.service.DocumentService;
import de.leuphana.cosa.eai.messagingsystem.behaviour.service.MessagingService;
import de.leuphana.cosa.eai.pricingsystem.behaviour.service.PricingService;
import de.leuphana.cosa.eai.printingsystem.behaviour.service.PrintingService;
import de.leuphana.cosa.eai.routesystem.behaviour.service.RouteService;

public class AssemblyLine {

    RouteToPricableAdapter routeToPricableAdapter;
    BookingDetailToDocumentableAdapter bookingDetailToDocumentableAdapter;
    DocumentToPrintableAdapter documentToPrintableAdapter;
    PrintReportToSendableAdapter printReportToSendableAdapter;

    private RouteService routeService;
    private PricingService pricingService;
    private DocumentService documentService;
    private PrintingService printingService;
    private MessagingService messagingService;

    private void createAdapter() {
        routeToPricableAdapter = new RouteToPricableAdapter(pricingService);
        bookingDetailToDocumentableAdapter = new BookingDetailToDocumentableAdapter(documentService);
        documentToPrintableAdapter = new DocumentToPrintableAdapter(printingService);
        printReportToSendableAdapter = new PrintReportToSendableAdapter(messagingService);
    }
}
