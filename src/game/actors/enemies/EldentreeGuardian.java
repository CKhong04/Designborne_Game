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
import game.enums.Ability;
import game.items.HealingVial;
import game.items.RefreshingFlask;
import game.items.Rune;
import game.utilities.Utility;

/**
 * EldentreeGuardian is an enemy encountered in the Overgrown Sanctuary. It implements MoveCapable and FollowCapable as
 * it is able to wander and follow a hostile actor. It can drop a Healing Vial, Refreshing Flask and will drop runes
 * when it dies.
 * Created by:
 * @author Carissa Khong
 */
public class EldentreeGuardian extends Enemy implements MoveCapable, FollowCapable {

    private static final int DAMAGE = 50;
    private static final int HIT_POINTS = 250;
    private static final int DROP_VIAL_CHANCE = 25;
    private static final int DROP_FLASK_CHANCE = 15;
    private static final int DROP_RUNES_CHANCE = 100;

    /**
     * The Eldentree Guardian is able to drop three different types of items. It also cannot be hurt when it walks on a
     * void.
     */
    public EldentreeGuardian() {
        super("Eldentree Guardian", 'e', HIT_POINTS);
        Utility.addItemByChance(this, DROP_VIAL_CHANCE, new HealingVial());
        Utility.addItemByChance(this, DROP_FLASK_CHANCE, new RefreshingFlask());
        Utility.addItemByChance(this, DROP_RUNES_CHANCE, new Rune(250));
        this.addCapability(Ability.NOT_HURT_BY_VOID);
    }

    /**
     * In Eldentree Guardian's playTurn method, canFollow and canMove are called, which add FollowBehaviour and
     * WanderBehaviour to the actor's behaviours list.
     * Then, the findAction method from the enemy superclass is called, where an action will be returned for the Guardian
     * to complete.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return The action that the Eldentree Guardian is taking.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        canFollow();
        canMove();
        return findAction(map);
    }

    /**
     * @return A new Intrinsic Weapon with the characteristics defined below.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        int hitRate = 80;
        String verb = "smacks";

        return new IntrinsicWeapon(DAMAGE, verb, hitRate);
    }

    /**
     * Adds Follow Behaviour to the Eldentree Guardian's behaviours HashMap.
     */
    @Override
    public void canFollow() {
        this.behaviours.put(0, new FollowBehaviour());
    }

    /**
     * Adds a WanderBehaviour to the Eldentree Guardian's behaviours HashMap.
     */
    @Override
    public void canMove() {
        this.behaviours.put(2, new WanderBehaviour());
    }
}
