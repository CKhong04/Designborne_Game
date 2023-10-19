package game.actors.traders;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.Ability;

/**
 * Class representing a trader abstract class.
 * Created by:
 * @author Laura Zhakupova
 */
public abstract class Trader extends Actor {
    /**
     * A constructor which accepts name and a display character.
     *
     * @param name Name to call the enemy in the UI.
     * @param displayChar Character to represent the enemy in the UI.
     */
    public Trader(String name, char displayChar) {
        super(name, displayChar, 100);
    }

    /**
     * Executes an allowable action every turn, which is DoNothingAction for all the traders.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return an action to be executed by this actor.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }
}
