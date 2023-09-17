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
public class HealingVile extends ConsumableItem{
    /***
     * Constructor.
     */
    public HealingVile() {
        super("the Healing vial", 'a', ActorAttributeOperations.INCREASE, BaseActorAttributes.HEALTH, 10);
    }
}
