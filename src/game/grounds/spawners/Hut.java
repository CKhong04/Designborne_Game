package game.grounds.spawners;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.ForestKeeper;
import game.weathers.AncientWoodEntity;

/**
 *The Hut class extends from the abstract class SpawningGround and has the chance of spawning a Forest Keeper on each
 * turn.
 * Created by:
 * @author Carissa Khong
 */
public class Hut extends SpawningGround implements AncientWoodEntity {

    //Private attributes
    private static final int CHANCE_TO_SPAWN = 15;
    private static final double SUNNY_SPAWNING_CHANCE = 2;


    /**
     * Constructor.
     * An instance of the Hut class may spawn an enemy, with a chance of 15% each turn. The display symbol of a hut is 'h'.
     */
    public Hut() {
        super(CHANCE_TO_SPAWN, 'h');
    }

    /**
     * The tick method calls the spawnEnemy() method from the SpawningGround class. When called each turn, it can spawn
     * a new ForestKeeper, at the location of the Hut.
     * @param location The location of the Hut.
     */
    @Override
    public void tick(Location location) {
        super.spawnEnemy(new ForestKeeper(),location);
        this.updateChancetoSpawn(2);
    }

    @Override
    public void sunnyUpdate() {
        this.updateChancetoSpawn(SUNNY_SPAWNING_CHANCE);
    }
    public void rainyUpdate() {

    }
}