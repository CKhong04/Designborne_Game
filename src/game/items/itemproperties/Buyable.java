package game.items.itemproperties;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Interface representing a buyable item.
 * Created by:
 * @author Laura Zhakupova
 */
public interface Buyable {
    /**
     * Performs a buy action on the item.
     *
     * @param actor player who buys an item.
     * @param trader who sells an item.
     * @param buyPrice price of the item.
     * @param scamChance chance of a trader to scam.
     */
    int bought(Actor actor, Actor trader, int buyPrice, int scamChance);
}
