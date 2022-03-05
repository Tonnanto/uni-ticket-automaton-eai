package de.leuphana.cosa.eai.assemblyline.behaviour.adapter;

import de.leuphana.cosa.eai.documentsystem.behaviour.service.DocumentService;
import de.leuphana.cosa.eai.documentsystem.structure.Documentable;
import de.leuphana.cosa.eai.pricingsystem.structure.Price;
import de.leuphana.cosa.eai.routesystem.structure.Route;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class BookingDetailToDocumentableAdapter {

    DocumentService documentService;
    private Price price;
    private Route route;

    public BookingDetailToDocumentableAdapter(DocumentService documentService) {
        this.documentService = documentService;
    }

//    @Override
//    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
//        System.out.println("AGGREGATE");
//
//        if (newExchange.getIn().getBody() instanceof Route route) {
//            System.out.println("Setting Route");
//            this.route = route;
//        }
//
//        if (newExchange.getIn().getBody() instanceof Price price) {
//            System.out.println("Setting Price");
//            this.price = price;
//        }
//
//        Documentable body = createDocumentable();
//        newExchange.getIn().setBody(body);
//        if (body != null) {
//            System.out.println("Documentable: " + body);
//            newExchange.setProperty(Exchange.AGGREGATION_COMPLETE_CURRENT_GROUP, true);
//        }
//        return newExchange;
//    }

    public Documentable onRouteCreated(Route route) {
        this.route = route;

        // Try to create documentable
        return createDocumentable();
    }

    public Documentable onPriceDetermined(Price price) {
        this.price = price;

        // Try to create documentable
        return createDocumentable();
    }

    private Documentable createDocumentable() {
        if (route == null || price == null) return null;

        Locale locale = new Locale("de", "DE");
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL, locale);

        String name = "Ticket " + UUID.randomUUID();
        String header = dateFormat.format(new Date());
        String body = route.getStartLocation().getName() + " -> " + route.getEndLocation().getName() + " (" + (int) route.getDistance() + "km)\n" +
                String.format("%.2f€ (%s)", price.calculatePrice(), price.getPriceRate().toString());
        String footer = "Wir wünschen Ihnen eine\n" +
                "schöne Reise!";

        return new Documentable(name, header, body, footer);
    }
}
