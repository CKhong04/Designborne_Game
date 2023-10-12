package game.actors.traders;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.BuyAction;
import game.actions.TalkAction;
import game.actors.traders.conversations.Talkable;
import game.items.HealingVial;
import game.items.RefreshingFlask;
import game.utilities.Utility;
import game.weapons.Broadsword;
import game.weapons.GreatKnife;

import java.util.ArrayList;

/**
 * Class representing a Traveller.
 * Created by:
 * @author Laura Zhakupova
 */
public class Traveller extends Trader implements Talkable {

    // Healing Vial price
    private static final int HEALING_VIAL_BUY_PRICE = 100;

    // Refreshing Flask price
    private static final int REFRESHING_FLASK_BUY_PRICE = 75;

    // Broadsword price
    private static final int BROADSWORD_BUY_PRICE = 250;
    private static final int BROADSWORD_BUY_SCAM_CHANCE = 5;

    // Great Knife price
    private static final int GREAT_KNIFE_BUY_PRICE = 300;

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

        list.add(new BuyAction(this, new HealingVial(),HEALING_VIAL_BUY_PRICE));
        list.add(new BuyAction(this, new RefreshingFlask(),REFRESHING_FLASK_BUY_PRICE));
        list.add(new BuyAction(this, new Broadsword(),BROADSWORD_BUY_PRICE,BROADSWORD_BUY_SCAM_CHANCE));
        list.add(new BuyAction(this, new GreatKnife(),GREAT_KNIFE_BUY_PRICE));

        list.add(new TalkAction(this));
        return list;
    }

    public String talked(Actor player){
        ArrayList<String> conversationArray = new ArrayList<>();
        conversationArray.add("Of course, I will never give you up, valuable customer!");
        conversationArray.add("I promise I will never let you down with the quality of the items that I sell.");
        // other stuff

        if (!player.hasCapability(Status.DEFEATED_ABXERVYER)){
            conversationArray.add("You know the rules of this world, and so do I. Each area is ruled by a lord. Defeat the lord of this area, Abxervyer, and you may proceed to the next area.");
        }

        if (player.hasCapability(Status.DEFEATED_ABXERVYER) && (player.hasCapability(Status.HOLDS_GIANT_HAMMER))){
            conversationArray.add("Congratulations on defeating the lord of this area. I noticed you still hold on to that hammer. Why don’t you sell it to me? We've known each other for so long. I can tell you probably don’t need that weapon any longer.");
        } else if (player.hasCapability(Status.HOLDS_GIANT_HAMMER)){
            conversationArray.add("Ooh, that’s a fascinating weapon you got there. I will pay a good price for it. You wouldn't get this price from any other guy.");
        }

        return Utility.getString(conversationArray);
    }
}
