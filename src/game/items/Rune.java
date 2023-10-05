package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;
import game.items.itemproperties.Consumable;

/**
 * Class representing an item, Rune
 * Created by:
 * @author Ishita Gupta
 */
public class Rune extends Item implements Consumable {

    private final int quantity;

    /***
     * Constructor.

     */
    public Rune(int quantity) {
        super("Runes", '$', true);
        this.quantity = quantity;
    }

    /**
     * Consumes the item and removes it from the actor's inventory.
     * Make changes to the actor's required skill
     *
     * @param actor the actor who is consuming the item
     */
    @Override
    public void consumeItem(Actor actor) {
        actor.addBalance(this.quantity);
        actor.removeItemFromInventory(this);
    }

    /**
     * List of allowable actions that the item can perform to the current actor.
     *
     * @param actor the actor that owns the item
     * @return an unmodifiable list of Actions
     */
    @Override
    public ActionList allowableActions(Actor actor) {
        ActionList actions = super.allowableActions(actor);
        actions.add(new ConsumeAction(this));
        return actions;
    }
}

