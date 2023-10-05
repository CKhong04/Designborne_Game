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
public class GreatSlamAction extends Action {
    private final WeaponItem weaponItem;
    private final Actor target;
    private final int staminaDecreasePercentage;
    private final static float DEFAULT_DAMAGE_MULTIPLIER = 1.0f;
    private final static float NEW_DAMAGE_MULTIPLIER = 0.5f;
    private final Random rand = new Random();
    private final Location targetLocation;

    /**
     * Constructor for GreatSlamAction
     * @param weaponItem declares the weapon doing the actio
     * @param target the target being attacked
     * @param targetLocation the location of the attack
     * @param staminaDecreasePercentage the stamina being reduced
     */
    public GreatSlamAction(WeaponItem weaponItem, Actor target, Location targetLocation, int staminaDecreasePercentage) {
        this.weaponItem = weaponItem;
        this.target = target;
        this.targetLocation = targetLocation;
        this.staminaDecreasePercentage = staminaDecreasePercentage;
    }

    /**
     * Execute method for the Action. Performs a check to see whether the actor has enough stamina to execute the action.
     * Hurts the target, the actor and all enemies in the vicinity.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String with the action that took place
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        int maxStamina = actor.getAttributeMaximum(BaseActorAttributes.STAMINA);
        int consumedAmount = this.staminaDecreasePercentage * maxStamina / 100;

        boolean isStaminaEnough = actor.getAttribute(BaseActorAttributes.STAMINA) >= consumedAmount;

        try {
            if (!(isStaminaEnough)) {
                throw new Exception();
            }
                actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, consumedAmount);

                if (!(rand.nextInt(100) <= weaponItem.chanceToHit())) {
                    return actor + " slams the ground but misses " + target + ".";
                }

                StringBuilder result = new StringBuilder(new AttackAction(target, targetLocation.toString(), weaponItem).execute(actor, map));

                // Hurt all surrounding actors, including the user at half damage
                Location currentLocation = map.locationOf(actor);
                List<Exit> availableExits = currentLocation.getExits();

                weaponItem.updateDamageMultiplier(NEW_DAMAGE_MULTIPLIER);
                actor.hurt(weaponItem.damage());

                for (Exit availableExit : availableExits) {
                    Location destination = availableExit.getDestination();

                    if (destination.containsAnActor()) {
                        Actor otherActor = destination.getActor();
                        result.append("\n").append(new AttackAction(otherActor, destination.toString(), weaponItem).execute(actor, map));
                    }
                }

                weaponItem.updateDamageMultiplier(DEFAULT_DAMAGE_MULTIPLIER);

                return result.toString();
        } catch (Exception e){
            return actor + " does not have enough stamina to complete the Great Slam Skill!";
        }
    }

    /**
     * Menu Description of the Action
     * @param actor The actor performing the action.
     * @return String, with the description of the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " slams " + this.target + " with the " + this.weaponItem + "!";
    }
}
