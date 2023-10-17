package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.itemproperties.Upgradable;

public class UpgradeAction extends Action {

    private final Upgradable item;
    private final int upgradePrice;

    public UpgradeAction(Upgradable item, int upgradePrice){
        this.item = item;
        this.upgradePrice = upgradePrice;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.getBalance() < this.upgradePrice){
            return actor + " cannot upgrade " + this.item + ", not enough runes";
        } else {
            this.item.upgrade(actor);
            return menuDescription(actor);
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Upgrade " + this.item + " for " + upgradePrice + " runes";
    }
}
