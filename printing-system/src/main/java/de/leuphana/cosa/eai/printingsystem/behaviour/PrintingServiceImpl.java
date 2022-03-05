package de.leuphana.cosa.eai.printingsystem.behaviour;

import de.leuphana.cosa.eai.printingsystem.behaviour.service.PrintingService;
import de.leuphana.cosa.eai.printingsystem.structure.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class PrintingServiceImpl implements PrintingService {
    private final Set<Printer> printers;

    public PrintingServiceImpl() {
        printers = new HashSet<>();
        Printer printer = new Printer();
        printer.setColorType(ColorType.BLACK_WHITE);
        printers.add(printer);
    }

    /**
     * Use Case: Print Printable
     * Prints the given Printable and returns a PrintReport
     */
    public PrintReport printPrintable(Printable printable) {
        // Trigger printing process
        return print(printable);
    }

    /**
     * Creates a PrintJob and hands it to the selected printer
     *
     * @param printable the Printable to print
     * @return the created PrintReport
     */
    public PrintReport print(Printable printable) {

        PrintJob printJob = new PrintJob(printable);
        // Suche des richtigen Druckers (simuliert)
        Printer selectedPrinter = null;
        for (Printer printer : printers) {
            // if( ) {
            selectedPrinter = printer;
            // }
        }

        assert selectedPrinter != null;
        selectedPrinter.addPrintJob(printJob);

        PrintReport printReport;

        // Create PrintReport (timestamp, ticketname, isPrinted)
        if (selectedPrinter.print()) {
            printReport = new PrintReport(LocalDate.now().toString(), printable.getTitle(), true);
        } else {
            printReport = new PrintReport(LocalDate.now().toString(), printable.getTitle(), false);
        }

        return printReport;
    }
}