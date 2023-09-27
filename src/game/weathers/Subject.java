package game.weathers;

import java.util.ArrayList;
public interface Subject {
    ArrayList<AncientWoodEntity> ancientWoodEntities = new ArrayList<>();

    public void registerSubject(AncientWoodEntity object);

    public void unregisterSubject(AncientWoodEntity object);

    public void notifyObservers();




}
