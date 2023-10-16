package game.actors.traders.conversations.conditions;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Class representing a condition that checks if an actor is unconscious.
 * Created by:
 * @author Minh Nguyen
 */
public class ActorIsUnconsiousCondition implements Condition {
    /**
     * The actor to be checked.
     */
    private final Actor actor;

    /**
     * Constructor.
     * @param actor the actor to be checked.
     */
    public ActorIsUnconsiousCondition(Actor actor) {
        this.actor = actor;
    }

    /**
     * Checks if the actor is unconscious.
     * @return true if the actor is unconscious, false otherwise.
     */
    @Override
    public boolean isMet() {
        return !actor.isConscious();
    }
}
