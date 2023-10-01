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
import game.items.itemproperties.Buyable;
import game.items.itemproperties.Consumable;
import game.items.itemproperties.Sellable;
import game.utilities.Utility;

/**
 * Class representing a healing vial.
 * Created by:
 * @author Laura Zhakupova
 */
public class HealingVial extends Item implements Sellable, Buyable, Consumable {
    //Private attributes
    private static final int INCREASE_HEALTH_VALUE = 10;
    private static final int SELL_PRICE = 35;
    /***
     * Constructor.
     */
    public HealingVial() {
        super("Healing Vial",'a', true);
    }

    public ActionList allowableActions(Actor actor) {
        ActionList actions = super.allowableActions(actor);
        actions.add(new ConsumeAction(this));
        return actions;
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
        int newPrice = Utility.increasePrice(SELL_PRICE, 10, 100);
        actor.addBalance(newPrice);
        actor.removeItemFromInventory(this);
        trader.addItemToInventory(this);
        return newPrice;
    }

    /**
     * Performs a buy action on the item.
     *
     * @param actor player who buys an item.
     * @param trader who sells an item.
     * @param buyPrice price of the item.
     * @param scamChance chance of a trader to scam.
     */
    public int bought(Actor actor, Actor trader, int buyPrice, int scamChance){
        int newPrice = Utility.increasePrice(buyPrice, 25, 50);
        actor.deductBalance(newPrice);
        trader.addBalance(newPrice);
        actor.addItemToInventory(this);
        return newPrice;
    }

    @Override
    public void consumeItem(Actor actor) {
        BaseActorAttributes baseActorAttributes = BaseActorAttributes.HEALTH;
        ActorAttributeOperations actorAttributeOperation = ActorAttributeOperations.INCREASE;
        int updateValue = actor.getAttributeMaximum(baseActorAttributes) * INCREASE_HEALTH_VALUE / 100;
        actor.modifyAttribute(baseActorAttributes, actorAttributeOperation, updateValue);
        actor.removeItemFromInventory(this);
    }
}