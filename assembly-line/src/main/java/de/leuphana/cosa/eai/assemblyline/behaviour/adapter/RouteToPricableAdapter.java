package de.leuphana.cosa.eai.assemblyline.behaviour.adapter;

import de.leuphana.cosa.eai.pricingsystem.behaviour.service.PricingService;
import de.leuphana.cosa.eai.pricingsystem.structure.Pricable;
import de.leuphana.cosa.eai.pricingsystem.structure.Price;
import de.leuphana.cosa.eai.pricingsystem.structure.PriceRate;
import de.leuphana.cosa.eai.routesystem.structure.Route;

public class RouteToPricableAdapter {
    PricingService pricingService;

    public RouteToPricableAdapter(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    public Pricable onRouteCreated(Route route) {

        // Map Route to Pricable
        Pricable pricable = new Pricable() {
            @Override
            public double getAmount() {
                return route.getDistance();
            }

            @Override
            public String getName() {
                return route.toString();
            }
        };

        // Call pricing service
//        pricingService.selectPriceRate(pricable);
        return pricable;
    }

}
