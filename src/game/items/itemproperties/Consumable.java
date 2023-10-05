package game.items.itemproperties;

import edu.monash.fit2099.engine.actors.Actor;

public interface Consumable {

    void consumeItem(Actor actor);

    String toString();

}
