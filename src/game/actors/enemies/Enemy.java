package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.DeathAction;
import game.behaviours.*;
import game.enums.Ability;
import game.enums.Status;
import game.actions.AttackAction;
import game.respawn.RespawnEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An abstract class representing an enemy.
 * Created by:
 * @author Laura Zhakupova
 * Modified by:
 * Carissa Khong, Ishita Gupta, Khoi Nguyen
 */
public abstract class Enemy extends Actor implements RespawnEntity {
    // Private attributes
    protected Map<Integer, Behaviour> behaviours = new HashMap<>();
    /**
     * A constructor which accepts name, display character and hit points.
     * An enemy cannot move through a Floor in the maps, therefore, an Ability is added preventing this from happening.
     *
     * @param name Name to call the enemy in the UI.
     * @param displayChar Character to represent the enemy in the UI.
     * @param hitPoints enemy's starting number of hit points.
     */
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Ability.CANNOT_ACCESS_FLOOR);
        this.addCapability(Status.HOSTILE_TO_PLAYER);
    }

    /**
     * At each turn, select a valid action to perform.
     *
     * @param map the map containing the Actor
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */
    public Action findAction(GameMap map) {
        if (!this.isConscious()){
            return new DeathAction();
        } else {
            this.behaviours.put(1, new AttackBehaviour());
            for (Behaviour behaviour : behaviours.values()) {
                Action action = behaviour.getAction(this, map);
                if(action != null)
                    return action;
            }
            return new DoNothingAction();
        }
    }

    /**
     * Enemies can be attacked by any actor that has the HOSTILE_TO_ENEMY capability
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map current GameMap
     * @return list of actions that can be performed by the other actor on this enemy
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

    /**
     * Method that can be executed when the actor is unconscious due to the action of another actor
     * @param actor the perpetrator
     * @param map where the actor fell unconscious
     * @return a string describing what happened when the actor is unconscious
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        List<Item> inventory = this.getItemInventory();
        Location deathLocation = map.locationOf(this);
        for(Item item : inventory){
            deathLocation.addItem(item);
        }
        return this + " met their demise at the hand of " + actor;
    }

    public void respawnUpdate(){
        this.hurt(this.getAttribute(BaseActorAttributes.HEALTH));
    }
}
