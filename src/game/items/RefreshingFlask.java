package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
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
import game.items.itemproperties.Upgradable;
import game.utilities.Utility;

/**
 * Class representing a refreshing flask.
 * Created by:
 * @author Laura Zhakupova
 * Modified by:
 * @author Ishita Gupta
 */
public class RefreshingFlask extends Item implements Sellable, Buyable, Consumable, Upgradable {
    //Private attributes
    private static int INCREASE_STAMINA_VALUE = 20;
    private static final int SELL_PRICE = 25;
    private static final int SELL_SCAM_CHANCE = 50;
    private static final int UPGRADE_PRICE = 175;
    private static boolean UPGRADE_HAPPENED = false;



    /**
     * Constructor.
     */
    public RefreshingFlask() {
        super("the Refreshing Flask", 'u', true);
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
        if (!Utility.getChance(SELL_SCAM_CHANCE)){
            actor.addBalance(SELL_PRICE);
        }
        actor.removeItemFromInventory(this);
        trader.addItemToInventory(this);
        return SELL_PRICE;
    }

    /**
     * Performs a buy action on the item.
     *
     * @param actor player who buys an item.
     * @param trader who sells an item.
     * @param buyPrice price of the item.
     * @param scamChance chance of a trader to scam.
     */
    public int bought(Actor actor, Actor trader, int buyPrice, int scamChance) {
        int newPrice = Utility.reducePrice(buyPrice, 10, 20);
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
        ActorAttributeOperations actorAttributeOperation = ActorAttributeOperations.INCREASE;
        int updateValue = actor.getAttributeMaximum(baseActorAttributes) * INCREASE_STAMINA_VALUE / 100;
        actor.modifyAttribute(baseActorAttributes, actorAttributeOperation, updateValue);actor.removeItemFromInventory(this);
        actor.removeItemFromInventory(this);
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

    @Override
    public void upgrade(Actor actor) {
        UPGRADE_HAPPENED = true;
        actor.deductBalance(UPGRADE_PRICE);
        INCREASE_STAMINA_VALUE = 100;
    }
}