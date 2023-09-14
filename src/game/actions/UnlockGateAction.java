package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.Status;
import game.grounds.Gate;

/**
 * Class representing the unlock gate action.
 * Created by:
 * @author Laura Zhakupova
 */
public class UnlockGateAction extends Action {
    // Private attributes
    private Gate gate;

    /**
     * A constructor which accepts a gate.
     *
     * @param gate to be unlocked.
     */
    public UnlockGateAction(Gate gate){
        this.gate = gate;
    }

    /**
     * action of unlocking a gate if the actor has a key.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string that says if the gate was opened or closed.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.hasCapability(Status.HAS_KEY)){
            this.gate.removeCapability(Status.LOCKED_GATE);
            return "Gate is now unlocked";
        } else {
            return "Gate is locked shut.";
        }
    }

    /**
     * Description of the action for the console menu.
     *
     * @param actor The actor performing the action.
     * @return a string which tells the user that the gate can be unlocked.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " unlocks Gate";
    }
}
