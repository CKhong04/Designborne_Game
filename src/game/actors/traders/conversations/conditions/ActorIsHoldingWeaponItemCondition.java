package game.actors.traders.conversations.conditions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enums.Status;

/**
 * Class representing a condition that checks if an actor is holding a weapon item.
 * Created by:
 * @author Minh Nguyen
 */
public class ActorIsHoldingWeaponItemCondition implements Condition {
    /**
     * The actor to be checked.
     */
    private final Actor actor;
    /**
     * The status of the actor.
     */
    private final Enum<Status> status;

    /**
     * Constructor.
     * @param actor the actor to be checked.
     * @param status the status of the actor.
     */
    public ActorIsHoldingWeaponItemCondition(Actor actor, Enum<Status> status) {
        this.actor = actor;
        this.status = status;
    }

    /**
     * Checks if the actor is holding a weapon item.
     * @return true if the actor is holding a weapon item, false otherwise.
     */
    @Override
    public boolean isMet() {
        return actor.hasCapability(status);
    }
}
