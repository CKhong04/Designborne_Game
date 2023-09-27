package game.items.consumableitems;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.actions.ConsumeAction;
import game.actions.SellAction;
import game.actors.traders.pricings.Pricing;
import game.enums.Status;
import game.items.tradableitems.Buyable;
import game.items.tradableitems.Sellable;

/**
 * Class representing an abstract consumable item.
 * Created by:
 * @author Laura Zhakupova
 */
public abstract class ConsumableItem extends Item {
    // Private attributes
    // Consumable attributes
    private final ActorAttributeOperations actorAttributeOperation;
    private final BaseActorAttributes baseActorAttributes;
    private final int percentageValue;
    private final boolean isDiscount;
    /***
     * Constructor.
     * @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     */
    public ConsumableItem(String name, char displayChar, ActorAttributeOperations actorAttributeOperation, BaseActorAttributes baseActorAttributes, int percentageValue, boolean isDiscount) {
        super(name, displayChar, true);

        //Consumable attributes
        this.actorAttributeOperation = actorAttributeOperation;
        this.baseActorAttributes = baseActorAttributes;
        this.percentageValue = percentageValue;
        this.isDiscount = isDiscount;
    }

    /**
     * Add action to activate the weapon's skill into the allowable list of actions
     *
     * @param actor the actor that owns the item.
     * @return list of actions that can be executed by this weapon.
     */
    @Override
    public ActionList allowableActions(Actor actor) {
        ActionList actions = super.allowableActions(actor);
        actions.add(new ConsumeAction(this, this.actorAttributeOperation, this.baseActorAttributes, this.percentageValue, this.isDiscount));
        return actions;
    }
}
