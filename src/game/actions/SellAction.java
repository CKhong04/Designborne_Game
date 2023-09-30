package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.tradableitems.Sellable;

/**
 * Class representing the sell action.
 * Created by:
 * @author Laura Zhakupova
 */
public class SellAction extends Action {
    // Private attributes
    private final Actor trader;
    private final Sellable item;
    private final int sellPrice;

    /**
     * A constructor which accepts a trader and an item.
     *
     * @param trader actor who buys the item.
     * @param item which is sold.
     */
    public SellAction(Actor trader, Sellable item, int sellPrice){
        this.trader = trader;
        this.item = item;
        this.sellPrice = sellPrice;
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
        int price = this.item.sold(actor, this.trader);
        return menuDescription(actor) + " for " + price + " runes";
    }

    /**
     * Description of the action to the console menu.
     *
     * @param actor The actor performing the action.
     * @return a string that the items can be sold.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " sells " + this.item;
    }
}
