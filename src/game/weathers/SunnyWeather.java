package game.weathers;


/**
 * SunnyWeather is the concrete subject of the observer pattern.
 */
public class SunnyWeather implements Weather {
    /**
     * Register the ancient wood's entities.
     * @param object the ancient wood's entities.
     */
    @Override
    public void registerEntity(AncientWoodEntity object) {
        ancientWoodEntities.add(object);
    }

    /**
     * Unregister the ancient wood's entities.
     * @param object the ancient wood's entities.
     */
    @Override
    public void unregisterEntity(AncientWoodEntity object) {
        ancientWoodEntities.remove(object);
    }

    /**
     * Update the ancient wood's entities when the weather is sunny.
     */

    @Override
    public void notifyEntities() {
        for (AncientWoodEntity entity : ancientWoodEntities) {
            entity.sunnyUpdate();
        }
    }
}
