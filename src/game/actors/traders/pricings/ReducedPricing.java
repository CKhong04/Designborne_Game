package game.actors.traders.pricings;

import java.util.Random;

/**
 * Class representing the reduced pricing strategy.
 * Created by:
 * @author Laura Zhakupova
 */
public class ReducedPricing implements Pricing {
    // Private attributes
    private int chanceToIncrease;
    private int percentageDecrease;

    /**
     * A constructor which accepts the chance that the price reduces and the percentage by which
     * the price must be reduced.
     *
     * @param chanceToIncrease chance for the price to be reduced.
     * @param percentageDecrease percentage by which the price is reduced.
     */
    public ReducedPricing(int chanceToIncrease, int percentageDecrease){
        this.chanceToIncrease = chanceToIncrease;
        this.percentageDecrease = percentageDecrease;
    }

    /**
     * Calculates the new price based on the original price and the chance to be reduced.
     *
     * @param originalPrice original price.
     * @return a new price calculated using the pricing strategy.
     */
    @Override
    public int getPrice(int originalPrice){
        Random rand = new Random();
        if (rand.nextInt(100) <= this.chanceToIncrease) {
            return originalPrice * (1-(this.percentageDecrease/100));
        } else {
            return originalPrice;
        }
    }
}