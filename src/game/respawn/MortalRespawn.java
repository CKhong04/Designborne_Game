package game.respawn;

public class MortalRespawn implements Respawn {

    @Override
    public void registerEntity(RespawnEntity object) {
        if(!respawnEntities.contains(object)){
        respawnEntities.add(object);}
    }

    @Override
    public void unregisterEntity(RespawnEntity object) {
        respawnEntities.remove(object);
    }

    @Override
    public void notifyEntities() {
        System.out.println(respawnEntities);
        for (RespawnEntity entity : respawnEntities) {
            entity.respawnUpdate();
        }
        respawnEntities.clear();

//        for (RespawnEntity entity: oneRespawnEntities){
//            entity.respawnUpdate();
//        }
    }
}
