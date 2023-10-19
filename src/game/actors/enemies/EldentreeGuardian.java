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

public class EldentreeGuardian extends Enemy implements MoveCapable, FollowCapable {

    private static final int DAMAGE = 50;
    private static final int HIT_POINTS = 250;
    private static final int DROP_VIAL_CHANCE = 25;
    private static final int DROP_FLASK_CHANCE = 15;
    private static final int DROP_RUNES_CHANCE = 100;

    /**
     * A constructor which accepts name, display character and hit points.
     * An enemy cannot move through a Floor in the maps, therefore, an Ability is added preventing this from happening.
     */
    public EldentreeGuardian() {
        super("Eldentree Guardian", 'e', HIT_POINTS);
        Utility.addItemByChance(this, DROP_VIAL_CHANCE, new HealingVial());
        Utility.addItemByChance(this, DROP_FLASK_CHANCE, new RefreshingFlask());
        Utility.addItemByChance(this, DROP_RUNES_CHANCE, new Rune(250));
        this.addCapability(Ability.NOT_HURT_BY_VOID);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        canFollow();
        canMove();
        return findAction(map);
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        int hitRate = 80;
        String verb = "smacks";

        return new IntrinsicWeapon(DAMAGE, verb, hitRate);
    }

    @Override
    public void canFollow() {
        this.behaviours.put(0, new FollowBehaviour());
    }

    @Override
    public void canMove() {
        this.behaviours.put(2, new WanderBehaviour());
    }
}
