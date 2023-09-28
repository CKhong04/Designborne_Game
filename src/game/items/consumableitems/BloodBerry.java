package game.items.consumableitems;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.SellAction;
import game.enums.Ability;
import game.items.tradableitems.Sellable;

public class BloodBerry extends ConsumableItem implements Sellable {
    //Private attributes
    private static final int INCREASE_HEALTH_VALUE = 5;
    private static final boolean IS_DISCOUNT = false;
    private static final int SELL_PRICE = 10;

    /***
     * Constructor.
     */
    public BloodBerry() {
        super("BloodBerry", '*', ActorAttributeOperations.INCREASE,BaseActorAttributes.HEALTH, INCREASE_HEALTH_VALUE, IS_DISCOUNT);
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
     * @param sellPrice price of the item.
     */
    public void sold(Actor actor, Actor trader, int sellPrice){
        actor.addBalance(sellPrice);
        actor.removeItemFromInventory(this);
        trader.addItemToInventory(this);
    }
}
