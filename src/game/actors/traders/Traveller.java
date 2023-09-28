package game.actors.traders;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.BuyAction;
import game.actors.traders.pricings.IncreasedPricing;
import game.actors.traders.pricings.Pricing;
import game.actors.traders.pricings.ReducedPricing;
import game.items.consumableitems.HealingVial;
import game.items.consumableitems.RefreshingFlask;
import game.weapons.Broadsword;
import game.weapons.GreatKnife;

/**
 * Class representing a Traveller.
 * Created by:
 * @author Laura Zhakupova
 */
public class Traveller extends Trader {

    // Healing Vial price
    private static final int HEALING_VIAL_BUY_PRICE = 100;
    private static final Pricing HEALING_VIAL_BUY_PRICING = new IncreasedPricing(25, 50);

    // Refreshing Flask price
    private static final int REFRESHING_FLASK_BUY_PRICE = 75;
    private static final Pricing REFRESHING_FLASK_BUY_PRICING = new ReducedPricing(10, 20);

    // Broadsword price
    private static final int BROADSWORD_BUY_PRICE = 250;
    private static final int BROADSWORD_BUY_SCAM_CHANCE = 5;

    // Great Knife price
    private static final int GREAT_KNIFE_BUY_PRICE = 300;
    private static final Pricing GREAT_KNIFE_BUY_PRICING = new IncreasedPricing(5, 200);


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

        list.add(new BuyAction(this, new HealingVial(),HEALING_VIAL_BUY_PRICE,HEALING_VIAL_BUY_PRICING));
        list.add(new BuyAction(this, new RefreshingFlask(),REFRESHING_FLASK_BUY_PRICE,REFRESHING_FLASK_BUY_PRICING));
        list.add(new BuyAction(this, new Broadsword(),BROADSWORD_BUY_PRICE,BROADSWORD_BUY_SCAM_CHANCE));
        list.add(new BuyAction(this, new GreatKnife(),GREAT_KNIFE_BUY_PRICE,GREAT_KNIFE_BUY_PRICING));

        return list;
    }
}
