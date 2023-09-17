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
    /***
     * Constructor.
     */
    public RefreshingFlask() {
        super("the Refreshing Flask", 'u', ActorAttributeOperations.INCREASE, BaseActorAttributes.STAMINA, 20,true);
    }

}