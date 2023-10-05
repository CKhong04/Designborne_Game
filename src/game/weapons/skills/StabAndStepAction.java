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

import java.util.List;
import java.util.Random;

/**
 * Created by:
 * @author Khoi Nguyen
 * Modified by:
 * Carissa Khong
 */
public class StabAndStepAction extends Action {
    private final WeaponItem weaponItem;
    private final Actor target;
    private final int staminaDecreasePercentage;
    private final Random rand = new Random();
    private final Location targetLocation;

    public StabAndStepAction(WeaponItem weaponItem, Actor target, Location targetLocation, int staminaDecreasePercentage) {
        this.weaponItem = weaponItem;
        this.target = target;
        this.targetLocation = targetLocation;
        this.staminaDecreasePercentage = staminaDecreasePercentage;
    }

    public String execute(Actor actor, GameMap map) {
        int maxStamina = actor.getAttributeMaximum(BaseActorAttributes.STAMINA);
        int consumedAmount = this.staminaDecreasePercentage * maxStamina / 100;

        boolean isStaminaEnough = actor.getAttribute(BaseActorAttributes.STAMINA) >= consumedAmount;

        try {
            if (!(isStaminaEnough)) {
                throw new Exception();
            }

            actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, consumedAmount);

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

    @Override
    public String menuDescription(Actor actor) {
        return actor + " stabs " + target +
                " with " + this.weaponItem + " and steps away!";
    }
}
