package game.respawn;

/**
 * This class represents all entities which will be wiped from the map when the player respawns after dying.
 * Created by:
 * @author Ishita Gupta
 * Modified by:
 * Carissa Khong
 */
public class MortalRespawn implements Respawn {

    /**
     * An entity can be registered to the list of entities that are affected during respawning. The list of entities
     * is checked to ensure it does not currently contain the object being registered.
     * @param object An entity which is a RespawnEntity.
     */
    @Override
    public void registerEntity(RespawnEntity object) {
        if(!mortalRespawnEntities.contains(object)){
        mortalRespawnEntities.add(object);}
    }

    /**
     * An entity can be unregistered from the list of entities through this method.
     * @param object An entity which is to be removed from the list.
     */
    @Override
    public void unregisterEntity(RespawnEntity object) {
        mortalRespawnEntities.remove(object);
    }

    /**
     * A method to notify any entities that respawning has occurred, and they should perform their respawn update.
     * The list of entities which are mortal is cleared.
     */
    @Override
    public void notifyEntities() {
        for (RespawnEntity entity : mortalRespawnEntities) {
            entity.respawnUpdate();
        }
        mortalRespawnEntities.clear();

    }
}
