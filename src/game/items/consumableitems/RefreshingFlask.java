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
    private static int BUY_PRICE = 75;
    private static Pricing BUY_PRICING = new ReducedPricing(10, 20);
    private static int SELL_PRICE = 25;
    private static Pricing SELL_PRICING = new RegularPricing();
    private static int BUY_SCAM_CHANCE = 0;
    private static int SELL_SCAM_CHANCE = 50;
    /***
     * Constructor.
     */
    public RefreshingFlask() {
        super("the Refreshing Flask", 'u', ActorAttributeOperations.INCREASE, BaseActorAttributes.STAMINA, 20,true, BUY_PRICE, BUY_PRICING,BUY_SCAM_CHANCE, SELL_PRICE,SELL_PRICING, SELL_SCAM_CHANCE);
    }
}