package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.enums.Ability;

/**
 * A class that represents the floor inside a building.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by: Laura Zhakupova
 */
public class Floor extends Ground {
    /**
     * A constructor.
     */
    public Floor() {
        super('_');
    }

    /**
     * The actor that has capability CANNOT_ACCESS_FLOOR cannot access floor.
     *
     * @param actor the Actor to check.
     * @return if actor can enter floor or not.
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return !actor.hasCapability(Ability.CANNOT_ACCESS_FLOOR);
    }
}
