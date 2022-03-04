package de.leuphana.cosa.eai.assemblyline.behaviour.adapter;

import de.leuphana.cosa.eai.printingsystem.behaviour.service.PrintingService;

public class DocumentToPrintableAdapter {
    PrintingService printingService;

    public DocumentToPrintableAdapter(PrintingService printingService) {
        this.printingService = printingService;
    }

//    public void onDocumentCreated(Event event) {
//        if (event.getProperty(DocumentService.DOCUMENT_KEY) instanceof TicketDocumentTemplate ticketDocument) {
//            // convert Document to Printable
//            Printable printable = new Printable() {
//                @Override
//                public String getTitle() {
//                    return ticketDocument.getName();
//                }
//
//                @Override
//                public List<String> getContent() {
//                    return ticketDocument.getDocument().lines().toList();
//                }
//            };
//
//            // hand to printingService
//            printingService.printPrintable(printable);
//        }
//    }
}
