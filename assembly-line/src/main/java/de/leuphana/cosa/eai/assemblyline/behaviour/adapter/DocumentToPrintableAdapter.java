package de.leuphana.cosa.eai.assemblyline.behaviour.adapter;

import de.leuphana.cosa.eai.documentsystem.structure.TicketDocumentTemplate;
import de.leuphana.cosa.eai.printingsystem.behaviour.service.PrintingService;
import de.leuphana.cosa.eai.printingsystem.structure.Printable;

import java.util.List;

public class DocumentToPrintableAdapter {
    PrintingService printingService;

    public DocumentToPrintableAdapter(PrintingService printingService) {
        this.printingService = printingService;
    }

    public Printable onDocumentCreated(TicketDocumentTemplate document) {
        // convert Document to Printable
        return new Printable() {
            @Override
            public String getTitle() {
                return document.getName();
            }

            @Override
            public List<String> getContent() {
                return document.getDocument().lines().toList();
            }
        };
    }

}
