package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.items.Rune;
import game.items.consumableitems.HealingVial;
import game.items.consumableitems.RefreshingFlask;
import game.utilities.Utility;

/**
 * Class representing the Hollow Soldier.
 * Created by:
 * @author Laura Zhakupova, Ishita Gupta
 */
public class HollowSoldier extends Enemy {
    // Private attributes
    private static final int CHANCE_DROP_HEALING_VIAL = 20;
    private static final int CHANCE_DROP_REFRESHING_FLASK = 30;
    private static final int HIT_POINTS = 200;
    private static final int CHANCE_DROP_RUNE = 100;
    /**
     * A constructor.
     * Adds an item that is dropped after death by chance.
     */
    public HollowSoldier() {
        super("Hollow Soldier", '&', HIT_POINTS);
        Utility.addItemByChance(this, CHANCE_DROP_RUNE, new Rune(100));
        Utility.addItemByChance(this, CHANCE_DROP_HEALING_VIAL, new HealingVial());
        Utility.addItemByChance(this, CHANCE_DROP_REFRESHING_FLASK, new RefreshingFlask());
    }

    /**
     * Executes an allowable action every turn
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return an action to be executed by this actor.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return super.findAction(map);
    }

    /**
     * Initiate a new intrinsic weapon.
     *
     * @return intrinsic weapon.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        int damage = 50;
        String verb = "whacks";
        return new IntrinsicWeapon(damage, verb);
    }



}
