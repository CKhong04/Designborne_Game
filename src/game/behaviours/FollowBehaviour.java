package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.displays.Display;
import game.enums.Status;

/**
 * The FollowBehaviour class is an implementation of the Behaviour interface. It allows enemies to follow the actor
 * around, until the actor does not move or until one of the enemy or actor dies.
 * Created by:
 * @author Carissa Khong
 */
public class FollowBehaviour implements Behaviour {
    //Private attributes
    private final Display display = new Display();
    private static Actor target;

    /**
     * The getAction method checks whether the enemy which is trying to follow already has the FOLLOWING_ACTOR status.
     * If not, it will check if an actor which is HOSTILE_TO_ENEMY is within its surroundings, in which case this status
     * is added and the hostile actor will be marked as the target. However, no action will be returned.
     * If the enemy is already following the target, then, it will check to make sure both the enemy and hostile actor
     * are on the same map, as they cannot follow to another map.
     * Also, a check is performed to see if the hostile actor has moved, as this would result in the enemy trying to
     * move closer to the hostile actor.
     * Otherwise, null is returned.
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return May return a MoveActorAction moving the enemy closer to the hostile actor. If not, null.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (!actor.hasCapability(Status.FOLLOWING_ACTOR)){ //We will not return an action here, regardless of whether the other actor is within the surroundings.
            for (Exit exit : map.locationOf(actor).getExits()) {
                Location destination = exit.getDestination();
                if (destination.containsAnActor() && destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)){
                    target = destination.getActor();
                    actor.addCapability(Status.FOLLOWING_ACTOR); //Make sure the actor has this status, allowing it to follow in future
                    display.println(actor + " is now following " + target);
                    return null;
                }
            }
        }
        else {
            for (Exit exit : map.locationOf(actor).getExits()) {
                Location destination = exit.getDestination();
                if (destination.containsAnActor() && destination.getActor() == target) {
                    return null; //This means the other actor has not moved and/or is still within the surroundings of the actor.
                    //The following actor does not need to return a MoveAction in this case and can perform a different action.
                }
            }
            for (Exit exit : map.locationOf(actor).getExits()){
                Location destination = exit.getDestination();
                if (destination.canActorEnter(actor) && !destination.containsAnActor()) {
                    if (!map.contains(actor) && !map.contains(target)){
                        return null; //Make sure both actors are still on the map, otherwise, they will not follow.
                    }
                    //Get the distance between the actor and other actor currently, and what this would change to if a movement were to occur.
                    int currentDistance = distance(map.locationOf(actor), map.locationOf(target));
                    int newDistance = distance(destination, map.locationOf(target));
                    if (newDistance < currentDistance) {
                        return new MoveActorAction(destination, exit.getName());
                    }
                }
            }
        }
        return null;
    }

    /**
     * Compute the Manhattan distance between two locations.
     *
     * @author Adrian Kristanto
     * @param a the first location
     * @param b the first location
     * @return the number of steps between a and b if you only move in the four cardinal directions.
     */
    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }
}
