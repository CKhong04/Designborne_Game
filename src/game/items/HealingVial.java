package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.actions.SellAction;
import game.actions.UpgradeAction;
import game.enums.Ability;
import game.items.itemproperties.Buyable;
import game.items.itemproperties.Consumable;
import game.items.itemproperties.Sellable;
import game.items.itemproperties.Upgradeable;
import game.utilities.Utility;

/**
 * Class representing a healing vial.
 * Created by:
 * @author Laura Zhakupova
 * Modified by: Ishita Gupta, Carissa Khong
 */
public class HealingVial extends Item implements Sellable, Buyable, Consumable, Upgradeable{
    //Private attributes
    private int increaseHealthValue = 10;
    private static final int SELL_PRICE = 35;

    private static final int UPGRADE_PRICE = 250;

    private static boolean UPGRADE_HAPPENED = false;

    /**
     * Constructor.
     */
    public HealingVial() {
        super("Healing Vial",'a', true);
    }

    /**
     * List of allowable actions that the item can perform to the current actor.
     *
     * @param actor the actor that owns the item
     * @return an unmodifiable list of Actions
     */
    @Override
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
            actions.add(new SellAction(otherActor, this));
        }
        if (!UPGRADE_HAPPENED && otherActor.hasCapability(Ability.CAN_UPGRADE_ITEM)){
            actions.add(new UpgradeAction(this, UPGRADE_PRICE));
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

    /**
     * Consumes the item and removes it from the actor's inventory.
     * Make changes to the actor's required skill
     *
     * @param actor the actor who is consuming the item
     */
    @Override
    public void consumeItem(Actor actor) {
        BaseActorAttributes baseActorAttributes = BaseActorAttributes.HEALTH;
        int updateValue = actor.getAttributeMaximum(baseActorAttributes) * increaseHealthValue / 100;
        actor.heal(updateValue);
        actor.removeItemFromInventory(this);
    }

    /**
     * When an upgrade occurs, a boolean signifying the item has been updated becomes true. The actor has the upgrade
     * price deducted from their balance. The value signifying the healing amount is updated to 80.
     * @param actor The actor whose inventory the HealingVial is in.
     */
    @Override
    public void upgrade(Actor actor) {
        UPGRADE_HAPPENED = true;
        actor.deductBalance(UPGRADE_PRICE);
        increaseHealthValue = 80;
    }
}