package game.weathers;
import java.util.ArrayList;

public abstract class Weather implements Subject {
    ArrayList<AncientWoodEntity> ancientWoodEntities = new ArrayList<>();
    @Override
    public void registerSubject(AncientWoodEntity object) {
        ancientWoodEntities.add(object);
    }

    @Override
    public void unregisterSubject(AncientWoodEntity object) {
        ancientWoodEntities.remove(object);
    }

    @Override
    public void notifyObservers() {

    }
}
