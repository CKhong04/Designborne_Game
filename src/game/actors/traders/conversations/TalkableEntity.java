package game.actors.traders.conversations;

public interface TalkableEntity {
    void addObserver(TalkingMaterial talkingMaterial);
    void removeObserver(TalkingMaterial talkingMaterial);
    void notifyObservers();
}
