package game.actors.traders;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.BuyAction;
import game.actions.SellAction;
import game.items.consumableitems.HealingVile;
import game.items.consumableitems.RefreshingFlask;
import game.items.tradableitems.Sellable;

/**
 * Class representing a Traveller.
 * Created by:
 * @author Laura Zhakupova
 */
public class Traveller extends Trader {
    /**
     * The constructor of the Traveller class.
     */
    public Traveller() {
        super("Traveller", 'ඞ');
    }

    /**
     * The Traveller gives the player a choice to buy or to sell items.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions that can be performed by the other actor on this enemy
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList list = super.allowableActions(otherActor, direction, map);

        //list.add(new BuyAction(this, new HealingVile()));
        //list.add(new BuyAction(this, new RefreshingFlask()));
        //list.add(new BuyAction(this, new Broadsword(110,80,5,10,90)));

        for (Item item : otherActor.getItemInventory()){
            list.add(new SellAction(this, (Sellable) item));
        }
        return list;
    }
}
