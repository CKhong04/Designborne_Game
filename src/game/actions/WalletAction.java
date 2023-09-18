package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;

/**
 * Class representing the Wallet action.
 * @author
 * Created by: Ishita Gupta
 */
public class WalletAction extends Action {

    private final int value;
    private final Item item;

    /**
     * Constructor
     * @param item the item being added to the wallet
     * @param quantity the quantity of the item
     * Created by:
     * @author Ishita Gupta
     */
    public WalletAction(Item item, int quantity){
        this.item = item;
        this.value = quantity;
    }

    /**
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String, explaining the action completed
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addBalance(this.value);
        actor.removeItemFromInventory(this.item);
        return "Balance added to " + actor + "'s wallet";
    }

    /**
     *
     * @param actor The actor performing the action.
     * @return String, description of the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Add Runes to the wallet";
    }
}
