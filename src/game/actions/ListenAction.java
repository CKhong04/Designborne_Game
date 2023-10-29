package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.traders.conversations.Talkable;

public class ListenAction extends Action {
    private final Talkable talkableEntity;

    /**
     * The constructor for the ListenAction class
     *
     * @param talkableEntity Set the talkableentity variable in the ListenAction class
     */
    public ListenAction(Talkable talkableEntity) {
        this.talkableEntity = talkableEntity;
    }

    /**
     * The execute function for the ListenAction class.
     *
     * @param actor who is listening to the talkableentity
     * @param map Retrieve the current location of the actor
     *
     * @return A string that is the result of the talkableentity's talked function
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return talkableEntity + " says: " + talkableEntity.talked();
    }

    /**
     * The menuDescription function is a function that returns the description of the action.
     *
     * @param actor Determine if the actor is able to perform this action
     *
     * @return The description of the menu option for this action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " listens to " + talkableEntity + "'s monologue.";
    }
}
