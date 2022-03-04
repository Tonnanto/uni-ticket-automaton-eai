package de.leuphana.cosa.eai.printingsystem.behaviour.service;

import de.leuphana.cosa.eai.printingsystem.structure.Printable;

public interface PrintingService {
    String PRINT_REPORT_CREATED_TOPIC = "printingservice/printreport/created";
    String PRINT_KEY = "print";
    void printPrintable(Printable printable);
}
