package de.leuphana.cosa.eai.pricingsystem.behaviour.service;

import de.leuphana.cosa.eai.pricingsystem.structure.Pricable;

public interface PricingService {
    String PRICE_DETERMINED_TOPIC = "priceservice/price/determined";
    String PRICE_KEY = "price";

    void selectPriceRate(Pricable pricable);
}
