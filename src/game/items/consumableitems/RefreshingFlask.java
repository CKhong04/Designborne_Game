package game.items.consumableitems;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.SellAction;
import game.enums.Ability;
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
    private static final int INCREASE_STAMINA_VALUE = 20;
    private static final boolean IS_DISCOUNT = true;
    private static final int SELL_PRICE = 25;
    private static final int SELL_SCAM_CHANCE = 50;
    /***
     * Constructor.
     */
    public RefreshingFlask() {
        super("the Refreshing Flask", 'u', ActorAttributeOperations.INCREASE, BaseActorAttributes.STAMINA, INCREASE_STAMINA_VALUE,IS_DISCOUNT);
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
        if (!Utility.getChance(SELL_SCAM_CHANCE)){
            actor.addBalance(sellPrice);
        }
        actor.removeItemFromInventory(this);
        trader.addItemToInventory(this);
    }

    /**
     * Performs a buy action on the item.
     *
     * @param actor player who buys an item.
     * @param trader who sells an item.
     * @param buyPrice price of the item.
     * @param scamChance chance of a trader to scam.
     */
    public void bought(Actor actor, Actor trader, int buyPrice, int scamChance) {
        actor.deductBalance(buyPrice);
        trader.addBalance(buyPrice);
        trader.removeItemFromInventory(this);
        actor.addItemToInventory(this);
    }
}