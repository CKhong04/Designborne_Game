package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.traders.conversations.Talkable;

public class TalkAction extends Action {
    private Talkable talkingActor;

    public TalkAction(Talkable talkingActor){
        this.talkingActor = talkingActor;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return this.talkingActor.talked();
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " talks to " + this.talkingActor;
    }
}