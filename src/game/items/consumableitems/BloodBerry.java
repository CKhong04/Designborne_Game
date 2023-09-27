package game.items.consumableitems;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.SellAction;
import game.items.tradableitems.Sellable;

public class BloodBerry extends ConsumableItem implements Sellable {
    //Private attributes
    //Consumable attributes
    private static final int INCREASE_HEALTH_VALUE = 5;
    private static final boolean IS_DISCOUNT = false;

    //Sellable attributes
    private static final int SELL_PRICE = 10;

    /***
     * Constructor.
     */
    public BloodBerry() {
        super("BloodBerry", '*', ActorAttributeOperations.INCREASE,BaseActorAttributes.HEALTH, INCREASE_HEALTH_VALUE, IS_DISCOUNT);
    }

    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = super.allowableActions(otherActor, location);
        actions.add(new SellAction(otherActor, this, SELL_PRICE));
        return actions;
    }

    public void sold(Actor actor, Actor trader, int sellPrice){
        actor.addBalance(sellPrice);
        actor.removeItemFromInventory(this);
        trader.addItemToInventory(this);
    }
}
