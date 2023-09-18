package game.actors.traders.pricings;

import java.util.Random;

/**
 * Class representing the increased pricing strategy.
 * Created by:
 * @author Laura Zhakupova
 */
public class IncreasedPricing implements Pricing {
    // Private attributes
    private int chanceToIncrease;
    private int percentageIncrease;

    /**
     * A constructor which accepts the chance that the price increases and the percentage by which
     * the price must be increased.
     *
     * @param chanceToIncrease chance for the price to be increased.
     * @param percentageIncrease percentage by which the price is increased.
     */
    public IncreasedPricing(int chanceToIncrease, int percentageIncrease){
        this.chanceToIncrease = chanceToIncrease;
        this.percentageIncrease = percentageIncrease;
    }

    /**
     * Calculates the new price based on the original price and the chance to be increased.
     *
     * @param originalPrice original price.
     * @return a new price calculated using the pricing strategy.
     */
    @Override
    public int getPrice(int originalPrice){
        Random rand = new Random();
        if (rand.nextInt(100) <= this.chanceToIncrease) {
            return (int)(originalPrice * (1+(this.percentageIncrease/100.0)));
        } else {
            return originalPrice;
        }
    }
}
