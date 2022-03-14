package de.leuphana.cosa.eai.printingsystem.behaviour.service.event;

import java.util.EventListener;

public interface PrintingEventListener extends EventListener {

    void onPrintReportCreated(PrintingEvent printingEvent);

    void onPrintReportEdited(PrintingEvent printingEvent);

    void onPrintReportRemoved(PrintingEvent printingEvent);

}
