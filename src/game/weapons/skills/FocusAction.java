
package game.weapons.skills;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enums.Status;

/**
 * An ability of a weapon to increase damage multiplier and hit rate.
 * Created by:
 * @author Laura Zhakupova
 */
public class FocusAction extends Action {
    /**
     * Current weapon
     */
    private WeaponItem weaponItem;
    private int decreaseStaminaPercentage;

    /**
     * Constructor.
     *
     * @param weaponItem a weapon item which uses the ability.
     */
    public FocusAction(WeaponItem weaponItem, int decreaseStaminaPercentage) {
        this.weaponItem = weaponItem;
        this.decreaseStaminaPercentage = decreaseStaminaPercentage;
    }

    /**
     * When an ability is active, increase damage multiplier and a hit rate for certain number of turns.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string describing who has dropped which item.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // Calculating how much stamina needed
        int maxStamina = actor.getAttributeMaximum(BaseActorAttributes.STAMINA);
        int decreasePercentage = maxStamina * this.decreaseStaminaPercentage / 100;

        if (actor.getAttribute(BaseActorAttributes.STAMINA)>=decreasePercentage){
            actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE,decreasePercentage);
            this.weaponItem.addCapability(Status.SKILL_ACTIVATED);
            this.weaponItem.addCapability(Status.FOCUS_SKILL);
            return actor + " takes a deep breath and focuses all their might!";
        } else {
            return actor + " does not have enough stamina!";
        }
    }

    /**
     * A string describing the action suitable for displaying in the UI menu.
     *
     * @param actor The actor performing the action.
     * @return a String, e.g. "Player drops the potato"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " activates the skills of the " + this.weaponItem;
    }
}