package game.grounds.spawners;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.ForestKeeper;

/**
 *The Hut class extends from the abstract class SpawningGround and has the chance of spawning a Forest Keeper on each
 * turn.
 * Created by:
 * @author Carissa Khong
 */
public class Hut extends SpawningGround {
    /**
     * Constructor.
     * An instance of the Hut class may spawn an enemy, with a chance which is passed in as a parameter when
     * instantiating a new Hut. The display symbol of a hut is 'h'.
     */
    public Hut(int chanceToSpawn) {
        super(chanceToSpawn, 'h');
    }

    /**
     * The tick method calls the spawnEnemy() method from the SpawningGround class. When called each turn, it can spawn
     * a new ForestKeeper, at the location of the Hut.
     * @param location The location of the Hut.
     */
    @Override
    public void tick(Location location) {
        super.spawnEnemy(new ForestKeeper(),location);
    }
}