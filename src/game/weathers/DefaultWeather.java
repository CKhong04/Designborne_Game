package game.weathers;

public class DefaultWeather implements Weather {
    @Override
    public void registerEntity(AncientWoodEntity object) {}

    @Override
    public void unregisterEntity(AncientWoodEntity object) {}

    /**
     * Update the ancient wood's entities when the weather is sunny.
     */
    @Override
    public void notifyEntities() {}
}
