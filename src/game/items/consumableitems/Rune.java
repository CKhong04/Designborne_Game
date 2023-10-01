package game.items.consumableitems;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import game.actions.ConsumeAction;

public class Rune extends ConsumableItems {

    private final int quantity;


    /***
     * Constructor.
     * Created by:
     * @author Ishita Gupta
     */
    public Rune(int quantity) {
        super("Runes", '$');
        this.quantity = quantity;
    }

    @Override
    public void consumeItem(Actor actor) {
        actor.addBalance(this.quantity);
        actor.removeItemFromInventory(this);
    }
}

