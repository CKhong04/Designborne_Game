package game.items.consumableitems;

import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;

/**
 * Class representing a healing vile.
 * Created by:
 * @author Laura Zhakupova
 */
public class HealingVile extends ConsumableItem {
    /***
     * Constructor.
     */
    public HealingVile() {
        super("the Healing vile", 'a', ActorAttributeOperations.INCREASE, BaseActorAttributes.HEALTH, 10);
    }
}
