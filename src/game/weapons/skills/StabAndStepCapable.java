package game.weapons.skills;

import edu.monash.fit2099.engine.actors.Actor;

public interface StabAndStepCapable {
    StabAndStepAction getStabAndStepAction(Actor otherActor);
}
