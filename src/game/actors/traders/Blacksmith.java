package game.actors.traders;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ListenAction;
import game.actors.traders.conversations.Monologue;
import game.actors.traders.conversations.Talkable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Blacksmith extends Trader implements Talkable {
    private final Random rand = new Random();
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
    }

    /**
     * Get the monologue to be spoken.
     * @return the monologue to be spoken.
     */
    @Override
    public String talked() {
        List<String> availablePhrases = new ArrayList<>();

        for (Monologue monologue : monologues) {
            if (monologue.canBeSpoken()) {
                String phrase = monologue.getPhrase();
                availablePhrases.add(phrase);
            }
        }

        return availablePhrases.get(rand.nextInt(availablePhrases.size()));
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
