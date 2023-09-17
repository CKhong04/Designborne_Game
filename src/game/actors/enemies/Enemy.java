package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actions.DeathAction;
import game.behaviours.*;
import game.enums.Ability;
import game.enums.Status;
import game.actions.AttackAction;

import java.util.HashMap;
import java.util.Map;

/**
 * Class representing an enemy abstract class.
 * Created by:
 * @author Laura Zhakupova
 */
public abstract class Enemy extends Actor {
    // Private attributes
    private Map<Integer, Behaviour> behaviours = new HashMap<>();
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
    }

    /**
     * At each turn, select a valid action to perform.
     *
     * @param map        the map containing the Actor
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */
    public Action findAction(GameMap map) {
        if (!this.isConscious()){
            return new DeathAction();
        } else {
            if (this.hasCapability(Status.RESIDENT_ANCIENT_WOODS)){
                this.behaviours.put(0, new FollowBehaviour());
            }
            this.behaviours.put(1, new AttackBehaviour());
            this.behaviours.put(2, new WanderBehaviour());
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
     * @param map        current GameMap
     * @return list of actions that can be performed by the other actor on this enemy
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(this, direction));

            if (otherActor.hasCapability(Status.EQUIPPED_WEAPON)){
                for(Item item : otherActor.getItemInventory()){
                    if (item.hasCapability(Status.EQUIPPED_WEAPON)){
                        actions.add(new AttackAction(this, direction, (Weapon) item));
                    }
                }
            }
        }
        return actions;
    }
}
