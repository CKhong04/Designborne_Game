package game.weathers;

import game.actors.enemies.AncientWoodEntity;

import java.util.ArrayList;

/**
 * Weather is the subject interface of the observer pattern.
 * It is responsible for registering, unregistering, and notifying the ancient wood's entities.
 */
public interface Weather {
    /**
     * The ancient wood's entities.
     */
    public ArrayList<AncientWoodEntity> ancientWoodEntities = new ArrayList<>();

    /**
     * Register the ancient wood's entities.
     * @param object the ancient wood's entities.
     */
    public void registerEntity(AncientWoodEntity object);

    /**
     * Unregister the ancient wood's entities.
     * @param object the ancient wood's entities.
     */
    public void unregisterEntity(AncientWoodEntity object);

    /**
     * Notify the ancient wood's entities.
     */
    public void notifyEntities();
}
