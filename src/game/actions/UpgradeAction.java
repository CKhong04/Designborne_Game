package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.itemproperties.Upgradeable;

public class UpgradeAction extends Action {

    private final Upgradeable item;
    private final int upgradePrice;

    /**
     * The constructor of the UpgradeAction function
     *
     * @param item Determine which item is being upgraded
     * @param upgradePrice Determine the price of the upgrade
     *
     *
     */
    public UpgradeAction(Upgradeable item, int upgradePrice){
        this.item = item;
        this.upgradePrice = upgradePrice;
    }

    /**
     * The execute function takes in an actor and a map, and upgrades the item if the actor has enough runes.
     *
     * @param actor Determine the name of the actor performing
     * @param map the map in which the player is
     *
     * @return A string

     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.getBalance() < this.upgradePrice){
            return actor + " cannot upgrade " + this.item + ", not enough runes";
        } else {
            this.item.upgrade(actor);
            return menuDescription(actor);
        }
    }

    /**
     * The menuDescription function returns a string that is the description of the upgradeAction.
     *
     * @param actor Determine the player's inventory
     *
     * @return A string that describes the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Upgrade " + this.item + " for " + upgradePrice + " runes";
    }
}
