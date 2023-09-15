package game.grounds.spawners;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.WanderingUndead;

/**
 * Class representing a Graveyard for Wandering Undead.
 * Created by:
 * @author Laura Zhakupova
 * Modified by:
 * @author Carissa Khong
 */
public class WanderingUndeadGraveyard extends SpawningGround {
    /**
     * A constructor for the WanderingUndeadGraveyard class. Each turn, this graveyard has a chance of spawning an
     * enemy. This chance is passed as a parameter upon instantiating. On a map, this class is always displayed as an 'n'.
     */
    public WanderingUndeadGraveyard(int chanceToSpawn) {
        super(chanceToSpawn, 'n');
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
