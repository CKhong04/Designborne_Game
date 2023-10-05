package game.weathers;


/**
 * RainyWeather is the concrete subject of the observer pattern.
 *
 * Created By:
 * @author: Khoi Nguyen
 */
public class RainyWeather implements Weather {
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
     * Update the ancient wood's entities when the weather is rainy.
     */
    @Override
    public void notifyEntities() {
        for (AncientWoodEntity entity : ancientWoodEntities) {
            entity.rainyUpdate();
        }
    }
}
