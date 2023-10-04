package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.itemproperties.Buyable;

/**
 * Class representing the buy action.
 * Created by:
 * @author Laura Zhakupova
 */
public class BuyAction extends Action {
    // Private attributes
    private Actor trader;
    private Buyable item;
    private final int buyPrice;
    private int scamChance = 0;

    /**
     * A constructor which accepts a seller and an item.
     *
     * @param trader actor who sells the item.
     * @param item which is sold.
     */

    public BuyAction(Actor trader, Buyable item, int buyPrice){
        this.trader = trader;
        this.item = item;
        this.buyPrice = buyPrice;
    }

    public BuyAction(Actor trader, Buyable item, int buyPrice, int scamChance){
        this.trader = trader;
        this.item = item;
        this.buyPrice = buyPrice;
        this.scamChance = scamChance;
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
        if (actor.getBalance() < this.buyPrice){
            return actor + " cannot buy " + this.item + ", not enough runes";
        } else {
            int price = this.item.bought(actor, this.trader, this.buyPrice, this.scamChance);
            return menuDescription(actor) + " for " + price + " runes";
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
        return actor + " buys " + this.item;
    }
}
