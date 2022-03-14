package de.leuphana.cosa.eai.printingsystem.structure.printjobstate;

import de.leuphana.cosa.eai.printingsystem.structure.PrintJob;

public abstract class PrintJobState {
    protected PrintJob printJob;

    public PrintJobState(PrintJob printJob) {
        this.printJob = printJob;
    }

    public abstract PrintJobState changePrintJobState(PrintAction printAction);
}