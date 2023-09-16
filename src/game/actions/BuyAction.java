package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.npcs.traders.pricings.Pricing;
import game.utilities.Utility;

public class BuyAction extends Action {
    // Private attributes
    private Actor seller;
    private Item item;
    private int buyPrice;
    private int chanceToScam = 0;

    public BuyAction(Actor seller, Item item, int buyPrice, Pricing pricingStrategy){
        this.seller = seller;
        this.item = item;
        this.buyPrice = pricingStrategy.getPrice(buyPrice);
    }

    public BuyAction(Actor seller, Item item, int buyPrice, Pricing pricingStrategy, int chanceToScam){
        this.seller = seller;
        this.item = item;
        this.buyPrice = pricingStrategy.getPrice(buyPrice);
        this.chanceToScam = chanceToScam;
    }

    /**
     * Modifications of the attribute are done, items is removed from the inventory.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string that the items was consumed.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.getBalance() < this.buyPrice){
            return "You are unable to buy " + this.item;
        } else {
            if (!Utility.getChance(this.chanceToScam)){
                seller.removeItemFromInventory((Item) this.item);
                actor.addItemToInventory((Item) this.item);
            }
            actor.deductBalance(this.buyPrice);
            return menuDescription(actor);
        }
    }

    /**
     * Description of the item to the console menu.
     *
     * @param actor The actor performing the action.
     * @return a string that the items can be consumed.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys " + this.item + " for " + this.buyPrice + " runes";
    }
}
