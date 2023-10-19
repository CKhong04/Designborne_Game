package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;
import game.items.Rune;
import game.utilities.FancyMessage;

public class RespawnAction extends Action {
    private GameMap spawnMap;
    public RespawnAction(GameMap spawnMap){
        this.spawnMap = spawnMap;
    }
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
        map.removeActor(actor);
        Location deathLocation = map.locationOf(actor);
        this.spawnMap.at(27, 5).addActor(actor);
        actor.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.UPDATE,actor.getAttributeMaximum(BaseActorAttributes.HEALTH));
        actor.modifyAttribute(BaseActorAttributes.STAMINA,ActorAttributeOperations.UPDATE,actor.getAttributeMaximum(BaseActorAttributes.STAMINA));
        int numOfRunes = actor.getBalance();
        actor.deductBalance(numOfRunes);
        deathLocation.addItem(new Rune(numOfRunes));

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
