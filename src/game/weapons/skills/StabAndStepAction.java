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

public class StabAndStepAction extends Action {
    private final WeaponItem weaponItem;
    private final Actor otherActor;
    private final int staminaDecreasePercentage;

    public StabAndStepAction(WeaponItem weaponItem, Actor otherActor, int staminaDecreasePercentage) {
        this.weaponItem = weaponItem;
        this.otherActor = otherActor;
        this.staminaDecreasePercentage = staminaDecreasePercentage;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        int maxStamina = actor.getAttributeMaximum(BaseActorAttributes.STAMINA);
        int consumedAmount = this.staminaDecreasePercentage * maxStamina / 100;
        
        boolean isStaminaEnough = actor.getAttribute(BaseActorAttributes.STAMINA) >= consumedAmount;
        
        if (isStaminaEnough) {
            actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, consumedAmount);

            Location currentLocation = map.locationOf(actor);
            List<Exit> availableExits = currentLocation.getExits();

            for (Exit availableExit: availableExits) {
                Location destination = availableExit.getDestination();

                if (destination.getGround().canActorEnter(actor)) {
                    map.moveActor(actor, destination);
                    otherActor.hurt(weaponItem.damage());

                    return actor + " stabs and steps away!";
                }
            }
        }

        return actor + " does not have enough stamina!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " stabs " + otherActor +
                " with " + this.weaponItem + " and steps away!";
    }
}
