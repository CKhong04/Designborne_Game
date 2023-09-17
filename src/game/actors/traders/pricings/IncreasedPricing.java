package game.actors.traders.pricings;

import java.util.Random;

public class IncreasedPricing extends Pricing {
    private int chanceToIncrease;
    private int percentageIncrease;
    public IncreasedPricing(int chanceToIncrease, int percentageIncrease){
        this.chanceToIncrease = chanceToIncrease;
        this.percentageIncrease = percentageIncrease;
    }

    @Override
    public int getPrice(int originalPrice){
        Random rand = new Random();
        if (rand.nextInt(100) <= this.chanceToIncrease) {
            return originalPrice * (1+(this.percentageIncrease/100));
        } else {
            return originalPrice;
        }
    }
}
