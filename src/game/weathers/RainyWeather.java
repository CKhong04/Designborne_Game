package game.weathers;

import java.util.List;

/**
 * RainyWeather is the concrete subject of the observer pattern.
 */
public class RainyWeather implements Weather {
    @Override
    public void registerEntity(AncientWoodEntity object) {
        ancientWoodEntities.add(object);
    }

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
            entity.rainyUpdate();
        }
    }
}