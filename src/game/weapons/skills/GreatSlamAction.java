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

public class GreatSlamAction extends Action {
    private final WeaponItem weaponItem;
    private final Actor target;
    private final int staminaDecreasePercentage;
    private final static float DEFAULT_DAMAGE_MULTIPLIER = 1.0f;
    private final static float NEW_DAMAGE_MULTIPLIER = 0.5f;

    public GreatSlamAction(WeaponItem weaponItem, Actor target, int staminaDecreasePercentage) {
        this.weaponItem = weaponItem;
        this.target = target;
        this.staminaDecreasePercentage = staminaDecreasePercentage;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        int maxStamina = actor.getAttributeMaximum(BaseActorAttributes.STAMINA);
        int consumedAmount = this.staminaDecreasePercentage * maxStamina / 100;

        boolean isStaminaEnough = actor.getAttribute(BaseActorAttributes.STAMINA) >= consumedAmount;

        if (isStaminaEnough) {
            actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, consumedAmount);

            // Hurt the targeted actor at full damage
            target.hurt(weaponItem.damage());

            // Hurt all surrounding actors, including the user at half damage
            Location currentLocation = map.locationOf(actor);
            List<Exit> availableExits = currentLocation.getExits();

            weaponItem.updateDamageMultiplier(NEW_DAMAGE_MULTIPLIER);
            actor.hurt(weaponItem.damage());

            for (Exit availableExit: availableExits) {
                Location destination = availableExit.getDestination();

                if (destination.containsAnActor()) {
                    Actor otherActor = destination.getActor();
                    otherActor.hurt(weaponItem.damage());
                }
            }

            weaponItem.updateDamageMultiplier(DEFAULT_DAMAGE_MULTIPLIER);

            return actor + " slams the ground with the " + this.weaponItem + "!";
        }

        return actor + " does not have enough stamina!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " slams " + this.target + " with the " + this.weaponItem + "!";
    }
}
