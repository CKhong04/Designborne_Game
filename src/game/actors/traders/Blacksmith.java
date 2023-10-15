package game.actors.traders;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ListenAction;
import game.actors.traders.conversations.TalkingMaterial;
import game.actors.traders.conversations.TalkableEntity;

import java.util.ArrayList;

public class Blacksmith extends Trader implements TalkableEntity {
    public ArrayList<TalkingMaterial> talkingMaterials = new ArrayList<>();

    /**
     * Constructor
     */
    public Blacksmith() {
        super("Blacksmith", 'B');
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

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);

        actions.add(new ListenAction(this));

        return actions;
    }
}
