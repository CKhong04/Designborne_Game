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
    public static void addItemByChance(Actor actor, int chance, Item item) {
        Random rand = new Random();
        if (rand.nextInt(100) <= chance) {
            actor.addItemToInventory(item);
        }
    }

    /**
     * Returns True if the chance is successful
     * @param chance
     * @return
     */
    public static boolean getChance(int chance){
        Random rand = new Random();
        if (rand.nextInt(100) <= chance) {
            return true;
        } else {
            return false;
        }
    }
}