package game.actors.traders;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ListenAction;
import game.actors.traders.conversations.Monologue;
import game.actors.traders.conversations.Talkable;

import java.util.List;
import game.enums.Ability;
import game.utilities.Utility;

public class Blacksmith extends Trader implements Talkable {
    /**
     * The list of monologues that the blacksmith can speak.
     */
    private final List<Monologue> monologues;

    /**
     * Constructor
     */
    public Blacksmith(List<Monologue> monologues) {
        super("Blacksmith", 'B');

        this.monologues = monologues;
        this.addCapability(Ability.CAN_UPGRADE_ITEM);
    }

    /**
     * Get the monologue to be spoken.
     * @return the monologue to be spoken.
     */
    @Override
    public String talked() {
        return Utility.getRandomMonologue(this.monologues);
    }

    /**
     * Get the list of allowable actions the blacksmith can perform.
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return the list of allowable actions the blacksmith can perform.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);

        actions.add(new ListenAction(this));

        return actions;
    }
}
