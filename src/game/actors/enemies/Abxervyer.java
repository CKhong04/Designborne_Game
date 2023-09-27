package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.enums.Ability;
import game.enums.Status;
import game.weathers.RainyWeather;
import game.weathers.SunnyWeather;

public class Abxervyer extends Enemy{
    /**
     * Hit points of Abxervyer.
     */
    private static final int HIT_POINTS = 2000;
    /**
     * The count of turns.
     */
    private int count = 0;

    /**
     * The constructor is taken from the Enemy abstract class. For Abxervyer, this gives the name of the enemy, which is
     * Abxervyer, the Forest Watcher, its display character, 'Y' and its initial HP, 2000.
     * Additionally, Abxervyer is a resident of the Ancient Woods and is unable to be hurt when walking on the void.
     * Created by:
     * @author Ishita Gupta, Carissa Khong, Khoi Nguyen, Laura Zhakupova
     */
    public Abxervyer() {
        super("Abxervyer, the Forest Watcher", 'Y', HIT_POINTS);
        this.addCapability(Status.RESIDENT_ANCIENT_WOODS);
        this.addCapability(Ability.NOT_HURT_BY_VOID); //Abxervyer will not be hurt if it steps on a void.
    }

    /**
     * Executes the weather changing skills every 3 turns.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return a valid action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (count % 3 == 0 && count % 2 == 0){
            display.println("Abxervyer changes the weather to sunny...");
            new SunnyWeather().notifyObservers();
        } else if(count % 3 == 0){
            display.println("Abxervyer changes the weather to rainy...");
            new RainyWeather().notifyObservers();
        }

        count ++;
        return super.findAction(map);
    }

    /**
     * @return The Intrinsic Weapon of Abxervyer will give 80 damage, is considered to "smash" and occurs with a
     * probability of 25%.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        int damage = 80;
        int hitRate = 25;
        String verb = "smashes";
        return new IntrinsicWeapon(damage, verb, hitRate);
    }
}
