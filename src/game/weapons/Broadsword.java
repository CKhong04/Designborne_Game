package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
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
import game.weapons.skills.FocusCapable;
import game.weapons.skills.FocusAction;

/**
 * Class representing a Broadsword.
 * Created by:
 * @author Laura Zhakupova
 */
public class Broadsword extends WeaponItem implements FocusCapable, Buyable, Sellable {
    /**
     * Name of this weapon.
     */
    private static final String NAME = "Broadsword";
    /**
     * Display character of this weapon.
     */
    private static final char DISPLAY_CHAR = '1';
    /**
     * The damage to this weapon.
     */
    private static final int DAMAGE = 110;
    /**
     * The hit rate of this weapon.
     */
    private static final int HIT_RATE = 80;
    /**
     * The verb of this weapon.
     */
    private static final String VERB = "slashes";
    /**
     * The default damage multiplier of this weapon.
     */
    private static final float DEFAULT_DAMAGE_MULTIPLIER = 1.0f;

    private int turnCounter = 0;
    private int normalHitRate;
    private int skillTurnCounter;
    private int skillDamageMultiplier;
    private static final int NEW_HIT_RATE = 90;

    // Buyable/Sellable attributes
    private static final int BUY_PRICE = 250;
    private static final Pricing BUY_PRICING = new RegularPricing();
    private static final int SELL_PRICE = 100;
    private static final Pricing SELL_PRICING = new RegularPricing();
    private static final int BUY_SCAM_CHANCE = 5;
    private static final int SELL_SCAM_CHANCE = 0;

    /**
     * Constructor.
     */
    public Broadsword() {
        super(NAME, DISPLAY_CHAR, DAMAGE, VERB, HIT_RATE);

        this.addCapability(Status.SELLABLE);
        this.addCapability(Ability.USED_AS_WEAPON);
    }

    /**
     * Check if the skill is activated, if it is activated, manage its effects.
     * If the weapon's skill is activated, it checks the turn counter:
     * - If the turn counter is greater than 0, it temporarily increases the character's
     *   damage multiplier and updates the hit rate based on the skill's properties.
     * - If the turn counter is equal to 0, it resets the character's hit rate and damage
     *   multiplier to their default values and removes the "FOCUS_SKILL" capability.
     */
    public void activateSkill(){
        if (this.hasCapability(Status.SKILL_ACTIVATED)){
            this.turnCounter = this.skillTurnCounter;
            this.removeCapability(Status.SKILL_ACTIVATED);
        }

        if (this.hasCapability(Status.FOCUS_SKILL)){
            if (this.turnCounter > 0){
                float newDamageMultiplier = DEFAULT_DAMAGE_MULTIPLIER * this.skillDamageMultiplier / 100;

                this.increaseDamageMultiplier(newDamageMultiplier);
                this.updateHitRate(NEW_HIT_RATE);
                this.turnCounter -=1;
            } else if (this.turnCounter == 0) {
                super.updateHitRate(this.normalHitRate);
                super.updateDamageMultiplier(DEFAULT_DAMAGE_MULTIPLIER);

                this.removeCapability(Status.FOCUS_SKILL);
            }
        }
    }

    public FocusAction getFocusAction() {
        return new FocusAction(this, 20);
    }

    /**
     * Inform a carried Item of the passage of time.
     * This method is called once per turn, if the Item is being carried.
     * This method manages weapon's skill every turn.
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        activateSkill();
    }

    /**
     * Inform an Item on the ground of the passage of time.
     * This method is called once per turn, if the item rests upon the ground.
     * If the item is on the ground, weapon's skill is removed.
     *
     * @param currentLocation The location of the ground on which we lie.
     */
    public void tick(Location currentLocation) {
        if (this.hasCapability(Status.FOCUS_SKILL)){
            this.removeCapability(Status.FOCUS_SKILL);
        }
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
        actions.add(getFocusAction());
        return actions;
    }

    /**
     * List of allowable actions that the item allows its owner do to other actor.
     * Allowing this weapon to attack another actor.
     *
     * @param otherActor the other actor
     * @param location the location of the other actor
     * @return
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location){
        ActionList actions = new ActionList();
        actions.add(new AttackAction(otherActor,location.toString(),this));
        return actions;
    }

    @Override
    public int getBuyPrice(){
        return BUY_PRICING.getPrice(BUY_PRICE);
    }

    @Override
    public int getBuyScamChance(){
        return BUY_SCAM_CHANCE;
    }

    @Override
    public int getSellPrice(){
        return SELL_PRICING.getPrice(SELL_PRICE);
    }

    @Override
    public int getSellScamChance(){
        return SELL_SCAM_CHANCE;
    }
}