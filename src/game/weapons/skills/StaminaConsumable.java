package game.weapons.skills;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Interface that represents the StaminaConsumable.
 * @author Minh Nguyen
 */
public interface StaminaConsumable {
    /**
     * This method consumes the stamina of the actor.
     * @param actor the actor that consumes the stamina.
     * @param staminaDecreasePercentage the percentage of stamina to be decreased.
     */
    public boolean consumeStamina(Actor actor, int staminaDecreasePercentage);
}
