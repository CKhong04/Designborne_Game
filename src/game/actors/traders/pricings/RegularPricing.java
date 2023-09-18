package game.actors.traders.pricings;

/**
 * Class representing the regular pricing strategy.
 * Created by:
 * @author Laura Zhakupova
 */
public class RegularPricing implements Pricing {
    /**
     * New price is the same as the original price.
     *
     * @param originalPrice original price.
     * @return the original price.
     */
    @Override
    public int getPrice(int originalPrice){
        return originalPrice;
    }
}