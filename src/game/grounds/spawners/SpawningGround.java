package game.grounds.spawners;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;
import game.weathers.Weather;

import java.util.Random;

/**
 * Class representing an abstract ground which is able to spawn different enemies..
 * Created by:
 * @author Laura Zhakupova
 * @author Carissa Khong
 */
public abstract class SpawningGround extends Ground {
    // Attributes
    /**
     * Random number generator
     */
    private final Random rand = new Random();

    private int chanceToSpawn;

    /**
     * A constructor which takes chance to spawn as a parameter.
     *
     * @param chanceToSpawn chance to spawn in percentage.
     */
    public SpawningGround(int chanceToSpawn, char displayChar){
        super(displayChar);
        this.chanceToSpawn = chanceToSpawn;
    }

    /**
     * Method which can be used by subclasses to spawn enemy
     * depending on the chance of the spawn.
     *
     * @param enemy to be added to the map.
     * @param location where the enemy is added.
     */
    protected void spawnEnemy(Actor enemy, Location location){
        if (!location.containsAnActor()){
            if (rand.nextInt(100) <= chanceToSpawn) {
                location.addActor(enemy);
            }
        }
    }

    public void updateChanceToSpawn(double newChanceToSpawn){
        chanceToSpawn = (int) newChanceToSpawn;
    }
}
