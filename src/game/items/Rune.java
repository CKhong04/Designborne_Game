package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.items.itemproperties.Consumable;

public class Rune extends Item implements Consumable {

    private final int quantity;


    /***
     * Constructor.
     * Created by:
     * @author Ishita Gupta
     */
    public Rune(int quantity) {
        super("Runes", '$', true);
        this.quantity = quantity;
    }

    @Override
    public void consumeItem(Actor actor) {
        actor.addBalance(this.quantity);
        actor.removeItemFromInventory(this);
    }
}

