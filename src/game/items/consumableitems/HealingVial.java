package game.items.consumableitems;

import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.actors.traders.pricings.IncreasedPricing;
import game.actors.traders.pricings.Pricing;

/**
 * Class representing a healing vial.
 * Created by:
 * @author Laura Zhakupova
 */
public class HealingVial extends ConsumableItem{
    private static int BUY_PRICE = 100;
    private static Pricing BUY_PRICING = new IncreasedPricing(25, 50);
    private static int SELL_PRICE = 35;
    private static Pricing SELL_PRICING = new IncreasedPricing(10, 100);
    /***
     * Constructor.
     */
    public HealingVial() {
        super("the Healing vial", 'a', ActorAttributeOperations.INCREASE, BaseActorAttributes.HEALTH, 10, BUY_PRICE, BUY_PRICING, 0, SELL_PRICE,SELL_PRICING, 0);
    }
}
