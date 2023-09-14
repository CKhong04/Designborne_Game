package game.grounds.spawners;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.npcs.enemies.WanderingUndead;

/**
 * Class representing a Graveyard for Wandering Undead.
 * Created by:
 * @author Laura Zhakupova
 */
public class WanderingUndeadGraveyard extends Graveyard{
    /**
     * A constructor which takes chance to spawn as a parameter.
     *
     * @param chanceToSpawn chance to spawn in percentage.
     */
    public WanderingUndeadGraveyard(int chanceToSpawn) {
        super(chanceToSpawn);
    }

    /**
     * Calls spawnEnemy every turn and passes a monster as an argument.
     *
     * @param location The location of the Ground
     */
    public void tick(Location location) {
        super.spawnEnemy(new WanderingUndead(),location);
    }
}
