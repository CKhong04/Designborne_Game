package game.items.consumableitems;

import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.actors.traders.pricings.Pricing;
import game.actors.traders.pricings.RegularPricing;

public class BloodBerry  extends ConsumableItem {
    private static final int BUY_PRICE = 0;
    private static final Pricing BUY_PRICING = new RegularPricing();
    private static final int SELL_PRICE = 10;
    private static final Pricing SELL_PRICING = new RegularPricing();
    private static final int SCAM_CHANCE = 0;
    /***
     * Constructor.
     */
    public BloodBerry() {
        super("BloodBerry", '*', ActorAttributeOperations.INCREASE,BaseActorAttributes.HEALTH, 5,false, BUY_PRICE, BUY_PRICING, SCAM_CHANCE, SELL_PRICE,SELL_PRICING, SCAM_CHANCE);
    }
}
