package game.weapons.skills;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

public interface StabAndStepCapable {
    StabAndStepAction getStabAndStepAction(Actor otherActor, Location targetLocation);
}
