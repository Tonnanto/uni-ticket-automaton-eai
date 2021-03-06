package de.leuphana.cosa.eai.printingsystem.structure.printjobstate;

import de.leuphana.cosa.eai.printingsystem.structure.PrintJob;

public class QueuedPrintJobState extends PrintJobState {

//	private final Logger logger;

    public QueuedPrintJobState(PrintJob printJob) {
        super(printJob);
//		logger = LogManager.getLogger(this.getClass());
//		logger.info("Print job with document name " + printJob.getPrintable().getTitle() + " queued!");
    }

    @Override
    public PrintJobState changePrintJobState(PrintAction printAction) {
        switch (printAction) {
            case PRINT: {
                return new PrintedPrintJobState(printJob);
            }
            case PAUSE: {
                return new PausedPrintJobState(printJob);
            }
            case CANCEL: {
                return new CanceledPrintJobState(printJob);
            }
            default:
                throw new IllegalArgumentException("Unexpected value: " + printAction);
        }
    }

}