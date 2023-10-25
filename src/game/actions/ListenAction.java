package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.traders.conversations.Talkable;

public class ListenAction extends Action {
    private final Talkable talkableEntity;
    public ListenAction(Talkable talkableEntity) {
        this.talkableEntity = talkableEntity;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return talkableEntity + " says: " + talkableEntity.talked();
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " listens to " + talkableEntity + "'s monologue.";
    }
}
