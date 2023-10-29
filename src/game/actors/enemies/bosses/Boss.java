package game.actors.enemies.bosses;

import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.actors.enemies.Enemy;
import game.enums.Ability;
import game.respawn.MortalRespawn;
import game.respawn.RespawnEntity;

/**
 * A class representing all enemies which are also bosses. This class extends from the Enemy class and is abstract.
 * Created by:
 * @author Carissa Khong
 */
public abstract class Boss extends Enemy implements RespawnEntity {
    /**
     * A constructor which accepts name, display character and hit points.
     * Bosses will not disappear from the map when the player respawns and therefore are considered part of
     * ImmortalRespawn, not MortalRespawn as with all other enemies. Bosses cannot be hurt by the void.
     *
     * @param name        Name to call the enemy in the UI.
     * @param displayChar Character to represent the enemy in the UI.
     * @param hitPoints   enemy's starting number of hit points.
     */
    public Boss(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        new MortalRespawn().registerEntity(this);
        this.addCapability(Ability.NOT_HURT_BY_VOID);
    }

    /**
     * When notified that the player has respawned, the Boss's health will be reset to full.
     */
    @Override
    public void respawnUpdate() {
        this.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.UPDATE, this.getAttributeMaximum(BaseActorAttributes.HEALTH));
    }
}
