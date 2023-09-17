package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.tradableitems.Sellable;
import game.utilities.Utility;

/**
 * Class representing the sell action.
 * Created by:
 * @author Laura Zhakupova
 */
public class SellAction extends Action {
    // Private attributes
    private Actor trader;
    private Sellable item;

    /**
     * A constructor which accepts a trader and an item.
     *
     * @param seller actor who buys the item.
     * @param item which is sold.
     */
    public SellAction(Actor seller, Sellable item){
        this.trader = seller;
        this.item = item;
    }

    /**
     * Sells an item from the player if executed.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string that the items was sold.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (Utility.getChance(this.item.getSellScamChance())){
            actor.addBalance(this.item.getSellPrice());
        }
        actor.removeItemFromInventory((Item) this.item);

        return menuDescription(actor);
    }

    /**
     * Description of the action to the console menu.
     *
     * @param actor The actor performing the action.
     * @return a string that the items can be sold.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " sells " + this.item + " for " + this.item.getSellPrice() + " runes";
    }
}
