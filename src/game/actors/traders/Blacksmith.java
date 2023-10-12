package game.actors.traders;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.Ability;

public class Blacksmith extends Trader {
    /**
     * A constructor which accepts name and a display character.
     *
     * @param name        Name to call the enemy in the UI.
     * @param displayChar Character to represent the enemy in the UI.
     */
    public Blacksmith(String name, char displayChar) {
        super(name, displayChar);
        this.addCapability(Ability.CAN_UPGRADE_ITEM);    }
}
