package game.actors.traders.pricings;

public class RegularPricing extends Pricing {
    @Override
    public int getPrice(int originalPrice){
        return originalPrice;
    }
}