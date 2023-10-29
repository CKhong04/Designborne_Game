package game.items.itemproperties;

import edu.monash.fit2099.engine.actors.Actor;

public interface Upgradeable {
    /**
     * Upgrade method to be implemented by the sub classes
     * @param actor the actor performing the upgrade
     */
    void upgrade(Actor actor);
}
