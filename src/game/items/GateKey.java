package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.enums.Status;

/**
 * Class representing a gate key.
 * Created by:
 * @author Laura Zhakupova
 */
public class GateKey extends Item {
    /***
     * A constructor.
     * Item has a capability HAS_KEY, which allows the user to open the gate.
     *
     *  @param name the name of this Item
     */
    public GateKey(String name) {
        super(name, '-', true);
        this.addCapability(Status.HAS_KEY);
    }
}
