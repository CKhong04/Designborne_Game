package game.weapons.skills;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;

import java.util.Random;

/**
 * Created by:
 * @author Minh Nguyen
 * Modified by:
 * Carissa Khong
 */
public class StabAndStepAction extends Action implements StaminaConsumable {
    private final WeaponItem weaponItem;
    private final Actor target;
    private final int staminaDecreasePercentage;
    private final Random rand = new Random();
    private final Location targetLocation;

    /**
     * The constructor for the StabAndStepAction
     * @param weaponItem The weapon used to stab
     * @param target The target being stabbed
     * @param targetLocation The location of the target
     * @param staminaDecreasePercentage The percentage of stamina to be decreased
     */
    public StabAndStepAction(WeaponItem weaponItem, Actor target, Location targetLocation, int staminaDecreasePercentage) {
        this.weaponItem = weaponItem;
        this.target = target;
        this.targetLocation = targetLocation;
        this.staminaDecreasePercentage = staminaDecreasePercentage;
    }

    /**
     * This method consumes the stamina of the actor.
     * @param actor the actor that consumes the stamina.
     * @param staminaDecreasePercentage the percentage of stamina to be decreased.
     * @return boolean, whether the actor has enough stamina to perform the action
     */
    @Override
    public boolean consumeStamina(Actor actor, int staminaDecreasePercentage) {
        int maxStamina = actor.getAttributeMaximum(BaseActorAttributes.STAMINA);
        int consumedAmount = staminaDecreasePercentage * maxStamina / 100;

        if (actor.getAttribute(BaseActorAttributes.STAMINA) >= consumedAmount) {
            actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, consumedAmount);
            return true;
        }

        return false;
    }

    /**
     * The execute method of the Action, checks whether there is enough stamina for the actor performing it
     * Stabs the target and moves to another open spot
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String describing the action completed
     */
    public String execute(Actor actor, GameMap map) {
        boolean isStaminaEnough = consumeStamina(actor, staminaDecreasePercentage);

        try {
            if (!(isStaminaEnough)) {
                throw new Exception();
            }

            StringBuilder result = new StringBuilder();

            if (!(rand.nextInt(100) <= weaponItem.chanceToHit())) {
                result.append(new AttackAction(target, targetLocation.toString(), weaponItem).execute(actor, map)).append("\n");
            } else {
                result.append(actor).append(" misses ").append(target).append("\n");
            }

            Location playerLocation = map.locationOf(actor);

            for (Exit availableExit: playerLocation.getExits()) {
                Location destination = availableExit.getDestination();

                if (!destination.containsAnActor() && destination.getGround().canActorEnter(actor)) {
                    map.moveActor(actor, destination);
                    result.append(actor).append( " steps away");
                    break;
                }
            }

            if (playerLocation.getActor() == actor) {
                result.append("\n").append(actor).append(" fails to step away");
            }

            return result.toString();
        } catch (Exception e) {
            return actor + " does not have enough stamina to complete the Great Slam Skill!";
        }
    }

    /**
     * Menu Description for the action class
     *
     * @param actor The actor performing the action.
     * @return String, saying the action that takes place
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " stabs " + target +
                " with " + this.weaponItem + " and steps away!";
    }
}
