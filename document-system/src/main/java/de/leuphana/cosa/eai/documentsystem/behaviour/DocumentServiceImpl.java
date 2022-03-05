package de.leuphana.cosa.eai.documentsystem.behaviour;

import de.leuphana.cosa.eai.documentsystem.behaviour.service.DocumentService;
import de.leuphana.cosa.eai.documentsystem.structure.Documentable;
import de.leuphana.cosa.eai.documentsystem.structure.TicketDocumentTemplate;
import de.leuphana.cosa.eai.uisystem.structure.SelectionView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentServiceImpl implements DocumentService {

    private final Map<String, TicketDocumentTemplate> documentMap;


    public DocumentServiceImpl() {
        documentMap = new HashMap<>();
    }

    /**
     * Use Case: Create Document
     * Prompts user confirmation
     * Creates a TicketDocumentTemplate from the given documentable object
     */
    @Override
    public TicketDocumentTemplate createDocument(Documentable documentable) {

        // Show overview and get user confirmation
        if (confirmTicket(documentable)) {
            // Create Ticket
            return createTicketDocument(documentable);
        }
        return null;
    }

    public TicketDocumentTemplate createTicketDocument(Documentable documentable) {
        return new TicketDocumentTemplate(documentable);
    }

    public TicketDocumentTemplate getDocument(String documentName) {
        return documentMap.get(documentName);
    }


    private boolean confirmTicket(Documentable documentable) {
        SelectionView selectionView = new SelectionView() {
            @Override
            protected List<String> getOptions() {
                List<String> option = new ArrayList<>();
                option.add("Nein");
                option.add("Ja");
                return option;
            }

            @Override
            protected String getMessage() {
                return "Sind alle Eingaben korrekt? \n \n" +
                        documentable.getHeader() + "\n" +
                        documentable.getBody() + "\n" +
                        documentable.getFooter();
            }
        };
        int selectedIndex = selectionView.displaySelection();
        return selectedIndex != 0;
    }
}