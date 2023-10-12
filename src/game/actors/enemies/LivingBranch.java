package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.enums.Ability;
import game.enums.Status;
import game.items.BloodBerry;
import game.items.Rune;
import game.utilities.Utility;

public class LivingBranch extends Enemy{

    private static final int DAMAGE = 250;
    private static final int HIT_POINTS = 75;
    private static final int DROP_BLOODBERRY_CHANCE = 50;
    private static final int DROP_RUNES_CHANCE = 100;

    /**
     * A constructor which accepts name, display character and hit points.
     * An enemy cannot move through a Floor in the maps, therefore, an Ability is added preventing this from happening.
     */
    public LivingBranch() {
        super("Living Branch", '?', HIT_POINTS);
        Utility.addItemByChance(this, DROP_BLOODBERRY_CHANCE, new BloodBerry());
        Utility.addItemByChance(this, DROP_RUNES_CHANCE, new Rune(500));
        this.addCapability(Ability.NOT_HURT_BY_VOID);
        this.addCapability(Status.UNABLE_TO_MOVE);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return super.findAction(map);
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        int hitRate = 90;
        String verb = "pokes";

        return new IntrinsicWeapon(DAMAGE, verb, hitRate);
    }
}
