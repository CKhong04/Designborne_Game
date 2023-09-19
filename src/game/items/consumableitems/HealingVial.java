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
public class HealingVial extends ConsumableItem {
    //Private attributes
    //Consumable attributes
    private static final int INCREASE_HEALTH_VALUE = 10;
    private static final boolean IS_DISCOUNT = true;

    //Buyable attributes
    private static final int BUY_PRICE = 100;
    private static final int BUY_INCREASE_CHANCE = 25;
    private static final int BUY_INCREASE_PERCENTAGE = 50;
    private static final Pricing BUY_PRICING = new IncreasedPricing(BUY_INCREASE_CHANCE, BUY_INCREASE_PERCENTAGE);
    private static final int BUY_SCAM_CHANCE = 0;

    //Sellable attributes
    private static final int SELL_PRICE = 35;
    private static final int SELL_INCREASE_CHANCE = 10;
    private static final int SELL_INCREASE_PERCENTAGE = 100;
    private static final Pricing SELL_PRICING = new IncreasedPricing(SELL_INCREASE_CHANCE, SELL_INCREASE_PERCENTAGE);
    private static final int SELL_SCAM_CHANCE = 0;
    /***
     * Constructor.
     */
    public HealingVial() {
        super("the Healing Vial", 'a', ActorAttributeOperations.INCREASE, BaseActorAttributes.HEALTH, INCREASE_HEALTH_VALUE, IS_DISCOUNT, BUY_PRICE, BUY_PRICING, BUY_SCAM_CHANCE, SELL_PRICE, SELL_PRICING, SELL_SCAM_CHANCE);
    }
}
