package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.npcs.traders.pricings.Pricing;
import game.utilities.Utility;

public class SellAction extends Action {
    // Private attributes
    private Actor seller;
    private Item item;
    private int cost;
    private int chanceToScam = 0;

    public SellAction(Actor seller, Item item, int cost, Pricing pricing){
        this.seller = seller;
        this.item = item;
        this.cost = pricing.getPrice(cost);
    }

    public SellAction(Actor seller, Item item, int cost, Pricing pricingStrategy, int chanceToScam){
        this.seller = seller;
        this.item = item;
        this.cost = pricingStrategy.getPrice(cost);
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
        if (Utility.getChance(this.chanceToScam)){
            actor.addBalance(this.cost);
        }
        actor.removeItemFromInventory(this.item);
        seller.addItemToInventory(this.item);

        return menuDescription(actor);
    }

    /**
     * Description of the item to the console menu.
     *
     * @param actor The actor performing the action.
     * @return a string that the items can be consumed.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " sells " + this.item + " for " + this.cost + " runes";
    }
}
