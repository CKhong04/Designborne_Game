package game.respawn;

public class ImmortalRespawn implements Respawn {

    @Override
    public void registerEntity(RespawnEntity object) {
        oneRespawnEntities.add(object);
    }

    @Override
    public void unregisterEntity(RespawnEntity object) {
        oneRespawnEntities.remove(object);
    }

    @Override
    public void notifyEntities() {
        for (RespawnEntity entity: oneRespawnEntities){
            entity.respawnUpdate();
        }
    }
}
