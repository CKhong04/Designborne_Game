package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actors.traders.pricings.Pricing;
import game.actors.traders.pricings.RegularPricing;
import game.enums.Ability;
import game.enums.Status;
import game.items.tradableitems.Buyable;
import game.items.tradableitems.Sellable;
import game.weapons.skills.StabAndStepAction;
import game.weapons.skills.StabAndStepCapable;

/**
 * Class representing a Great Knife.
 */
public class GreatKnife extends WeaponItem implements Buyable, Sellable, StabAndStepCapable {
    /**
     * Name of this weapon.
     */
    private static final String NAME = "Great Knife";
    /**
     * Display character of this weapon.
     */
    private static final char DISPLAY_CHAR = '>';
    /**
     * The damage to this weapon.
     */
    private static final int DAMAGE = 75;
    /**
     * The hit rate of this weapon.
     */
    private static final int HIT_RATE = 70;
    /**
     * The verb of this weapon.
     */
    private static final String VERB = "slashes";
    /**
     * The stamina decrease percentage of this weapon.
     */
    private static final int STAMINA_DECREASE_PERCENTAGE = 25;
    /**
     * The buy price of this weapon.
     */
    private static final int BUY_PRICE = 300;
    /**
     * The buy pricing of this weapon.
     */
    private static final Pricing BUY_PRICING = new RegularPricing();
    /**
     * The sell price of this weapon.
     */
    private static final int SELL_PRICE = 175;
    /**
     * The sell pricing of this weapon.
     */
    private static final Pricing SELL_PRICING = new RegularPricing();
    /**
     * The buy scam chance of this weapon.
     */
    private static final int BUY_SCAM_CHANCE =  5;
    /**
     * The sell scam chance of this weapon.
     */
    private static final int SELL_SCAM_CHANCE = 10;

    /**
     * Constructor.
     */
    public GreatKnife() {
        super(NAME, DISPLAY_CHAR, DAMAGE, VERB, HIT_RATE);

        this.addCapability(Status.SELLABLE);
        this.addCapability(Ability.USED_AS_WEAPON);
    }

    /**
     * Gets the StabAndStepAction of this weapon.
     * @param otherActor the actor to stab and step.
     * @return the StabAndStepAction of this weapon.
     */
    public StabAndStepAction getStabAndStepAction(Actor otherActor){
        return new StabAndStepAction(this, otherActor, STAMINA_DECREASE_PERCENTAGE);
    }

    /**
     * Gets the allowable actions of this weapon.
     * @param otherActor the actor to stab and step.
     * @param location the location of the actor.
     * @return the allowable actions of this weapon.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions =  super.allowableActions(otherActor, location);
        actions.add(this.getStabAndStepAction(otherActor));
        return actions;
    }

    /**
     * Gets the buy price of this weapon.
     * @return the buy price of this weapon.
     */
    public int getBuyPrice() {
        return BUY_PRICING.getPrice(BUY_PRICE);
    }

    /**
     * Gets the buy scam chance of this weapon.
     * @return the buy scam chance of this weapon.
     */
    public int getBuyScamChance(){
        return BUY_SCAM_CHANCE;
    }

    /**
     * Gets the sell price of this weapon.
     * @return the sell price of this weapon.
     */
    public int getSellPrice() {
        return SELL_PRICING.getPrice(SELL_PRICE);
    }

    /**
     * Gets the sell scam chance of this weapon.
     * @return the sell scam chance of this weapon.
     */
    public int getSellScamChance(){
        return SELL_SCAM_CHANCE;
    }
}
