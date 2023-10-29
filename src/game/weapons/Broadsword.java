package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.actions.SellAction;
import game.actions.UpgradeAction;
import game.enums.Ability;
import game.enums.Status;
import game.items.itemproperties.Buyable;
import game.items.itemproperties.Sellable;
import game.items.itemproperties.Upgradeable;
import game.utilities.Utility;
import game.weapons.skills.FocusCapable;
import game.weapons.skills.FocusAction;

/**
 * Class representing a Broadsword.
 * Created by:
 * @author Laura Zhakupova
 * Modified by:
 * Khoi Nguyen, Carissa Khong
 */
public class Broadsword extends WeaponItem implements FocusCapable, Sellable, Buyable, Upgradeable {
    /**
     * The turn counter.
     */
    private int turnCounter = 0;
    /**
     * Normal damage for this weapon.
     */
    private static int DAMAGE = 110;

    private final static int DEFAULT_DAMAGE = 110;
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

    private static float DAMAGE_MULTIPLIER;

    private static final int UPGRADE_PRICE = 1000;

    private final Display display = new Display();

    /**
     * Constructor.
     */
    public Broadsword() {
        super("Broadsword", '1', DAMAGE, "slashes", HIT_RATE);
        DAMAGE_MULTIPLIER = DEFAULT_DAMAGE_MULTIPLIER;
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
                DAMAGE_MULTIPLIER = DEFAULT_DAMAGE_MULTIPLIER + DEFAULT_DAMAGE_MULTIPLIER * SKILL_DAMAGE_MULTIPLIER / 100;

                this.updateDamageMultiplier(DAMAGE_MULTIPLIER);
                this.updateHitRate(NEW_HIT_RATE);
                display.println("Focus skill turns left: " + turnCounter);
                this.turnCounter -=1;
            } else if (this.turnCounter == 0) {
                super.updateHitRate(HIT_RATE);
                DAMAGE_MULTIPLIER = DEFAULT_DAMAGE_MULTIPLIER;

                this.removeCapability(Status.FOCUS_SKILL);
                display.println("The Broadsword's focus skill has been deactivated");
            }
        }
    }

    /**
     * Returns a Focus Action for the Broadsword.
     * @return A Focus Action.
     */
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
     * Allowing the actor to sell this weapon to the traders.
     *
     * @param otherActor the other actor.
     * @param location the location of the other actor.
     * @return the allowable actions of this weapon.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location){
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_PLAYER)){
            actions.add(new AttackAction(otherActor,location.toString(),this));
        }
        if (otherActor.hasCapability((Ability.CAN_BE_SOLD_TO))){
            actions.add(new SellAction(otherActor, this));
        }
        if (otherActor.hasCapability(Ability.CAN_UPGRADE_ITEM)) {
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
        actor.addBalance(SELL_PRICE);
        actor.removeItemFromInventory(this);
        trader.addItemToInventory(this);
        return SELL_PRICE;
    }

    /**
     * Performs a buy action on the item.
     * There is a chance that the trader takes player's runes
     * without giving the weapon.
     *
     * @param actor player who buys an item.
     * @param trader who sells an item.
     * @param buyPrice price of the item.
     * @param scamChance chance of a trader to scam.
     */
    public int bought(Actor actor, Actor trader, int buyPrice, int scamChance){
        if (!Utility.getChance(scamChance)) {
            actor.addItemToInventory(this);
        }
        actor.deductBalance(buyPrice);
        return buyPrice;
    }

    /**
     * Overrides the damage method from the WeaponItem class in the game engine's weapons package.
     * It checks if the damage multiplier has been updated already and is now greater than the default multiplier.
     * If so, use the default damage multiplier.
     * @return The damage inflicted using the weapon.
     */
    @Override
    public int damage() {
        if (DAMAGE_MULTIPLIER > DEFAULT_DAMAGE_MULTIPLIER){
            DAMAGE =  Math.round(DEFAULT_DAMAGE * DAMAGE_MULTIPLIER);
        }
        return DAMAGE;
    }


    /**
     * Called when upgrading the Broadsword. Updates the actor's balance and increases the damage inflicted when using
     * the weapon.
     * @param actor The actor holding the weapon.
     */
    @Override
    public void upgrade(Actor actor) {
        actor.deductBalance(UPGRADE_PRICE);
        DAMAGE += 10;
    }

}