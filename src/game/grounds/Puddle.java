package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.enums.Status;
import game.items.itemproperties.Consumable;

/**
 * A class that represents a puddle.
 * Created by:
 * @author
 * Modified by: Ishita Gupta
 */
public class Puddle extends Ground implements Consumable {
    public Puddle() {
        super('~');
    }

    /**
     * Returns the list of items that can be done by the Actor
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a list of actions that can be done by thr actor
     */

    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = super.allowableActions(actor, location, direction);
        Actor currActor = location.getActor();
        if (currActor != null && currActor.hasCapability(Status.DRINK_WATER)) {
            actions.add(new ConsumeAction(this));
        }
        return actions;
    }
    /**
     * Consumes the item and removes it from the actor's inventory.
     * Make changes to the actor's required skill
     *
     * @param actor the actor who is consuming the item
     */
    @Override
    public void consumeItem(Actor actor) {
        int currentStamina = actor.getAttribute(BaseActorAttributes.STAMINA);
        int maxStamina = actor.getAttributeMaximum(BaseActorAttributes.STAMINA);
        int currentHP = actor.getAttribute(BaseActorAttributes.HEALTH);

        if (currentStamina < maxStamina){
            int recoverPercentage = maxStamina / 100;
            actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE,recoverPercentage);
        }
        actor.modifyAttribute(BaseActorAttributes.HEALTH,ActorAttributeOperations.UPDATE,1+currentHP);
    }

    /**
     * To String methods for the puddle class, returns the action done by the iterm
     * @return String with the item name
     */
    public String toString(){
        return "water from Puddle";
    }
}

