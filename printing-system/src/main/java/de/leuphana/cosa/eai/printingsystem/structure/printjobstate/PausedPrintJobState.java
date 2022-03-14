package de.leuphana.cosa.eai.printingsystem.structure.printjobstate;

import de.leuphana.cosa.eai.printingsystem.structure.PrintJob;

public class PausedPrintJobState extends PrintJobState {

    public PausedPrintJobState(PrintJob printJob) {
        super(printJob);
    }

    @Override
    public PrintJobState changePrintJobState(PrintAction printAction) {
        // TODO Auto-generated method stub
        return null;
    }

}
