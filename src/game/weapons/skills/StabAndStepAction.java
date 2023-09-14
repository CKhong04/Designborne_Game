package game.weapons.skills;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import java.util.List;

public class StabAndStepAction extends Action {
    private final StabAndStepCapable WEAPON_ITEM;
    private final int STAMINA_DECREASE_PERCENTAGE;

    public StabAndStepAction(StabAndStepCapable weaponItem, int staminaDecreasePercentage) {
        this.WEAPON_ITEM = weaponItem;
        this.STAMINA_DECREASE_PERCENTAGE = staminaDecreasePercentage;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        int maxStamina = actor.getAttributeMaximum(BaseActorAttributes.STAMINA);
        int consumedAmount = this.STAMINA_DECREASE_PERCENTAGE * maxStamina / 100;
        
        boolean isStaminaEnough = actor.getAttribute(BaseActorAttributes.STAMINA) >= consumedAmount;
        
        if (isStaminaEnough) {
            actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, consumedAmount);

            Location currentLocation = map.locationOf(actor);
            List<Exit> availableExits = currentLocation.getExits();

            Exit randomExit = availableExits.get((int) (Math.random() * availableExits.size()));
            Location destination = randomExit.getDestination();

            map.moveActor(actor, destination);

            return actor + " stabs and steps away!";
        }

        return actor + " does not have enough stamina!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " activates the skills of the " + this.WEAPON_ITEM;
    }
}
