package game.weathers;

import java.util.ArrayList;

public class SunnyWeather extends Weather{

    public void notifyObservers(){
        ArrayList<AncientWoodEntity> observers = super.getAncientWoodEntities();
        for (AncientWoodEntity observer : observers){
            observer.sunnyUpdate();
        }
    }
}
