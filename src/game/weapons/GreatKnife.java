package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.actions.SellAction;
import game.actors.traders.conversations.TalkingMaterial;
import game.enums.Ability;
import game.enums.Status;
import game.items.itemproperties.Buyable;
import game.items.itemproperties.Sellable;
import game.utilities.Utility;
import game.weapons.skills.StabAndStepAction;
import game.weapons.skills.StabAndStepCapable;
import game.actors.traders.conversations.TalkableEntity;

/**
 * Class representing a Great Knife.
 * Created by:
 * @author Minh Nguyen
 */
public class GreatKnife extends WeaponItem implements Buyable, Sellable, StabAndStepCapable, TalkingMaterial {
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
    private final TalkableEntity talkableEntity;
    private boolean isHolding = false;

    /**
     * Constructor.
     */
    public GreatKnife(TalkableEntity talkableEntity) {
        super("Great Knife", '>', DAMAGE, "slashes", HIT_RATE);

        this.talkableEntity = talkableEntity;
        this.talkableEntity.addObserver(this);
    }

    /**
     * Gets the StabAndStepAction of this weapon.
     * @param otherActor the actor to stab and step.
     * @return the StabAndStepAction of this weapon.
     */
    @Override
    public StabAndStepAction getStabAndStepAction(Actor otherActor, Location targetLocation) {
        return new StabAndStepAction(this, otherActor, targetLocation, STAMINA_DECREASE_PERCENTAGE);
    }

    @Override
    public String getPhrase() {
        if (isHolding) {
            return "Hey now, that’s a weapon from a foreign land that I have not seen for so long. I can upgrade it for you if you wish.";
        }

        return null;
    }

    @Override
    public void tick(Location currentLocation) {
        isHolding = false;
        this.talkableEntity.removeObserver(this);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        isHolding = true;
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
        if (otherActor.hasCapability(Status.HOSTILE_TO_PLAYER)){
            actions.add(new AttackAction(otherActor,location.toString(),this));
            actions.add(this.getStabAndStepAction(otherActor, location));
        }
        if (otherActor.hasCapability((Ability.CAN_BE_SOLD_TO))){
            actions.add(new SellAction(otherActor, this, SELL_PRICE));
        }
        return actions;
    }

    /**
     * Performs a sell action on the item.
     * If scam is unsuccessful player gets money, trader gets a weapon.
     * Else trader gets money and a weapon.
     * Trader gets money only if player has enough runes.
     *
     * @param actor player who sell an item.
     * @param trader who buys an item.
     */
    public int sold(Actor actor, Actor trader){
        if (!Utility.getChance(SELL_SCAM_CHANCE)){
            actor.addBalance(SELL_PRICE);
        } else {
            if (actor.getBalance() > SELL_PRICE) {
                actor.deductBalance(SELL_PRICE);
                trader.addBalance(SELL_PRICE);
            }
        }
        actor.removeItemFromInventory(this);
        trader.addItemToInventory(this);
        return SELL_PRICE;
    }

    /**
     * Performs a buy action on the item.
     *
     * @param actor player who buys an item.
     * @param trader who sells an item.
     * @param buyPrice price of the item.
     * @param scamChance chance of a trader to scam.
     */
    public int bought(Actor actor, Actor trader, int buyPrice, int scamChance){
        int newPrice = Utility.increasePrice(buyPrice, 5, 200);
        actor.deductBalance(newPrice);
        trader.addBalance(newPrice);
        actor.addItemToInventory(this);
        return newPrice;
    }
}
