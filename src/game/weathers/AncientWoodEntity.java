package game.weathers;

/**
 * AncientWoodEntity is the observer interface of the observer pattern.
 */
public interface AncientWoodEntity {
    /**
     * Update the ancient wood's entities when the weather is sunny.
     */
    void sunnyUpdate();
    /**
     * Update the ancient wood's entities when the weather is rainy.
     */
    void rainyUpdate();
}
