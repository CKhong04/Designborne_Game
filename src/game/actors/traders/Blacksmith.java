package game.actors.traders;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.traders.conversations.TalkingMaterial;
import game.actors.traders.conversations.TalkableEntity;

import java.util.ArrayList;

public class Blacksmith extends Trader implements TalkableEntity {
    public ArrayList<TalkingMaterial> talkingMaterials = new ArrayList<>();

    /**
     * A constructor which accepts name and a display character.
     *
     * @param name        Name to call the enemy in the UI.
     * @param displayChar Character to represent the enemy in the UI.
     */
    public Blacksmith(String name, char displayChar) {
        super(name, displayChar);
    }

    @Override
    public void addObserver(TalkingMaterial talkingMaterial) {
        talkingMaterials.add(talkingMaterial);
    }

    @Override
    public void removeObserver(TalkingMaterial talkingMaterial) {
        talkingMaterials.remove(talkingMaterial);
    }

    @Override
    public void notifyObservers() {
        for (TalkingMaterial talkingMaterial : talkingMaterials) {
            talkingMaterial.getPhrase();
        }
    }
}
