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
     * The getAction method first checks whether the actor is able to start following the target, by calling the
     * canStartFollowingTarget() method. If it can, a null action is returned.
     * If this is not fulfilled, then the targetWithinSurroundings() method is called to check the actor's surroundings
     * for the target. If the target is there, a null action is returned.
     * If not, the followTarget() method will be called, as the actor is following the target and the target is not
     * within the actor's surroundings. The action which is returned from the followTarget() method will be the overall
     * action returned for the getAction() method.
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return Either null or the result of the followTarget() method.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (canStartFollowingTarget(actor, map)){
            return null; //Regardless of whether the actor starts following, no action is returned.
        }
        else {
            if (targetWithinSurroundings(actor, map)){
                return null; //If the target is still within the actor's surroundings, no action should be returned.
            }
            return followTarget(actor, map); //
        }
    }

    /**
     * The canStartFollowingTarget() method checks whether the enemy which is trying to follow already has the
     * FOLLOWING_ACTOR status. If not, it will check if an actor which is HOSTILE_TO_ENEMY is within its surroundings,
     * in which case this status is added and the hostile actor will be marked as the target.
     * As long as the actor does not have the status FOLLOWING_ACTOR, this method will return true.
     * Otherwise, this method will return false.
     * @param actor The actor who may start following the target.
     * @param map The map the actor is currently on.
     * @return A boolean related to the presence of the FOLLOWING_ACTOR status.
     */
    private boolean canStartFollowingTarget(Actor actor, GameMap map){
        if (!actor.hasCapability(Status.FOLLOWING_ACTOR)){
            for (Exit exit : map.locationOf(actor).getExits()) {
                Location destination = exit.getDestination();
                if (destination.containsAnActor() && destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)){
                    target = destination.getActor();
                    actor.addCapability(Status.FOLLOWING_ACTOR); //Make sure the actor has this status, allowing it to follow in future
                    display.println(actor + " is now following " + target);
                    return true;
                }
            }
            return true;
        }
        return false; //If the actor has the status FOLLOWING_ACTOR, returns false
    }

    /**
     * targetWithinSurroundings() checks if the target is still within the surroundings of the actor.
     * If the target is within the surroundings, true will be returned. Otherwise, false is returned.
     * @param actor The actor who is following the target.
     * @param map The map which the actor is on.
     * @return A boolean value related to whether the target is within the actor's surroundings.
     */
    private boolean targetWithinSurroundings(Actor actor, GameMap map){
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor() && destination.getActor() == target) {
                return true; //This means the other actor has not moved and/or is still within the surroundings of the actor.
                //The following actor does not need to return a MoveAction in this case and can perform a different action.
            }
        }
        return false; //The target was not within the surroundings
    }

    /**
     * The followTarget() method first checks that both the enemy and the target are on the same map, as the actor
     * cannot follow the target to another map. However, should the target return to the map, the actor will again try
     * to move closer to the target.
     * Provided they are both on the same map, the distance between the two actors is calculated. A potential destination
     * is found and the distance from this new destination will be calculated.
     * If the new distance is less than the current distance, the actor will return a MoveActorAction to move closer to
     * the target.
     * If all the exits' distances are calculated and none can get them closer, null is returned.
     * @param actor The actor who is following the target.
     * @param map The map on which the actor is.
     * @return An Action, either a MoveActorAction to move the actor closer to the target, or null.
     */
    private Action followTarget(Actor actor, GameMap map){
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
