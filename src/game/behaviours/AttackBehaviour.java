package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;
import game.actions.AttackAction;

import java.util.ArrayList;
import java.util.Random;
/**
 * Class representing an attack behaviour for an NPC.
 * Created by:
 * @author Laura Zhakupova
 */
public class AttackBehaviour implements Behaviour {
    /**
     * Random number generator
     */
    private final Random random = new Random();

    /**
     * A factory for creating actions for the NPCs
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return an action that can be performed by the NPC
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        ArrayList<Action> actions = new ArrayList<>();

        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor()) {
                actor = destination.getActor();
                if(actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
                    actions.add(new AttackAction(actor,exit.getName()));
                }
            }
        }

        if (!actions.isEmpty()) {
            return actions.get(random.nextInt(actions.size()));
        }
        else {
            return null;
        }
    }
}
