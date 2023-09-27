package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.actions.SellAction;
import game.actors.traders.pricings.Pricing;
import game.actors.traders.pricings.RegularPricing;
import game.enums.Ability;
import game.enums.Status;
import game.items.tradableitems.Buyable;
import game.items.tradableitems.Sellable;
import game.utilities.Utility;
import game.weapons.skills.FocusCapable;
import game.weapons.skills.FocusAction;

/**
 * Class representing a Broadsword.
 * Created by:
 * @author Laura Zhakupova
 */
public class Broadsword extends WeaponItem implements FocusCapable, Sellable, Buyable {
    /**
     * The turn counter.
     */
    private int turnCounter = 0;
    /**
     * Normal damage of this weapon.
     */
    private static final int DAMAGE = 110;
    /**
     * Normal hit rate of this weapon.
     */
    private static final int HIT_RATE = 80;
    /**
     * The default damage multiplier of this weapon.
     */
    private static final float DEFAULT_DAMAGE_MULTIPLIER = 1.0f;
    /**
     * Number of turns the skill is activated.
     */
    private static final int SKILL_TURN_COUNTER = 5;
    /**
     * Increase in damage multiplier when skill is activated.
     */
    private static final int SKILL_DAMAGE_MULTIPLIER = 10;
    /**
     * Hit rate when skill is activated.
     */
    private static final int NEW_HIT_RATE = 90;
    /**
     * The sell price of this weapon.
     */
    private static final int SELL_PRICE = 100;

    /**
     * Constructor.
     */
    public Broadsword() {
        super("Broadsword", '1', DAMAGE, "slashes", HIT_RATE);
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
            this.turnCounter = SKILL_TURN_COUNTER;
            this.removeCapability(Status.SKILL_ACTIVATED);
        }

        if (this.hasCapability(Status.FOCUS_SKILL)){
            if (this.turnCounter > 0){
                float newDamageMultiplier = DEFAULT_DAMAGE_MULTIPLIER * SKILL_DAMAGE_MULTIPLIER / 100;

                this.increaseDamageMultiplier(newDamageMultiplier);
                this.updateHitRate(NEW_HIT_RATE);
                this.turnCounter -=1;
            } else if (this.turnCounter == 0) {
                super.updateHitRate(HIT_RATE);
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
        actions.add(new SellAction(otherActor, this, SELL_PRICE));
        return actions;
    }

    public void sold(Actor actor, Actor trader, int sellPrice){
        actor.addBalance(sellPrice);
        actor.removeItemFromInventory(this);
        trader.addItemToInventory(this);
    }

    public void bought(Actor actor, Actor trader, int buyPrice, int scamChance){
        if (!Utility.getChance(scamChance)) {
            trader.removeItemFromInventory(this);
            actor.addItemToInventory(this);
        }
        actor.deductBalance(buyPrice);
    }
}