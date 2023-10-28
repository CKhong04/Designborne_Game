package game.respawn;

import java.util.ArrayList;

/**
 * Respawn is the subject interface in the observer pattern introduced for all entities affected when the player respawns.
 * It is responsible for registering, unregistering and notifying entities of the player's respawn.
 */
public interface Respawn {

    /**
     * An ArrayList of Respawn Entities containing all entities that will be removed from the map during respawning.
     */
    ArrayList<RespawnEntity> mortalRespawnEntities = new ArrayList<>();

    /**
     * An ArrayList of Respawn Entities containing all entities affected but not removed during respawning.
     */
    ArrayList<RespawnEntity> immortalRespawnEntities = new ArrayList<>();

    /**
     * registerEntity registers Respawn Entities.
     * @param object A Respawn Entity
     */
    void registerEntity(RespawnEntity object);

    /**
     * unregisterEntity unregisters Respawn Entities.
     * @param object A Respawn Entity
     */
    void unregisterEntity(RespawnEntity object);

    /**
     * notifyEntities alerts entities to the respawning.
     */
    void notifyEntities();
}
