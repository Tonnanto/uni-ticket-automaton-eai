package de.leuphana.cosa.eai.assemblyline.behaviour.adapter;

import de.leuphana.cosa.eai.documentsystem.structure.TicketDocumentTemplate;
import de.leuphana.cosa.eai.messagingsystem.behaviour.service.MessagingService;
import de.leuphana.cosa.eai.messagingsystem.structure.Sendable;

public class PrintReportToSendableAdapter {
    final MessagingService messagingService;
    private TicketDocumentTemplate ticketDocument;

    public PrintReportToSendableAdapter(MessagingService messagingService) {
        this.messagingService = messagingService;
    }

    public void onDocumentCreated(TicketDocumentTemplate document) {
        this.ticketDocument = document;
    }

    public Sendable onDocumentPrinted() {
        if (this.ticketDocument == null) return null;

        Sendable sendable = new Sendable();
        sendable.setContent(ticketDocument.getDocument());

        return sendable;
    }
}
