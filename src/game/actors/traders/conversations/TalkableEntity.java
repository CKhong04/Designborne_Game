package game.actors.traders.conversations;

public interface TalkableEntity {
    void addTalkingMaterial(TalkingMaterial talkingMaterial);
    void removeTalkingMaterial(TalkingMaterial talkingMaterial);
    String talked();
}
