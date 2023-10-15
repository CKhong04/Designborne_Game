package game.actors.traders;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ListenAction;
import game.actors.traders.conversations.TalkingMaterial;
import game.actors.traders.conversations.TalkableEntity;

import java.util.ArrayList;
import java.util.Random;

public class Blacksmith extends Trader implements TalkableEntity {
    private final ArrayList<TalkingMaterial> talkingMaterials = new ArrayList<>();
    private final ArrayList<String> phrases = new ArrayList<>();
    private final Random rand = new Random();

    /**
     * Constructor
     */
    public Blacksmith() {
        super("Blacksmith", 'B');
    }

    @Override
    public void addTalkingMaterial(TalkingMaterial talkingMaterial) {
        talkingMaterials.add(talkingMaterial);
    }

    @Override
    public void removeTalkingMaterial(TalkingMaterial talkingMaterial) {
        talkingMaterials.remove(talkingMaterial);
    }

    @Override
    public String talked() {
        phrases.add("I used to be an adventurer like you, but then …. Nevermind, let’s get back to smithing.");
        phrases.add("It’s dangerous to go alone. Take my creation with you on your adventure!");
        phrases.add("Ah, it’s you. Let’s get back to make your weapons stronger.");

        for (TalkingMaterial talkingMaterial : talkingMaterials) {
            phrases.add(talkingMaterial.getPhrase());
        }

        return phrases.get(rand.nextInt(phrases.size()));
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        phrases.clear();

        return super.playTurn(actions, lastAction, map, display);
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);

        actions.add(new ListenAction(this));

        return actions;
    }
}
