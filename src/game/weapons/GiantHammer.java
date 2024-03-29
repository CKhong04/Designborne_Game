package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.actions.SellAction;
import game.enums.Ability;
import game.enums.Status;
import game.items.itemproperties.Sellable;
import game.weapons.skills.GreatSlamAction;
import game.weapons.skills.GreatSlamCapable;

/**
 * Class representing a Giant Hammer.
 * Created by:
 * @author Minh Nguyen
 * Modified by:
 * Carissa Khong, Laura Zhakupova
 */
public class GiantHammer extends WeaponItem implements Sellable, GreatSlamCapable{
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
        this.addCapability(Status.HOLDING_GIANT_HAMMER);
    }

    /**
     * Get the GreatSlamAction of this weapon.
     *
     * @param otherActor the actor to be attacked.
     * @return the GreatSlamAction of this weapon.
     */
    @Override
    public GreatSlamAction getGreatSlamAction(Actor otherActor, Location targetLocation) {
        return new GreatSlamAction(this, otherActor, targetLocation, STAMINA_DECREASE_PERCENTAGE);
    }

    /**
     * Get the allowable actions of this weapon.
     * Allowing this weapon to attack another actor.
     * Allowing the actor to sell this weapon to the traders.
     *
     * @param otherActor the other actor.
     * @param location the location of the actor.
     * @return the allowable actions of this weapon.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = super.allowableActions(otherActor, location);
        if (otherActor.hasCapability(Status.HOSTILE_TO_PLAYER)){
            actions.add(new AttackAction(otherActor,location.toString(),this));
            actions.add(getGreatSlamAction(otherActor, location));
        }
        if (otherActor.hasCapability((Ability.CAN_BE_SOLD_TO))){
            actions.add(new SellAction(otherActor, this));
        }
        return actions;
    }

    /**
     * Performs a sell action on the item.
     *
     * @param actor player who sell an item.
     * @param trader who buys an item.
     */
    public int sold(Actor actor, Actor trader){
        actor.addBalance(SELL_PRICE);
        actor.removeItemFromInventory(this);
        trader.addItemToInventory(this);
        return SELL_PRICE;
    }
}
