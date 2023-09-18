package game.items.consumableitems;

import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.actors.traders.pricings.Pricing;
import game.actors.traders.pricings.ReducedPricing;
import game.actors.traders.pricings.RegularPricing;

/**
 * Class representing a refreshing flask.
 * Created by:
 * @author Laura Zhakupova
 */
public class RefreshingFlask extends ConsumableItem {
    //Private attributes
    //Consumable attributes
    private static final int INCREASE_STAMINA_VALUE = 20;
    private static final boolean IS_DISCOUNT = true;

    //Buyable attributes
    private static final int BUY_PRICE = 75;
    private static final int BUY_REDUCE_CHANCE = 10;
    private static final int BUY_REDUCE_PERCENTAGE = 20;
    private static final Pricing BUY_PRICING = new ReducedPricing(BUY_REDUCE_CHANCE, BUY_REDUCE_PERCENTAGE);
    private static final int BUY_SCAM_CHANCE = 0;

    //Sellable attributes
    private static final int SELL_PRICE = 25;
    private static final Pricing SELL_PRICING = new RegularPricing();
    private static final int SELL_SCAM_CHANCE = 50;
    /***
     * Constructor.
     */
    public RefreshingFlask() {
        super("the Refreshing Flask", 'u', ActorAttributeOperations.INCREASE, BaseActorAttributes.STAMINA, INCREASE_STAMINA_VALUE,IS_DISCOUNT, BUY_PRICE, BUY_PRICING,BUY_SCAM_CHANCE, SELL_PRICE,SELL_PRICING, SELL_SCAM_CHANCE);
    }
}