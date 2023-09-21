package game.weathers;
import java.util.ArrayList;

public class Weather implements Subject{

    ArrayList<?> ancientWoodEntities = new ArrayList<>();
    @Override
    public void registerSubject() {
        ancientWoodEntities.add(null);
    }

    @Override
    public void unregisterSubject() {
        ancientWoodEntities.remove(null);
    }

    @Override
    public void notifyObservers() {

    }
}
