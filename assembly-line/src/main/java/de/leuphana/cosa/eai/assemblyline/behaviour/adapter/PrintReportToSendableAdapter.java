package de.leuphana.cosa.eai.assemblyline.behaviour.adapter;

import de.leuphana.cosa.eai.documentsystem.structure.TicketDocumentTemplate;
import de.leuphana.cosa.eai.messagingsystem.behaviour.service.MessagingService;

public class PrintReportToSendableAdapter {
    MessagingService messagingService;
    private TicketDocumentTemplate ticketDocument;

    public PrintReportToSendableAdapter(MessagingService messagingService) {
        this.messagingService = messagingService;
    }

//    public  void onDocumentCreated(Event event) {
//        if(event.getProperty(DocumentService.DOCUMENT_KEY) instanceof TicketDocumentTemplate ticketDocument) {
//            this.ticketDocument = ticketDocument;
//        }
//    }
//
//    public void onPrintReportCreated(Event event) {
//        // convert to sendable
//        Sendable sendable = new Sendable();
//        if(event.getProperty(PrintingService.PRINT_KEY) instanceof PrintReport printReport) {
//            sendable.setContent(ticketDocument.getDocument());
//        }
//        // hand sendable to messagingService for logging
//        messagingService.sendMessage(sendable);
//    }
}
