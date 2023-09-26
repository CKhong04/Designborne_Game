package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.consumableitems.ConsumableItem;

/**
 * Class representing the consume action.
 * Created by:
 * @author Laura Zhakupova
 */
public class ConsumeAction extends Action {
    // Private attributes
    private ConsumableItem item;
    private ActorAttributeOperations actorAttributeOperation;
    private BaseActorAttributes baseActorAttributes;
    private int percentageValue;

    private boolean isDiscount;

    /**
     * A constructor which accepts an operation to be done on the attribute, attribute
     * itself and the percentage to be changed from the maximum value.
     *
     * @param item which is consumed.
     * @param actorAttributeOperation operation which item makes on the actor.
     * @param baseActorAttributes attribute which is modified.
     * @param percentageValue percentage by which attribute is modified.
     */
    public ConsumeAction(ConsumableItem item, ActorAttributeOperations actorAttributeOperation, BaseActorAttributes baseActorAttributes, int percentageValue, boolean isDiscount){
        this.item = item;
        this.actorAttributeOperation = actorAttributeOperation;
        this.baseActorAttributes = baseActorAttributes;
        this.percentageValue = percentageValue;
        this.isDiscount = isDiscount;
    }

    /**
     * Modifications of the attribute are done, items is removed from the inventory.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string that the items was consumed.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        int updateValue = this.percentageValue;
        if (isDiscount){
            updateValue = actor.getAttributeMaximum(this.baseActorAttributes) * this.percentageValue / 100;
        }
        actor.modifyAttribute(this.baseActorAttributes, this.actorAttributeOperation, updateValue);
        actor.removeItemFromInventory(this.item);
        return menuDescription(actor);
    }

    /**
     * Description of the item to the console menu.
     *
     * @param actor The actor performing the action.
     * @return a string that the items can be consumed.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + this.item;
    }
}
