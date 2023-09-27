package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.actions.SellAction;
import game.items.tradableitems.Sellable;
import game.weapons.skills.GreatSlamAction;
import game.weapons.skills.GreatSlamCapable;

/**
 * Class representing a Giant Hammer.
 * Created by:
 * @author Minh Nguyen
 */
public class GiantHammer extends WeaponItem implements Sellable, GreatSlamCapable {
    /**
     * The damage to this weapon.
     */
    private static final int DAMAGE = 160;
    /**
     * The hit rate of this weapon.
     */
    private static final int HIT_RATE = 90;
    /**
     * The stamina decrease percentage of this weapon.
     */
    private static final int STAMINA_DECREASE_PERCENTAGE = 5;
    /**
     * The sell price of this weapon.
     */
    private static final int SELL_PRICE = 250;

    /**
     * Constructor.
     */
    public GiantHammer() {
        super("Giant Hammer", 'P', DAMAGE, "smashes", HIT_RATE);
    }

    /**
     * Get the GreatSlamAction of this weapon.
     *
     * @param otherActor the actor to be attacked.
     * @return the GreatSlamAction of this weapon.
     */
    public GreatSlamAction getGreatSlamAction(Actor otherActor) {
        return new GreatSlamAction(this, otherActor, STAMINA_DECREASE_PERCENTAGE);
    }

    /**
     * Get the allowable actions of this weapon.
     *
     * @param otherActor the actor to be attacked.
     * @param location the location of the actor.
     * @return the allowable actions of this weapon.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = super.allowableActions(otherActor, location);
        actions.add(new AttackAction(otherActor,location.toString(),this));
        actions.add(getGreatSlamAction(otherActor));
        actions.add(new SellAction(otherActor, this, SELL_PRICE));
        return actions;
    }

    public void sold(Actor actor, Actor trader, int sellPrice){
        actor.addBalance(sellPrice);
        actor.removeItemFromInventory(this);
        trader.addItemToInventory(this);
    }
}
