package game.actors.traders.conversations.conditions;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Class representing a condition that checks if an actor is conscious.
 * Created by:
 * @author Minh Nguyen
 */
public class ActorIsConsciousCondition implements Condition {
    /**
     * The actor to be checked.
     */
    private final Actor actor;

    /**
     * Constructor.
     * @param actor the actor to be checked.
     */
    public ActorIsConsciousCondition(Actor actor) {
        this.actor = actor;
    }

    /**
     * Checks if the actor is conscious.
     * @return true if the actor is conscious, false otherwise.
     */
    @Override
    public boolean isMet() {
        return actor.isConscious();
    }
}
