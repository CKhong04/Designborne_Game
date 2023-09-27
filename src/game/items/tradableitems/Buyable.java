package game.items.tradableitems;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Interface representing a buyable item.
 * Created by:
 * @author Laura Zhakupova
 */
public interface Buyable {
    void bought(Actor actor, Actor trader, int buyPrice, int scamChance);
}
