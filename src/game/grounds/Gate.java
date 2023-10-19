package game.grounds;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;
import game.actions.UnlockGateAction;
import game.respawn.ImmortalRespawn;
import game.respawn.MortalRespawn;
import game.respawn.RespawnEntity;

import java.util.ArrayList;

/**
 * Class representing a gate.
 * Created by:
 * @author Laura Zhakupova
 * Modified by:
 * Carissa Khong
 */
public class Gate extends Ground implements RespawnEntity {
    // Private attributes
    private ArrayList<Action> moveActions = new ArrayList<>();
    private final Display display = new Display();

    /**
     * A constructor.
     */
    public Gate(){
        super('=');
        this.addCapability(Status.LOCKED_GATE);
        new ImmortalRespawn().registerEntity(this);
    }

    /**
     * Allows adding a moving action to the gate.
     *
     * @param moveAction action which allows user to move to another map.
     */
    public void addMoveAction(Action moveAction){
        this.moveActions.add(moveAction);
    }

    /**
     * Checks whether an actor can enter
     *
     * @param actor the Actor to check
     * @return Boolean, whether an actor can enter
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return !this.hasCapability(Status.LOCKED_GATE);
    }

    /**
     * Actions that can de done with this gate
     * If gate is locked, user can unlock it.
     * If gate is unlocked, user can move to another map.
     *
     * @param actor the Actor acting.
     * @param location the current Location.
     * @param direction the direction of the Ground from the Actor.
     * @return a list of actions that can be executed with this gate.
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = new ActionList();
        //Only add an unlocking action if the gate is locked, the actor has a key and is hostile to enemies.
        if (this.hasCapability(Status.LOCKED_GATE) && actor.hasCapability(Status.HAS_KEY) && actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new UnlockGateAction(this));
        } else if (this.hasCapability(Status.LOCKED_GATE) && actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            display.println("The gate is locked. " + actor + " must have an Old Key to unlock it.");
        } else {
            for (Action moveAction : moveActions) {
                actions.add(moveAction);
            }
        }
        return actions;
    }

    /**
     * When the actor is respawned, all gates need to be locked again, meaning they must be unlocked again to use it.
     */
    @Override
    public void respawnUpdate() {
        this.addCapability(Status.LOCKED_GATE);
    }
}
