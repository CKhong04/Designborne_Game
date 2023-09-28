package game.utilities;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

import java.util.Random;

/**
 * Utility class
 * Contains helpful methods
 * Created by:
 * @author Laura Zhakupova
 */
public class Utility {
    /**
     * Adds item to the actor's inventory by chance
     *
     * @param actor to which inventory the item is added
     * @param chance to add the item
     * @param item to add to the inventory
     */
    public static void addItemByChance(Actor actor, int chance, Item item) {
        Random rand = new Random();
        if (rand.nextInt(100) <= chance) {
            actor.addItemToInventory(item);
        }
    }

    /**
     * Returns True if the chance is successful.
     *
     * @param chance chance
     * @return if the chance is successful or not.
     */
    public static boolean getChance(int chance){
        Random rand = new Random();
        return rand.nextInt(100) <= chance;
    }

    public static int increasePrice(int originalPrice, int chanceToIncrease, int percentageIncrease){
        Random rand = new Random();
        if (rand.nextInt(100) <= chanceToIncrease) {
            return (int)(originalPrice * (1+(percentageIncrease/100.0)));
        } else {
            return originalPrice;
        }
    }

    public static int reducePrice(int originalPrice, int chanceToDecrease, int percentageDecrease){
        Random rand = new Random();
        if (rand.nextInt(100) <= chanceToDecrease) {
            return (int)(originalPrice * (1-(percentageDecrease/100.0)));
        } else {
            return originalPrice;
        }
    }
}