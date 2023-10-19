package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.enemies.abilities.FollowCapable;
import game.actors.enemies.abilities.MoveCapable;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.enums.Ability;
import game.grounds.Gate;
import game.items.Rune;
import game.utilities.Utility;
import game.weathers.RainyWeather;
import game.weathers.SunnyWeather;
import game.weathers.Weather;

/**
 * The class for the Abxervyer
 * Created By:
 * @author Ishita Gupta, Carissa Khong, Minh Nguyen, Laura Zhakupova
 */
public class Abxervyer extends Enemy implements MoveCapable, FollowCapable {
    /**
     * Hit points of Abxervyer.
     */
    private static final int HIT_POINTS = 2000;
    private static final int CHANCE_DROP_RUNE = 100;
    /**
     * The count of turns.
     */
    private int count = 0;
    private Weather weather;
    private final Gate gateToWoods;

    /**
     * The constructor is taken from the Enemy abstract class. For Abxervyer, this gives the name of the enemy, which is
     * Abxervyer, the Forest Watcher, its display character, 'Y' and its initial HP, 2000.
     * Additionally, Abxervyer is a resident of the Ancient Woods and is unable to be hurt when walking on the void.
     * Abxervyer has 5000 runes in its inventory, which it will drop when killed.
     * @param gateToWoods The gate which appears when Abxervyer dies.
     */
    public Abxervyer(Gate gateToWoods, Weather sunnyWeather) {
        super("Abxervyer, the Forest Watcher", 'Y', HIT_POINTS);
        this.weather = sunnyWeather;
        this.addCapability(Ability.NOT_HURT_BY_VOID); //Abxervyer will not be hurt if it steps on a void.
        Utility.addItemByChance(this, CHANCE_DROP_RUNE, new Rune(5000));
        this.gateToWoods = gateToWoods;
    }

    /**
     * Executes the weather changing skills every 3 turns.
     * Returns an action which can occur when Abxervyer is alive, occurs once per turn.
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

            setWeather(new SunnyWeather());
            weather.notifyEntities();
        } else if(count % 3 == 0){
            display.println("Abxervyer changes the weather to rainy...");

            setWeather(new RainyWeather());
            weather.notifyEntities();
        }
        canFollow();
        canMove();

        count ++;
        return super.findAction(map);
    }

    /** 
     * This allows the weather to be set to exhibit different characteristics.
     * @param newWeather The Weather being changed to
     */
    private void setWeather(Weather newWeather) {
        this.weather = newWeather;
    }

    /**
     * This overrides the unconscious method which is located in the Enemy abstract class. The location where Abxervyer
     * stood last turns into a gate which takes the player back to the Ancient Woods. Additionally, a message is printed,
     * indicating that the boss has been defeated.
     * @param actor the perpetrator
     * @param map where the actor fell unconscious
     * @return The string which is returned by the unconscious method in the Enemy class, with additional information
     * specific to the defeat of Abxervyer.
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        //Set the location the actor died at to become a gate, which transports the player back to the Ancient Woods.
        Location deathLocation = map.locationOf(this);
        deathLocation.setGround(this.gateToWoods);
        String result =  super.unconscious(actor, map);
        result += "\nYou have successfully defeated the boss, " + this;
        result += "\nAbxervyer crumples, as a Gate is created at its feet...";
        return result;
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

    @Override
    public void canFollow() {
        this.behaviours.put(0, new FollowBehaviour());
    }

    @Override
    public void canMove() {
        this.behaviours.put(2, new WanderBehaviour());
    }
}
