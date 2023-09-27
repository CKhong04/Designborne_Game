package game.weathers;

/**
 * RainyWeather is the concrete subject of the observer pattern.
 */
public class RainyWeather extends Weather {
    /**
     * Update the ancient wood's entities when the weather is rainy.
     */
    public void notifyObservers() {
        for (AncientWoodEntity observer : super.getAncientWoodEntities()) {
            observer.rainyUpdate();
        }
    }
}
