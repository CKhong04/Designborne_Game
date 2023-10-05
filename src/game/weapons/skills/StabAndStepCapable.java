package game.weapons.skills;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

/**
 * The interface for the StabAndStep Action
 */
public interface StabAndStepCapable {
    /**
     * Constructor
     * @param otherActor The target being attacked
     * @param targetLocation The location of the target
     * @return
     */
    StabAndStepAction getStabAndStepAction(Actor otherActor, Location targetLocation);
}
