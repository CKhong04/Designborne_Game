package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;
import game.respawn.MortalRespawn;
import game.respawn.RespawnEntity;

/**
 * Class representing a gate key.
 * Created by:
 * @author Laura Zhakupova
 */
public class OldKey extends Item implements RespawnEntity{

    private static Location LOCATION;

    private MortalRespawn respawn = new MortalRespawn();
    /**
     * Constructor.
     * Item has a capability HAS_KEY, which allows the user to open the gate.
     */
    public OldKey(){
        super("Old Key", '-', true);
        this.addCapability(Status.HAS_KEY);
    }

    private boolean check = false;
    public void tick(Location currentLocation, Actor actor) {
        if (check){
            respawn.unregisterEntity(this);
        }
        check = false;

    }

    @Override
    public void tick(Location currentLocation) {
        LOCATION = currentLocation;
        if(!check){
            respawn.registerEntity(this);
            check = true;
        }
    }

    @Override
    public void respawnUpdate() {
        LOCATION.removeItem(this);
    }
}
