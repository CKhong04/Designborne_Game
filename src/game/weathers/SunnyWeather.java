package game.weathers;

import java.util.ArrayList;

/**
 * SunnyWeather is the concrete subject of the observer pattern.
 */
public class SunnyWeather extends Weather {
    /**
     * Update the ancient wood's entities when the weather is sunny.
     */
    public void notifyObservers(){
        ArrayList<AncientWoodEntity> observers = super.getAncientWoodEntities();
        for (AncientWoodEntity observer : observers){
            observer.sunnyUpdate();
        }
    }
}
