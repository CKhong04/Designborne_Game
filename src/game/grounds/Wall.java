package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

/**
 * A class that represents a wall.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 */
public class Wall extends Ground {
    /**
     * Constructor
     */
    public Wall() {
        super('#');
    }

    /**
     * Checks whether an actor can enter
     * @param actor the Actor to check
     * @return boolean, if an actor can enter
     */

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}
