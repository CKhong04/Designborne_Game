package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.enums.Ability;
import game.items.BloodBerry;
import game.items.Rune;
import game.utilities.Utility;

/**
 * The LivingBranch is an enemy encountered in the Overgrown Sanctuary.
 * Created by:
 * @author Carissa Khong
 */
public class LivingBranch extends Enemy {

    private static final int DAMAGE = 250;
    private static final int HIT_POINTS = 75;
    private static final int DROP_BLOODBERRY_CHANCE = 50;
    private static final int DROP_RUNES_CHANCE = 100;

    /**
     * A constructor for the Living Branch class. It can drop Bloodberries and Runes when it is killed. Also, Living
     * Branches cannot be hurt if they spawn on a void.
     */
    public LivingBranch() {
        super("Living Branch", '?', HIT_POINTS);
        Utility.addItemByChance(this, DROP_BLOODBERRY_CHANCE, new BloodBerry());
        Utility.addItemByChance(this, DROP_RUNES_CHANCE, new Rune(500));
        this.addCapability(Ability.NOT_HURT_BY_VOID);
    }

    /**
     * @return An action called when the findAction method of the Enemy class is run.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return super.findAction(map);
    }

    /**
     * @return An Intrinsic Weapon with the characteristics given below.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        int hitRate = 90;
        String verb = "pokes";

        return new IntrinsicWeapon(DAMAGE, verb, hitRate);
    }
}
