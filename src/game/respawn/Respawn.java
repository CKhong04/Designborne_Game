package game.respawn;

import java.util.ArrayList;

public interface Respawn {

    ArrayList<RespawnEntity> respawnEntities = new ArrayList<>();

    ArrayList<RespawnEntity> oneRespawnEntities = new ArrayList<>();

    void registerEntity(RespawnEntity object);

    void unregisterEntity(RespawnEntity object);

    void notifyEntities();
}
