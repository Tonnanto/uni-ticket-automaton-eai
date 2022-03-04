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

//    @Override
//    public void start(BundleContext bundleContext) {
//        System.out.println("Registering PricingService.");
//        registration = bundleContext.registerService(
//                PricingService.class,
//                this,
//                new Hashtable<String, String>());
//        reference = registration
//                .getReference();
//
//        eventAdminTracker = new ServiceTracker(bundleContext, EventAdmin.class.getName(), null);
//        eventAdminTracker.open();
//    }
//
//    @Override
//    public void stop(BundleContext bundleContext) {
//        System.out.println("Unregistering PricingService.");
//        registration.unregister();
//    }

    /**
     * Use Case: Select Price Rate
     * Creates a price object based on the price rate selected by the user.
     * Triggers an event with the "PRICE_DETERMINED_TOPIC" topic once the price is determined.
     */
    public void selectPriceRate(Pricable pricable) {

        // Let user select price rate
        PriceRate priceRate = selectPriceRate(Arrays.stream(PriceRate.values()).toList(), pricable);

        // Calculate price
        Price price = new Price(pricable, priceRate);

        // TODO: trigger event (PRICE_DETERMINED_TOPIC)
//        EventAdmin eventAdmin = (EventAdmin) eventAdminTracker.getService();
//
//        if (eventAdmin != null) {
//            Dictionary<String, Object> content = new Hashtable<>();
//            content.put(PRICE_KEY, price);
//            eventAdmin.sendEvent(new Event(PRICE_DETERMINED_TOPIC, content));
//        } else {
//            System.out.println("EventAdmin not found: Event could not be triggered: " + PRICE_DETERMINED_TOPIC);
//        }
    }

    /**
     * Prompts the user to select a price rate
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
