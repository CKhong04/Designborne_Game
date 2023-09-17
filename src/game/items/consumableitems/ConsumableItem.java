package game.items.consumableitems;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;
import game.actors.traders.pricings.Pricing;
import game.items.tradableitems.Buyable;
import game.items.tradableitems.Sellable;

/**
 * Class representing an abstract consumable item.
 * Created by:
 * @author Laura Zhakupova
 */
public abstract class ConsumableItem extends Item implements Buyable, Sellable {
    // Private attributes
    // Consumable attributes
    private ActorAttributeOperations actorAttributeOperation;
    private BaseActorAttributes baseActorAttributes;
    private int percentageValue;

    // Buyable attributes
    private int buyPrice;
    private int buyChanceScam;

    // Sellable attributes
    private int sellPrice;
    private int sellChanceScam;

    /***
     * Constructor.
     * @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     */
    public ConsumableItem(String name, char displayChar, ActorAttributeOperations actorAttributeOperation, BaseActorAttributes baseActorAttributes, int percentageValue, int buyPrice, Pricing buyPricingStrategy, int buyChanceScam, int sellPrice, Pricing sellPricingStrategy, int sellChanceScam) {
        super(name, displayChar, true);
        this.actorAttributeOperation = actorAttributeOperation;
        this.baseActorAttributes = baseActorAttributes;
        this.percentageValue = percentageValue;
        this.buyPrice = buyPricingStrategy.getPrice(buyPrice);
        this.buyChanceScam = buyChanceScam;
        this.sellPrice = sellPricingStrategy.getPrice(sellPrice);
        this.sellChanceScam = sellChanceScam;
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
        actions.add(new ConsumeAction(this, this.actorAttributeOperation, this.baseActorAttributes, this.percentageValue));
        return actions;
    }

    @Override
    public int getBuyPrice(){
        return this.buyPrice;
    }

    @Override
    public int getBuyScamChance(){
        return this.buyChanceScam;
    }

    @Override
    public int getSellPrice(){
        return this.sellPrice;
    }

    @Override
    public int getSellScamChance(){
        return this.sellChanceScam;
    }
}
