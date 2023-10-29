package game.utilities;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actors.traders.conversations.Monologue;

import java.util.ArrayList;
import java.util.List;
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

    /**
     * The increasePrice function takes in an original price, a chance to increase the price, and a percentage increase.
     * It then randomly decides whether or not to increase the price.
     *
     * @param originalPrice Pass the original price of the item into the function
     * @param chanceToIncrease Determine the chance that the price will increase
     * @param percentageIncrease Calculate the percentage increase of the original price
     *
     * @return The original price if the random number is greater than chancetoincrease, and returns the new price (originalprice * (percentageincrease/100
     */
    public static int increasePrice(int originalPrice, int chanceToIncrease, int percentageIncrease){
        Random rand = new Random();
        if (rand.nextInt(100) <= chanceToIncrease) {
            return (int)(originalPrice * (1+(percentageIncrease/100.0)));
        } else {
            return originalPrice;
        }
    }

    /**
     * The reducePrice function takes in an original price, a chance to decrease the price, and a percentage
     * by which to decrease the price. It then returns either the originalPrice or a reducedPrice based on
     * whether or not it randomly decides to reduce it.
     *
     * @param originalPrice Store the original price of an item
     * @param chanceToDecrease Determine the chance that the price will decrease
     * @param percentageDecrease Determine the percentage of decrease
     *
     * @return The price
     *
     */
    public static int reducePrice(int originalPrice, int chanceToDecrease, int percentageDecrease){
        Random rand = new Random();
        if (rand.nextInt(100) <= chanceToDecrease) {
            return (int)(originalPrice * (1-(percentageDecrease/100.0)));
        } else {
            return originalPrice;
        }
    }

    /**
     * The getRandomMonologue function takes a list of Monologue objects and returns a random phrase from the list.
     *
     * @param monologues Pass in the list of monologues that are available to be spoken
     *
     * @return A random phrase from the list of available phrases
     *
     */
    public static String getRandomMonologue(List<Monologue> monologues){
        List<String> availablePhrases = new ArrayList<>();
        Random rand = new Random();

        for (Monologue monologue : monologues) {
            if (monologue.canBeSpoken()) {
                String phrase = monologue.getPhrase();
                availablePhrases.add(phrase);
            }
        }

        return availablePhrases.get(rand.nextInt(availablePhrases.size()));
    }
}