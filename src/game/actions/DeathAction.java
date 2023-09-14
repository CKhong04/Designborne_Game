package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.utilities.FancyMessage;
import game.enums.Status;

/**
 * Class representing the death action.
 * Created by:
 * @author Laura Zhakupova
 */
public class DeathAction extends Action {
    /**
     * Kills a character if executed.
     * If the actor is playable, "you died" message is printed.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String which says that the actor died.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            for (String line : FancyMessage.YOU_DIED.split("\n")) {
                new Display().println(line);
                try {
                    Thread.sleep(200);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
        return actor.unconscious(map);
    }

    /**
     * No menu description because action cannot be chosen by the user.
     *
     * @param actor The actor performing the action.
     * @return empty string.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "";
    }
}
