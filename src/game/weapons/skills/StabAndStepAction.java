package game.weapons.skills;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;

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

    public StabAndStepAction(WeaponItem weaponItem, Actor target, int staminaDecreasePercentage) {
        this.weaponItem = weaponItem;
        this.target = target;
        this.staminaDecreasePercentage = staminaDecreasePercentage;
    }

    public String execute(Actor actor, GameMap map) {
        if (!hasEnoughStamina(actor)) {
            return actor + " does not have enough stamina to complete the Stab and Step Skill";
        }

        decreaseStamina(actor);

        Location currentLocation = map.locationOf(actor);
        Location destination = findStepDestination(actor, currentLocation, map);

        if (destination != null) {
            map.moveActor(actor, destination);
        }

        if (missedAttack()) {
            if (destination == null) {
                return actor + " stabs but misses " + target + " and fails to step away.";
            }
            return actor + " steps away but misses " + target + ".";
        }

        target.hurt(weaponItem.damage());

        if (destination == null) {
            return actor + " stabs " + target + " but fails to step away.";
        }

        return actor + " stabs and steps away!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " stabs " + target +
                " with " + this.weaponItem + " and steps away!";
    }

    private boolean hasEnoughStamina(Actor actor) {
        int maxStamina = actor.getAttributeMaximum(BaseActorAttributes.STAMINA);
        int consumedAmount = this.staminaDecreasePercentage * maxStamina / 100;
        return actor.getAttribute(BaseActorAttributes.STAMINA) >= consumedAmount;
    }

    private void decreaseStamina(Actor actor) {
        int maxStamina = actor.getAttributeMaximum(BaseActorAttributes.STAMINA);
        int consumedAmount = this.staminaDecreasePercentage * maxStamina / 100;
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, consumedAmount);
    }

    private Location findStepDestination(Actor actor, Location currentLocation, GameMap map) {
        List<Exit> availableExits = currentLocation.getExits();
        for (Exit availableExit : availableExits) {
            Location destination = availableExit.getDestination();
            if (destination.getGround().canActorEnter(actor) && !destination.containsAnActor()) {
                return destination;
            }
        }
        return null;  // No possible places to move to
    }

    private boolean missedAttack() {
        return !(rand.nextInt(100) <= weaponItem.chanceToHit());
    }
}
