package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class ListenAction extends Action {
    private final Actor target;
    public ListenAction(Actor target) {
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " listens to " + target + "'s conversation.";
    }
}
