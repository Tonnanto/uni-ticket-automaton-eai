package de.leuphana.cosa.eai.assemblyline.behaviour.adapter;

import de.leuphana.cosa.eai.documentsystem.behaviour.service.DocumentService;
import de.leuphana.cosa.eai.pricingsystem.structure.Price;
import de.leuphana.cosa.eai.routesystem.structure.Route;

public class BookingDetailToDocumentableAdapter {

    DocumentService documentService;
    private Price price;
    private Route route;

    public BookingDetailToDocumentableAdapter(DocumentService documentService) {
        this.documentService = documentService;
    }

//    public void onRouteCreated(Event event) {
//        if (event.getProperty(RouteService.ROUTE_KEY) instanceof Route route) {
//            this.route = route;
//
//            // Call document service
//            callDocumentService();
//        }
//    }
//
//    public void onPriceDetermined(Event event) {
//        if (event.getProperty(PricingService.PRICE_KEY) instanceof Price price) {
//            this.price = price;
//
//            // Call document service
//            callDocumentService();
//        }
//    }
//
//    private void callDocumentService() {
//        if (route == null || price == null) return;
//
//        Locale locale = new Locale("de", "DE");
//        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL, locale);
//
//        String name = "Ticket " + UUID.randomUUID();
//        String header = dateFormat.format(new Date());
//        String body = route.getStartLocation().getName() + " -> " + route.getEndLocation().getName() + " (" + (int) route.getDistance() + "km)\n" +
//                String.format("%.2f€ (%s)", price.calculatePrice(), price.getPriceRate().toString());
//        String footer = "Wir wünschen Ihnen eine\n" +
//                "schöne Reise!";
//
//        Documentable documentable = new Documentable(name, header, body, footer);
//
//        System.out.println(documentable);
//
//        documentService.createDocument(documentable);
//    }
}
