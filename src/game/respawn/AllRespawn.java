package game.respawn;

import game.weathers.AncientWoodEntity;

public class AllRespawn implements Respawn {

    @Override
    public void registerEntity(RespawnEntity object) {
        respawnEntities.add(object);
    }

    @Override
    public void unregisterEntity(RespawnEntity object) {
        respawnEntities.remove(object);
    }

    @Override
    public void notifyEntities() {
        for (RespawnEntity entity : respawnEntities) {
            entity.respawnUpdate();
        }
    }
}
