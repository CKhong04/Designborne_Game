package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;

public class DrinkAction extends Action {

    /**
     * A constructor which accepts an operation to be done on the attribute, attribute
     * itself and the percentage to be changed from the maximum value.
     * @param actor The actor drinking the water
     * Created by:
     * @author Ishita Gupta
     */

    @Override
    public String execute(Actor actor, GameMap map) {
        int currentStamina = actor.getAttribute(BaseActorAttributes.STAMINA);
        int maxStamina = actor.getAttributeMaximum(BaseActorAttributes.STAMINA);
        int currentHP = actor.getAttribute(BaseActorAttributes.HEALTH);

        if (currentStamina < maxStamina){
            int recoverPercentage = maxStamina / 100;
            actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE,recoverPercentage);
        }
        actor.modifyAttribute(BaseActorAttributes.HEALTH,ActorAttributeOperations.UPDATE,1+currentHP);
        return actor + " drank water!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Drink water from the Puddle";
    }
}
