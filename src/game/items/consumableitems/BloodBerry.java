package game.items.consumableitems;

import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.actors.traders.pricings.Pricing;
import game.actors.traders.pricings.RegularPricing;

public class BloodBerry extends ConsumableItem {
    //Private attributes
    //Consumable attributes
    private static final int INCREASE_HEALTH_VALUE = 5;
    private static final boolean IS_DISCOUNT = false;

    //Buyable attributes
    private static final int BUY_PRICE = 0;
    private static final Pricing BUY_PRICING = new RegularPricing();
    private static final int BUY_SCAM_CHANCE = 0;

    //Sellable attributes
    private static final int SELL_PRICE = 10;
    private static final Pricing SELL_PRICING = new RegularPricing();
    private static final int SELL_SCAM_CHANCE = 0;
    /***
     * Constructor.
     */
    public BloodBerry() {
        super("BloodBerry", '*', ActorAttributeOperations.INCREASE,BaseActorAttributes.HEALTH, INCREASE_HEALTH_VALUE, IS_DISCOUNT, BUY_PRICE, BUY_PRICING, BUY_SCAM_CHANCE, SELL_PRICE, SELL_PRICING, SELL_SCAM_CHANCE);
    }
}
