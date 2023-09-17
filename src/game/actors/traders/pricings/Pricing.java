package game.actors.traders.pricings;

/**
 * Interface for all the pricing strategies.
 * Created by:
 * @author Laura Zhakupova
 */
public interface Pricing {
    /**
     * Calculates the new price based on the original price and the pricing strategy.
     *
     * @param originalPrice original price.
     * @return a new price calculated using the pricing strategy.
     */
    int getPrice(int originalPrice);
}
