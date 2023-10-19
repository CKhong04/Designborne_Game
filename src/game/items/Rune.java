package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.items.itemproperties.Consumable;
import game.respawn.MortalRespawn;
import game.respawn.RespawnEntity;

/**
 * Class representing an item, Rune
 * Created by:
 * @author Ishita Gupta
 */
public class Rune extends Item implements Consumable, RespawnEntity {
    /**
     * The quantity of the item
     */
    private final int quantity;

    private static Location LOCATION;

    private MortalRespawn respawn = new MortalRespawn();

    /**
     * Constructor.
     *
     * @param quantity the quantity of the item
     */
    public Rune(int quantity){

        super("Runes", '$', true);
        this.quantity = quantity;
        respawn.registerEntity(this);
    }

    /**
     * Consumes the item and removes it from the actor's inventory.
     * Make changes to the actor's required skill
     *
     * @param actor the actor who is consuming the item
     */
    @Override
    public void consumeItem(Actor actor) {
        actor.addBalance(this.quantity);
        actor.removeItemFromInventory(this);
    }

    /**
     * List of allowable actions that the item can perform to the current actor.
     *
     * @param actor the actor that owns the item
     * @return an unmodifiable list of Actions
     */
    @Override
    public ActionList allowableActions(Actor actor) {
        ActionList actions = super.allowableActions(actor);
        actions.add(new ConsumeAction(this));
        return actions;
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        this.LOCATION = currentLocation;
    }

    @Override
    public void respawnUpdate() {
        this.LOCATION.removeItem(this);
//        respawn.unregisterEntity(this);
    }
}

