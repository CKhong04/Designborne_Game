package game.actors.traders.pricings;

import java.util.Random;

public class ReducedPricing extends Pricing {
    private int chanceToIncrease;
    private int percentageDecrease;
    public ReducedPricing(int chanceToIncrease, int percentageDecrease){
        this.chanceToIncrease = chanceToIncrease;
        this.percentageDecrease = percentageDecrease;
    }

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