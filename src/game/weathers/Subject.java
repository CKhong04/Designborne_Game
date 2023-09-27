package game.weathers;

public interface Subject {
    public void registerSubject(AncientWoodEntity object);

    public void unregisterSubject(AncientWoodEntity object);

    public void notifyObservers();
}
