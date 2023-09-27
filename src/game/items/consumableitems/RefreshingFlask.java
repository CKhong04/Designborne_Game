package game.items.consumableitems;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.SellAction;
import game.items.tradableitems.Buyable;
import game.items.tradableitems.Sellable;
import game.utilities.Utility;

/**
 * Class representing a refreshing flask.
 * Created by:
 * @author Laura Zhakupova
 */
public class RefreshingFlask extends ConsumableItem implements Sellable, Buyable {
    //Private attributes
    //Consumable attributes
    private static final int INCREASE_STAMINA_VALUE = 20;
    private static final boolean IS_DISCOUNT = true;

    //Sellable attributes
    private static final int SELL_PRICE = 25;
    private static final int SELL_SCAM_CHANCE = 50;
    /***
     * Constructor.
     */
    public RefreshingFlask() {
        super("the Refreshing Flask", 'u', ActorAttributeOperations.INCREASE, BaseActorAttributes.STAMINA, INCREASE_STAMINA_VALUE,IS_DISCOUNT);
    }

    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = super.allowableActions(otherActor, location);
        actions.add(new SellAction(otherActor, this, SELL_PRICE));
        return actions;
    }

    public void sold(Actor actor, Actor trader, int sellPrice){
        if (Utility.getChance(SELL_SCAM_CHANCE)){
            actor.addBalance(sellPrice);
        }
        actor.removeItemFromInventory(this);
        trader.addItemToInventory(this);
    }

    public void bought(Actor actor, Actor trader, int buyPrice, int scamChance) {
        actor.deductBalance(buyPrice);
        trader.removeItemFromInventory(this);
        actor.addItemToInventory(this);
    }
}