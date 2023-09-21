package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.actors.traders.pricings.Pricing;
import game.actors.traders.pricings.RegularPricing;
import game.enums.Ability;
import game.enums.Status;
import game.items.tradableitems.Buyable;
import game.items.tradableitems.Sellable;
import game.weapons.skills.GreatSlamAction;
import game.weapons.skills.GreatSlamCapable;

/**
 * Class representing a Giant Hammer.
 * Created by:
 * @author Minh Nguyen
 */
public class GiantHammer extends WeaponItem implements Buyable, Sellable, GreatSlamCapable {
    /**
     * Name of this weapon.
     */
    private static final String NAME = "Giant Hammer";
    /**
     * Display character of this weapon.
     */
    private static final char DISPLAY_CHAR = 'P';
    /**
     * The damage to this weapon.
     */
    private static final int DAMAGE = 160;
    /**
     * The hit rate of this weapon.
     */
    private static final int HIT_RATE = 90;
    /**
     * The verb of this weapon.
     */
    private static final String VERB = "smashes";
    /**
     * The stamina decrease percentage of this weapon.
     */
    private static final int STAMINA_DECREASE_PERCENTAGE = 5;
    /**
     * The buy price of this weapon.
     */
    private static final int BUY_PRICE = 0;
    /**
     * The buy pricing of this weapon.
     */
    private static final Pricing BUY_PRICING = new RegularPricing();
    /**
     * The sell price of this weapon.
     */
    private static final int SELL_PRICE = 250;
    /**
     * The sell pricing of this weapon.
     */
    private static final Pricing SELL_PRICING = new RegularPricing();
    /**
     * The buy scam chance of this weapon.
     */
    private static final int BUY_SCAM_CHANCE =  0;
    /**
     * The sell scam chance of this weapon.
     */
    private static final int SELL_SCAM_CHANCE = 0;

    /**
     * Constructor.
     */
    public GiantHammer() {
        super(NAME, DISPLAY_CHAR, DAMAGE, VERB, HIT_RATE);

        this.addCapability(Ability.USED_AS_WEAPON);
        this.addCapability(Status.SELLABLE);
    }

    /**
     * Get the GreatSlamAction of this weapon.
     *
     * @param otherActor the actor to be attacked.
     * @return the GreatSlamAction of this weapon.
     */
    public GreatSlamAction getGreatSlamAction(Actor otherActor) {
        return new GreatSlamAction(this, otherActor, STAMINA_DECREASE_PERCENTAGE);
    }

    /**
     * Get the allowable actions of this weapon.
     *
     * @param otherActor the actor to be attacked.
     * @param location the location of the actor.
     * @return the allowable actions of this weapon.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = super.allowableActions(otherActor, location);
        actions.add(new AttackAction(otherActor,location.toString(),this));
        actions.add(getGreatSlamAction(otherActor));
        return actions;
    }

    /**
     * Get the buy price of this weapon.
     *
     * @return the buy price of this weapon.
     */
    public int getBuyPrice() {
        return BUY_PRICING.getPrice(BUY_PRICE);
    }

    /**
     * Get the buy scam chance of this weapon.
     *
     * @return the buy scam chance of this weapon.
     */
    public int getBuyScamChance(){
        return BUY_SCAM_CHANCE;
    }

    /**
     * Get the sell price of this weapon.
     *
     * @return the sell price of this weapon.
     */
    public int getSellPrice() {
        return SELL_PRICING.getPrice(SELL_PRICE);
    }

    /**
     * Get the sell scam chance of this weapon.
     *
     * @return the sell scam chance of this weapon.
     */
    public int getSellScamChance(){
        return SELL_SCAM_CHANCE;
    }
}
