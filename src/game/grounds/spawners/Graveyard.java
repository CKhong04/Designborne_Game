package game.grounds.spawners;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

import java.util.Random;

/**
 * Class representing an abstract Graveyard.
 * Created by:
 * @author Laura Zhakupova
 */
public abstract class Graveyard extends Ground {
    // Attributes
    /**
     * Random number generator
     */
    private Random rand = new Random();
    private int chanceToSpawn;

    /**
     * A constructor which takes chance to spawn as a parameter.
     *
     * @param chanceToSpawn chance to spawn in percentage.
     */
    public Graveyard(int chanceToSpawn){
        super('n');
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
            if (rand.nextInt(100) <= this.chanceToSpawn) {
                location.addActor(enemy);
            }
        }
    }
}
