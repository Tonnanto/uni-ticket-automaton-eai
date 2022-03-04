package de.leuphana.cosa.eai.documentsystem.behaviour.service;

import de.leuphana.cosa.eai.documentsystem.structure.Documentable;
import de.leuphana.cosa.eai.documentsystem.structure.TicketDocumentTemplate;

public interface DocumentService {
    String DOCUMENT_CREATED_TOPIC = "documentservice/document/created";
    String DOCUMENT_KEY = "document";

    TicketDocumentTemplate createDocument(Documentable documentable);
}
