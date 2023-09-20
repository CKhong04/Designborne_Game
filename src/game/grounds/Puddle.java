package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.DrinkAction;
import game.enums.Status;

/**
 * A class that represents a puddle.
 * Created by:
 * @author
 * Modified by:
 */
public class Puddle extends Ground {
    public Puddle() {
        super('~');
    }

    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = super.allowableActions(actor, location, direction);
        Actor currActor = location.getActor();
        if (currActor != null && currActor.hasCapability(Status.DRINK_WATER)) {
            actions.add(new DrinkAction());
        }
        return actions;
    }
}

