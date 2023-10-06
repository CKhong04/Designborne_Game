package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.enums.Status;

/**
 * Class representing a gate key.
 * Created by:
 * @author Laura Zhakupova
 */
public class OldKey extends Item {
    /**
     * Constructor.
     * Item has a capability HAS_KEY, which allows the user to open the gate.
     */
    public OldKey() {
        super("Old Key", '-', true);
        this.addCapability(Status.HAS_KEY);
    }
}
