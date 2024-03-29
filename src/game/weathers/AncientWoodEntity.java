package game.weathers;

/**
 * Interface representing an Ancient Wood Entity.
 * Created by:
 * @author Laura Zhakupova
 * Modified by: Minh Nguyen
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
