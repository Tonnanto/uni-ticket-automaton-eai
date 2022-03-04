package de.leuphana.cosa.eai.printingsystem.structure.printjobstate;

import de.leuphana.cosa.eai.printingsystem.structure.PrintJob;

public class CreatedPrintJobState extends PrintJobState {
	
	public CreatedPrintJobState(PrintJob printJob) {
		super(printJob);
	}

	@Override
	public PrintJobState changePrintJobState(PrintAction printAction) {
		
		// TODO check precondition "printer available"
		switch (printAction) {
		case QUEUE: {
			return new QueuedPrintJobState(printJob);
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + printAction);
		}
	}

}