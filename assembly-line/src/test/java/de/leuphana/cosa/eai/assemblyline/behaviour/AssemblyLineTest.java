package de.leuphana.cosa.eai.assemblyline.behaviour;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AssemblyLineTest {

    static AssemblyLine assemblyLine;

    @BeforeAll
    static void setUp() {
        assemblyLine = new AssemblyLine();
    }

    @Test
    protected void arePipesCreated() {
        Assertions.assertNotNull(assemblyLine.bookingDetailToDocumentableAdapter);
        Assertions.assertNotNull(assemblyLine.documentToPrintableAdapter);
        Assertions.assertNotNull(assemblyLine.routeToPricableAdapter);
        Assertions.assertNotNull(assemblyLine.printReportToSendableAdapter);
    }

    @Test
    protected void areFiltersCreated() {
        Assertions.assertNotNull(assemblyLine.routeService);
        Assertions.assertNotNull(assemblyLine.pricingService);
        Assertions.assertNotNull(assemblyLine.documentService);
        Assertions.assertNotNull(assemblyLine.printingService);
        Assertions.assertNotNull(assemblyLine.messagingService);
    }
}
