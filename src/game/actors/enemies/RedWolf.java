package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.enums.Status;
import game.items.Rune;
import game.items.HealingVial;
import game.utilities.Utility;
import game.weathers.Weather;

/**
 * The class RedWolf is a child class of the abstract class Enemy. It is encountered in the Ancient Woods.
 * Created by:
 * @author Carissa Khong
 * Modified by: Ishita Gupta
 */
public class RedWolf extends Enemy implements AncientWoodEntity {

    //Private attributes
    private static final int DROP_VIAL_CHANCE = 10;

    private static final int CHANCE_DROP_RUNE = 100;

    private static final int HIT_POINTS = 25;

    private static final int DAMAGE = 15;

    private final Display display = new Display();
    private final Weather weather;

    /**
     * The constructor of the Actor class.
     * <p>
     * The Red Wolf is an enemy which the player can encounter in the Ancient Woods. It is spawned from the bushes and
     * has the display character 'r' and 25 hit points to start.
     * The Red Wolf can also drop a Healing Vial when they are killed by the player. The chance of this occurring is 10%.
     * </p>
     */
    public RedWolf(Weather weather) {
        super("Red Wolf", 'r', HIT_POINTS);
        Utility.addItemByChance(this, CHANCE_DROP_RUNE, new Rune(25));
        Utility.addItemByChance(this, DROP_VIAL_CHANCE, new HealingVial());
        this.addCapability(Status.RESIDENT_ANCIENT_WOODS);

        this.weather = weather;
    }

    /**
     * Describes what happens during a turn if a Red Wolf is alive.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return An action which the Red Wolf takes on a particular turn.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (!this.isConscious()) {
            weather.unregisterEntity(this);
        }

        return super.findAction(map);
    }

    /**
     * @return The Intrinsic Weapon which belongs to the Red Wolf class.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon(){
        int hitRate = 80;
        String verb = "bites";

        return new IntrinsicWeapon(DAMAGE, verb, hitRate);
    }

    @Override
    public void sunnyUpdate() {
        this.updateDamageMultiplier(3 * DAMAGE);
        display.println("The Red Wolf is getting more aggressive.");
    }


    @Override
    public void rainyUpdate(){
        this.updateDamageMultiplier(DAMAGE);
        display.println("The Red Wolf returns to its normal state.");
    }
}