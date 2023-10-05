package game.weapons.skills;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Interface that represents the GreatSlamCapable.
 */
public interface GreatSlamCapable {
    /**
     * This method returns the GreatSlamAction.
     * @return the GreatSlamAction.
     */
    GreatSlamAction getGreatSlamAction(Actor otherActor, Location targetLocation);
}
