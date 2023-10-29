package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.enemies.abilities.FollowCapable;
import game.actors.enemies.abilities.MoveCapable;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.items.Rune;
import game.items.HealingVial;
import game.utilities.Utility;
import game.weathers.AncientWoodEntity;
import game.weathers.Weather;

/**
 * The ForestKeeper class is a child class of the Enemy class. Forest Keepers are encountered in the Ancient Woods.
 * The Forest Keeper is able to follow a hostile player and wander, therefore it implements FollowCapable and MoveCapable.
 * Created by:
 * @author Carissa Khong
 * Modified by: Ishita Gupta, Khoi Nguyen
 *
 */
public class ForestKeeper extends Enemy implements AncientWoodEntity, FollowCapable, MoveCapable {

    //Private attributes
    private static final int DROP_VIAL_CHANCE = 20;
    private static final int HIT_POINTS = 125;
    private static final int CHANCE_DROP_RUNE = 100;
    private final Weather weather;
    private final Display display = new Display();
    private boolean registered = false;

    /**
     * A constructor which accepts name, display character and hit points.
     * The Forest Keeper is an enemy which the player can encounter in the Ancient Woods. They can spawn from the huts
     * in the map. The Forest Keeper has the display character '8' and 125 hit points to start.
     * The Forest Keeper can also drop a Healing Vial when killed by the player, which has a 20% chance of occurring.
     */
    public ForestKeeper(Weather weather) {
        super("Forest Keeper", '8', HIT_POINTS);
        Utility.addItemByChance(this, DROP_VIAL_CHANCE, new HealingVial());
        Utility.addItemByChance(this,CHANCE_DROP_RUNE, new Rune(50));

        this.weather = weather;
    }

    /**
     * Describes what can happen when a turn is called and a Forest Keeper is alive.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return An action which can be taken by this enemy.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (!this.isConscious()) {
            weather.unregisterEntity(this);
        }
        if (!registered){
            weather.registerEntity(this);
            registered = true;
        }
        canFollow();
        canMove();
        return super.findAction(map);
    }

    /**
     * @return The Intrinsic Weapon which belongs to the Forest Keeper class.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        int damage = 25;
        int hitRate = 75;
        String verb = "hits";
        return new IntrinsicWeapon(damage, verb, hitRate);
    }
    /**
     * Update the ancient wood's entities when the weather is sunny. Does not make any change for Forest Keeper
     */
    @Override
    public void sunnyUpdate() {
    }
    /**
     * Update the ancient wood's entities when the weather is rainy. Heals the Forest Keeper by 10.
     */
    @Override
    public void rainyUpdate() {
        int healPoints = 10;
        this.heal(healPoints);
        display.println(this + " feels rejuvenated by the rain.");
    }

    /**
     * Forest Keepers are able to follow hostile actors, therefore, FollowBehaviour is added to their behaviours map.
     */
    @Override
    public void canFollow() {
        this.behaviours.put(0, new FollowBehaviour());
    }

    /**
     * Forest Keepers are able to move around, a WanderBehaviour is added to their behaviours map to allow this.
     */
    @Override
    public void canMove() {
        this.behaviours.put(2, new WanderBehaviour());
    }
}
