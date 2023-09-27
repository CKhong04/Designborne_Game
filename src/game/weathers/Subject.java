package game.weathers;

/**
 * Subject is the subject interface of the observer pattern.
 * It is responsible for registering, unregistering, and notifying the ancient wood's entities.
 */
public interface Subject {
    /**
     * Register the ancient wood's entities.
     * @param object the ancient wood's entities.
     */
    public void registerSubject(AncientWoodEntity object);
    /**
     * Unregister the ancient wood's entities.
     * @param object the ancient wood's entities.
     */
    public void unregisterSubject(AncientWoodEntity object);
    /**
     * Notify the ancient wood's entities.
     */
    public void notifyObservers();
}
