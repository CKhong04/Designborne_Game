package game.items.tradableitems;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Interface representing a sellable item.
 * Created by:
 * @author Laura Zhakupova
 */
public interface Sellable {
    void sold(Actor actor, Actor trader, int sellPrice);
}
