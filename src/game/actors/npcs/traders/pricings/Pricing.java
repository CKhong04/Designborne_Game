package game.actors.npcs.traders.pricings;

/**
 * Abstract PricingStrategy class
 * Base class for all the pricing strategies
 */
public abstract class Pricing {
    /**
     * Calculates the new price based on the original price and the specific pricing strategy
     *
     * @param originalPrice original price of the product
     * @return new price calculated using the pricing strategy
     */
    public abstract int getPrice(int originalPrice);
}
