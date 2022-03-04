package de.leuphana.cosa.eai.assemblyline.behaviour.adapter;

import de.leuphana.cosa.eai.pricingsystem.behaviour.service.PricingService;

public class RouteToPricableAdapter {
    PricingService pricingService;

    public RouteToPricableAdapter(PricingService pricingService) {
        this.pricingService = pricingService;
    }

//    public void onRouteCreated(Event event) {
//        if (event.getProperty(RouteService.ROUTE_KEY) instanceof Route route) {
//
//            // Map Route to Pricable
//            Pricable pricable = new Pricable() {
//                @Override
//                public double getAmount() {
//                    return route.getDistance();
//                }
//
//                @Override
//                public String getName() {
//                    return route.toString();
//                }
//            };
//
//            // Call pricing service
//            pricingService.selectPriceRate(pricable);
//        }
//    }
}
