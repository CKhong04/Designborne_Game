package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enums.Status;
import game.weapons.skills.AbleToActivateSkill;
import game.weapons.skills.FocusAction;

/**
 * Class representing a Broadsword.
 * Created by:
 * @author Laura Zhakupova
 */
public class Broadsword extends WeaponItem implements AbleToActivateSkill {
    // Private attributes
    private static final float DEFAULT_DAMAGE_MULTIPLIER = 1.0f;
    private int turnCounter = 0;
    private int normalHitRate;
    private int skillTurnCounter;
    private int skillDamageMultiplier;
    private int skillHitRate;
    private Action weaponAbility;

    /**
     * A constructor which accepts values of damage, hit rate, number of turns for the skill to ba activated,
     * increase in damage multiplier after the skill was activated and hit rate after the skill was activated.
     *
     * @param damage amount of damage this weapon does.
     * @param hitRate the probability/chance to hit the target.
     * @param skillTurnCounter number of turns for the skill to ba activated.
     * @param skillDamageMultiplier increase in damage multiplier after the skill was activated.
     * @param skillHitRate hit rate after the skill was activated.
     */
    public Broadsword(int damage, int hitRate, int skillTurnCounter, int skillDamageMultiplier, int skillHitRate) {
        super("Broadsword", '1', damage, "slashes", hitRate);
        this.normalHitRate = hitRate;
        this.skillTurnCounter = skillTurnCounter;
        this.skillDamageMultiplier = skillDamageMultiplier;
        this.skillHitRate = skillHitRate;
        this.weaponAbility = new FocusAction(this,20);
        this.addCapability(Status.EQUIPPED_WEAPON);
    }

    /**
     * Check if the skill is activated, if it is activated, manage its effects.
     * If the weapon's skill is activated, it checks the turn counter:
     * - If the turn counter is greater than 0, it temporarily increases the character's
     *   damage multiplier and updates the hit rate based on the skill's properties.
     * - If the turn counter is equal to 0, it resets the character's hit rate and damage
     *   multiplier to their default values and removes the "FOCUS_SKILL" capability.
     */
    @Override
    public void activatedSkill(){
        if (this.hasCapability(Status.SKILL_ACTIVATED)){
            this.turnCounter = this.skillTurnCounter;
            this.removeCapability(Status.SKILL_ACTIVATED);
        }

        if (this.hasCapability(Status.FOCUS_SKILL)){
            if (this.turnCounter > 0){
                float newDamageMultiplier = DEFAULT_DAMAGE_MULTIPLIER * this.skillDamageMultiplier / 100;
                this.increaseDamageMultiplier(newDamageMultiplier);
                this.updateHitRate(this.skillHitRate);
                this.turnCounter -=1;
            } else if (this.turnCounter == 0) {
                super.updateHitRate(this.normalHitRate);
                super.updateDamageMultiplier(DEFAULT_DAMAGE_MULTIPLIER);
                this.removeCapability(Status.FOCUS_SKILL);
            }
        }
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
        activatedSkill();
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
        actions.add(this.weaponAbility);
        return actions;
    }
}