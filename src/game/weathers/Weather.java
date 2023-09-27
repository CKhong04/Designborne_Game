package game.weathers;
import java.util.ArrayList;

/**
 * Weather is the abstract subject of the observer pattern.
 * It is responsible for registering, unregistering, and notifying the ancient wood's entities.
 */
public class Weather implements Subject {
    /**
     * The ancient wood's entities.
     */
    ArrayList<AncientWoodEntity> ancientWoodEntities = new ArrayList<>();

    /**
     * Register the ancient wood's entities.
     * @param object the ancient wood's entities.
     */
    @Override
    public void registerSubject(AncientWoodEntity object) {
        ancientWoodEntities.add(object);
    }

    /**
     * Unregister the ancient wood's entities.
     * @param object the ancient wood's entities.
     */
    @Override
    public void unregisterSubject(AncientWoodEntity object) {
        ancientWoodEntities.remove(object);
    }

    /**
     * Notify the ancient wood's entities.
     */
    @Override
    public void notifyObservers() {}

    /**
     * Get the ancient wood's entities.
     * @return the ancient wood's entities.
     */
    public ArrayList<AncientWoodEntity> getAncientWoodEntities() {
        return ancientWoodEntities;
    }
}
