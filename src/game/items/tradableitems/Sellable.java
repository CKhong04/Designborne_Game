package game.items.tradableitems;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Interface representing a sellable item.
 * Created by:
 * @author Laura Zhakupova
 */
public interface Sellable {
    /**
     * Performs a sell action on the item.
     *
     * @param actor player who sell an item.
     * @param trader who buys an item.
     * @param sellPrice price of the item.
     */
    void sold(Actor actor, Actor trader, int sellPrice);
}
