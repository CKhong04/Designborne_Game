package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.enums.Status;
import game.items.consumableitems.HealingVial;
import game.utilities.Utility;

/**
 * The class RedWolf is a child class of the abstract class Enemy. It is encountered in the Ancient Woods.
 * Created by:
 * @author Carissa Khong
 */
public class RedWolf extends Enemy{

    //Private attributes
    private int dropVialChance = 10;

    /**
     * The constructor of the Actor class.
     *
     * The Red Wolf is an enemy which the player can encounter in the Ancient Woods. It is spawned from the bushes and
     * has the display character 'r' and 25 hit points to start.
     * The Red Wolf can also drop a Healing Vial when they are killed by the player. The chance of this occurring is 10%.
     */
    public RedWolf() {
        super("Red Wolf", 'r', 25);
        Utility.addItemByChance(this, dropVialChance, new HealingVial());
        this.addCapability(Status.RESIDENT_ANCIENT_WOODS);
    }

    /**
     * Describes what happens during a turn if a Red Wolf is alive.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return An action which the Red Wolf takes on a particular turn.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return super.findAction(map);
    }

    /**
     * @return The Intrinsic Weapon which belongs to the Red Wolf class.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(15, "bites", 80);
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
