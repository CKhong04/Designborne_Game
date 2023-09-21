package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.tradableitems.Buyable;
import game.utilities.Utility;

/**
 * Class representing the buy action.
 * Created by:
 * @author Laura Zhakupova
 */
public class BuyAction extends Action {
    // Private attributes
    private Actor trader;
    private Buyable item;

    /**
     * A constructor which accepts a seller and an item.
     *
     * @param trader actor who sells the item.
     * @param item which is sold.
     */
    public BuyAction(Actor trader, Buyable item){
        this.trader = trader;
        this.item = item;
    }

    /**
     * If the player has enough balance, item is added to the player's inventory
     * and balance is deducted.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string that the items was sold.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.getBalance() < this.item.getBuyPrice()){
            return "You are unable to buy " + this.item;
        } else {
            if (!Utility.getChance(this.item.getBuyScamChance())){
                trader.removeItemFromInventory((Item) this.item);
                actor.addItemToInventory((Item) this.item);
            }
            actor.deductBalance(this.item.getBuyPrice());
            return menuDescription(actor);
        }
    }

    /**
     * Description of the action to the console menu.
     *
     * @param actor The actor performing the action.
     * @return a string that the items can be bought.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys " + this.item + " for " + this.item.getBuyPrice() + " runes";
    }
}
