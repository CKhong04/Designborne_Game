package game.respawn;

/**
 * ImmortalRespawn represents any entities affected during a player's respawning, which remain on the map but may be
 * affected in other ways.
 * Created by:
 * @author Ishita Gupta
 * Modified by:
 * Carissa Khong
 */
public class ImmortalRespawn implements Respawn {

    /**
     * registerEntity adds an entity to the list of immortal entities.
     * @param object A RespawnEntity to be registered to the list.
     */
    @Override
    public void registerEntity(RespawnEntity object) {
        if (!(immortalRespawnEntities.contains(object))) {
            immortalRespawnEntities.add(object);
        }
    }

    /**
     * unregisterEntity removes an entity from the list of immortal entities.
     * @param object A RespawnEntity to be unregistered from the list.
     */
    @Override
    public void unregisterEntity(RespawnEntity object) {
        immortalRespawnEntities.remove(object);
    }

    /**
     * notifyEntities alerts all entities in the list that the respawning has occurred, so they can respond accordingly.
     */
    @Override
    public void notifyEntities() {
        for (RespawnEntity entity: immortalRespawnEntities){
            entity.respawnUpdate();
        }
    }
}
