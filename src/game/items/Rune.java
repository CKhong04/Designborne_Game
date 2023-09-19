package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.WalletAction;

public class Rune extends Item {

    private final int quantity;


    /***
     * Constructor.
     * Created by:
     * @author Ishita Gupta
     */
    public Rune(int quantity) {
        super("Runes", '$', true);
        this.quantity = quantity;
    }
    /**
     * Add action to activate the weapon's skill into the allowable list of actions
     * @param owner the actor that owns the item.
     *
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actions =  super.allowableActions(owner);
        actions.add(new WalletAction(this, this.quantity));
        return actions;
    }

}

