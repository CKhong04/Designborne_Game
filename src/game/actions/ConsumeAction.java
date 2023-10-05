package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.itemproperties.Consumable;

/**
 * Class representing the consume action.
 * Created by:
 * @author Laura Zhakupova
 * Modified by: Ishita Gupta
 */
public class ConsumeAction extends Action {
    // Private attributes
    private final Consumable item;

    /**
     * A constructor which accepts an operation to be done on the attribute, attribute
     * itself and the percentage to be changed from the maximum value.
     *
     * @param item which is consumed.
     */
    public ConsumeAction(Consumable item){
        this.item = item;
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
        this.item.consumeItem(actor);
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
        return actor + " consumes the " + this.item;
    }
}
