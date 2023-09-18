package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Ability;

/**
 * A class that represents void.
 * Created by:
 * @author Laura Zhakupova
 * Modified by:
 * Carissa Khong
 */
public class BottomlessPit extends Ground{
    /**
     * A constructor.
     */
    public BottomlessPit() {
        super('+');
    }

    /**
     * Ground can also experience the joy of time.
     * Checks if there is an actor on this location and that the actor can be hurt by the void.
     * If yes, sets HP to 0.
     *
     * @param location The location of the Ground
     */
    public void tick(Location location) {
        if (location.containsAnActor() && !location.getActor().hasCapability(Ability.NOT_HURT_BY_VOID)){
            Actor actor = location.getActor();
            actor.hurt(actor.getAttribute(BaseActorAttributes.HEALTH));
        }
    }
}
