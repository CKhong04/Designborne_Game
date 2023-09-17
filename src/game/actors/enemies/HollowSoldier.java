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
 * @author Laura Zhakupova
 */
public class HollowSoldier extends Enemy {
    // Private attributes
    private final int chanceDropHealingVial = 20;
    private final int chanceDropRefreshingFlask = 30;

    private final int chanceDropRune = 100;
    /**
     * A constructor.
     * Adds an item that is dropped after death by chance.
     */
    public HollowSoldier() {
        super("Hollow Soldier", '&', 200);
        Utility.addItemByChance(this,chanceDropRune, new Rune(100));
        Utility.addItemByChance(this, chanceDropHealingVial, new HealingVial());
        Utility.addItemByChance(this, chanceDropRefreshingFlask, new RefreshingFlask());
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
        return new IntrinsicWeapon(50, "whacks");
    }



}
