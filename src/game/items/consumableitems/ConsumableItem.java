package game.items.consumableitems;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.actions.ConsumeAction;
import game.actors.traders.pricings.Pricing;
import game.enums.Status;
import game.items.tradableitems.Buyable;
import game.items.tradableitems.Sellable;

/**
 * Class representing an abstract consumable item.
 * Created by:
 * @author Laura Zhakupova
 */
public abstract class ConsumableItem extends Item implements Buyable, Sellable{
    // Private attributes
    // Consumable attributes
    private final ActorAttributeOperations actorAttributeOperation;
    private final BaseActorAttributes baseActorAttributes;
    private final int percentageValue;
    private final boolean isDiscount;

    // Buyable attributes
    private final int buyPrice;
    private final Pricing buyPricingStrategy;
    private final int buyChanceScam;

    // Sellable attributes
    private final int sellPrice;
    private final Pricing sellPricingStrategy;
    private final int sellChanceScam;
    /***
     * Constructor.
     * @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     */
    public ConsumableItem(String name, char displayChar, ActorAttributeOperations actorAttributeOperation, BaseActorAttributes baseActorAttributes, int percentageValue, boolean isDiscount, int buyPrice, Pricing buyPricingStrategy, int buyChanceScam, int sellPrice, Pricing sellPricingStrategy, int sellChanceScam) {
        super(name, displayChar, true);

        //Consumable attributes
        this.actorAttributeOperation = actorAttributeOperation;
        this.baseActorAttributes = baseActorAttributes;
        this.percentageValue = percentageValue;
        this.isDiscount = isDiscount;

        //Buyable attributes
        this.buyPrice = buyPrice;
        this.buyPricingStrategy = buyPricingStrategy;
        this.buyChanceScam = buyChanceScam;

        //Sellable attributes
        this.sellPrice = sellPrice;
        this.sellPricingStrategy = sellPricingStrategy;
        this.sellChanceScam = sellChanceScam;
        this.addCapability(Status.SELLABLE);
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

    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = super.allowableActions(otherActor, location);
        //actions.add();
        return actions;
    }

    @Override
    public int getBuyPrice(){
        int price = this.buyPricingStrategy.getPrice(this.buyPrice);
        return price;
    }

    @Override
    public int getBuyScamChance(){
        return this.buyChanceScam;
    }

    @Override
    public int getSellPrice(){
        return this.sellPricingStrategy.getPrice(this.sellPrice);
    }

    @Override
    public int getSellScamChance(){
        return this.sellChanceScam;
    }
}
