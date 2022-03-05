package de.leuphana.cosa.eai.pricingsystem.behaviour.service;

import de.leuphana.cosa.eai.pricingsystem.structure.Pricable;
import de.leuphana.cosa.eai.pricingsystem.structure.Price;

public interface PricingService {
    String PRICE_DETERMINED_TOPIC = "priceservice/price/determined";
    String PRICE_KEY = "price";

    Price selectPriceRate(Pricable pricable);
}
