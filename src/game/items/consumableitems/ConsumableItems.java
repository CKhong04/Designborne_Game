package game.items.consumableitems;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;

/**
 * Class representing an abstract consumable item.
 * Created by:
 * @author Laura Zhakupova
 */
public abstract class ConsumableItems extends Item implements ConsumableItem {
    // Private attributes
    // Consumable attributes

    /***
     * Constructor.
     * @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     */
    public ConsumableItems(String name, char displayChar) {
        super(name, displayChar, true);

    }

    /**
     * Add action to activate the weapon's skill into the allowable list of actions
     *
     *
     * @param actor the actor that owns the item.
     * @return list of actions that can be executed by this weapon.
     */
    @Override
    public ActionList allowableActions(Actor actor) {
        ActionList actions = super.allowableActions(actor);
        actions.add(new ConsumeAction(this));
        return actions;
    }
}
