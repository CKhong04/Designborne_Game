package game.actors.npcs.traders.pricings;

import java.util.Random;

public class IncreasedPricing extends Pricing {
    private int chanceToIncrease;
    private int percentage;
    public IncreasedPricing(int chanceToIncrease, int percentage){
        this.chanceToIncrease = chanceToIncrease;
        this.percentage = percentage;
    }

    @Override
    public int getPrice(int originalPrice){
        Random rand = new Random();
        if (rand.nextInt(100) <= this.chanceToIncrease) {
            return originalPrice * (1+this.percentage);
        } else {
            return originalPrice;
        }
    }
}
