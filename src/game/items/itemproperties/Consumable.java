package game.items.itemproperties;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * The consumable interface for all consumable items
 * Created by
 * @author Ishita Gupta
 */
public interface Consumable {
    /**
     * The consumeItem that is called for all methods
     *
     * @param actor the actor who is consuming the item
     */
    void consumeItem(Actor actor);

    /**
     * To string method for the function
     * @return the String
     */
    String toString();

}
