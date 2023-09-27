package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.actions.SellAction;
import game.enums.Ability;
import game.items.tradableitems.Buyable;
import game.items.tradableitems.Sellable;
import game.utilities.Utility;
import game.weapons.skills.StabAndStepAction;
import game.weapons.skills.StabAndStepCapable;

/**
 * Class representing a Great Knife.
 */
public class GreatKnife extends WeaponItem implements Buyable, Sellable, StabAndStepCapable {
    /**
     * The damage to this weapon.
     */
    private static final int DAMAGE = 75;
    /**
     * The hit rate of this weapon.
     */
    private static final int HIT_RATE = 70;
    /**
     * The stamina decrease percentage of this weapon.
     */
    private static final int STAMINA_DECREASE_PERCENTAGE = 25;
    /**
     * The sell price of this weapon.
     */
    private static final int SELL_PRICE = 175;
    /**
     * The sell scam chance of this weapon.
     */
    private static final int SELL_SCAM_CHANCE = 10;

    /**
     * Constructor.
     */
    public GreatKnife() {
        super("Great Knife", '>', DAMAGE, "slashes", HIT_RATE);
    }

    /**
     * Gets the StabAndStepAction of this weapon.
     * @param otherActor the actor to stab and step.
     * @return the StabAndStepAction of this weapon.
     */
    public StabAndStepAction getStabAndStepAction(Actor otherActor){
        return new StabAndStepAction(this, otherActor, STAMINA_DECREASE_PERCENTAGE);
    }

    /**
     * Gets the allowable actions of this weapon.
     * Allowing this weapon to attack another player with and without a skill
     * Allowing the actor to sell this weapon to the traders.
     *
     * @param otherActor the other actor.
     * @param location the location of the actor.
     * @return the allowable actions of this weapon.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions =  super.allowableActions(otherActor, location);
        actions.add(new AttackAction(otherActor,location.toString(),this));
        actions.add(this.getStabAndStepAction(otherActor));
        if (otherActor.hasCapability((Ability.CAN_BE_SOLD_TO))){
            actions.add(new SellAction(otherActor, this, SELL_PRICE));
        }
        return actions;
    }

    public void sold(Actor actor, Actor trader, int sellPrice){
        if (Utility.getChance(SELL_SCAM_CHANCE)){
            actor.addBalance(sellPrice);
        } else {
            if (actor.getBalance() > sellPrice) {
                actor.deductBalance(sellPrice);
                trader.addBalance(sellPrice);
            }
        }
        actor.removeItemFromInventory(this);
        trader.addItemToInventory(this);
    }

    public void bought(Actor actor, Actor trader, int buyPrice, int scamChance){
        actor.deductBalance(buyPrice);
        trader.removeItemFromInventory(this);
        actor.addItemToInventory(this);
    }
}
