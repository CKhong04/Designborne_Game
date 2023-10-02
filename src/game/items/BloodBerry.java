package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.actions.SellAction;
import game.enums.Ability;
import game.items.itemproperties.Consumable;
import game.items.itemproperties.Sellable;

public class BloodBerry extends Item implements Sellable, Consumable {
    //Private attributes
    private static final int INCREASE_HEALTH_VALUE = 5;
    private static final int SELL_PRICE = 10;

    /***
     * Constructor.
     */
    public BloodBerry() {
        super("BloodBerry", '*', true);
    }

    /**
     * List of allowable actions that the item allows its owner do to other actor.
     * Allowing the actor to sell this item to the traders.
     *
     * @param otherActor the other actor.
     * @param location the location of the other actor.
     * @return the allowable actions of this weapon.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = super.allowableActions(otherActor, location);
        if (otherActor.hasCapability((Ability.CAN_BE_SOLD_TO))){
            actions.add(new SellAction(otherActor, this, SELL_PRICE));
        }
        return actions;
    }

    /**
     * Performs a sell action on the item.
     *
     * @param actor player who sell an item.
     * @param trader who buys an item.
     */
    public int sold(Actor actor, Actor trader){
        actor.addBalance(SELL_PRICE);
        actor.removeItemFromInventory(this);
        trader.addItemToInventory(this);
        return SELL_PRICE;
    }

    @Override
    public void consumeItem(Actor actor) {
        BaseActorAttributes baseActorAttributes = BaseActorAttributes.HEALTH;
        ActorAttributeOperations actorAttributeOperation = ActorAttributeOperations.INCREASE;
        actor.modifyAttributeMaximum(baseActorAttributes, actorAttributeOperation, INCREASE_HEALTH_VALUE);
        actor.removeItemFromInventory(this);
    }

    @Override
    public ActionList allowableActions(Actor actor) {
        ActionList actions = super.allowableActions(actor);
        actions.add(new ConsumeAction(this));
        return actions;
    }
}
