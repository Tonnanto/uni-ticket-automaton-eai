package de.leuphana.cosa.eai.printingsystem.behaviour.service.event;

public interface PrintingEventService {
    void addPrintingEventListener(PrintingEventListener printingEventListener);

    void removePrintingEventListener(PrintingEventListener printingEventListener);
}
