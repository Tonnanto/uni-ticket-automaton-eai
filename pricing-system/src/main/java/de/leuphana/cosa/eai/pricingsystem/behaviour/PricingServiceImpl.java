package de.leuphana.cosa.eai.pricingsystem.behaviour;

import de.leuphana.cosa.eai.pricingsystem.behaviour.service.PricingService;
import de.leuphana.cosa.eai.pricingsystem.structure.Pricable;
import de.leuphana.cosa.eai.pricingsystem.structure.Price;
import de.leuphana.cosa.eai.pricingsystem.structure.PriceRate;
import de.leuphana.cosa.eai.uisystem.structure.SelectionView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PricingServiceImpl implements PricingService {

    /**
     * Use Case: Select Price Rate
     * Creates a price object based on the price rate selected by the user.
     */
    public Price selectPriceRate(Pricable pricable) {

        // Let user select price rate
        PriceRate priceRate = selectPriceRate(Arrays.stream(PriceRate.values()).toList(), pricable);

        // Calculate price
        return new Price(pricable, priceRate);
    }

    /**
     * Prompts the user to select a price rate
     *
     * @param priceRates the price rates the user can choose from
     * @return the selected price rate
     */
    private PriceRate selectPriceRate(List<PriceRate> priceRates, Pricable pricable) {
        SelectionView view = new SelectionView() {

            @Override
            protected String getMessage() {
                return "Bitte wählen Sie Ihren Tarif aus.";
            }

            @Override
            protected List<String> getOptions() {
                return priceRates.stream().map((priceRate) -> {
                    Price price = new Price(pricable, priceRate);
                    return String.format("%s: (%.2f€)", priceRate, price.calculatePrice());
                }).collect(Collectors.toList());
            }
        };

        int selectedIndex = view.displaySelection();
        return priceRates.get(selectedIndex);
    }
}
