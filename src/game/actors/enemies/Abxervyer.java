package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.enums.Ability;
import game.enums.Status;

public class Abxervyer extends Enemy{

    private static final int HIT_POINTS = 2000;

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

    //To be implemented.
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return null;
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
