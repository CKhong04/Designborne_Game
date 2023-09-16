package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.enums.Status;
import game.items.Rune;
import game.items.consumableitems.HealingVial;
import game.utilities.Utility;

/**
 * The ForestKeeper class is a child class of the Enemy class. Forest Keepers are encountered in the Ancient Woods.
 * Created by:
 * @author Carissa Khong
 */
public class ForestKeeper extends Enemy{

    //Private attributes
    private final int dropVialChance = 20;

    /**
     * A constructor which accepts name, display character and hit points.
     *
     * The Forest Keeper is an enemy which the player can encounter in the Ancient Woods. They can spawn from the huts
     * in the map. The Forest Keeper has the display character '8' and 125 hit points to start.
     * The Forest Keeper can also drop a Healing Vial when killed by the player, which has a 20% chance of occurring.
     */
    public ForestKeeper() {
        super("The Forest Keeper", '8', 125);
        Utility.addItemByChance(this, dropVialChance, new HealingVial());
        this.addItemToInventory(new Rune(22));
        this.addCapability(Status.RESIDENT_ANCIENT_WOODS);
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
        return super.findAction(map);
    }

    /**
     * @return The Intrinsic Weapon which belongs to the Forest Keeper class.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(25, "hits", 75);
    }
    /**
     * Method that can be executed when the actor is unconscious due to the action of another actor
     * @param actor the perpetrator
     * @param map where the actor fell unconscious
     * @return a string describing what happened when the actor is unconscious
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        for (int i = 0; i < this.getItemInventory().size();i++) {
            this.getItemInventory().get(i).getDropAction(this).execute(this, map);
        }
        return this + " met their demise in the hand of " + actor;
    }
}
