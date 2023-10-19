package game.grounds.spawners;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

import java.util.Random;

/**
 * Class representing an abstract ground which is able to spawn different enemies.
 * Created by:
 * @author Laura Zhakupova
 * @author Carissa Khong
 * Modified by: Ishita Gupta
 */
public abstract class SpawningGround extends Ground {
    // Attributes
    /**
     * Random number generator
     */
    private final Random rand = new Random();
    private int chanceToSpawn;

    /**
     * Constructor
     *
     * @param chanceToSpawn chance to spawn in percentage.
     */
    public SpawningGround(int chanceToSpawn, char displayChar){
        super(displayChar);
        this.chanceToSpawn = chanceToSpawn;
    }

    /**
     * Method which can be used by subclasses to spawn enemy dependent on the chance of spawning.
     *
     * @param enemy to be added to the map.
     * @param location where the enemy could be spawned or spawned in its surroundings.
     */
    protected void spawnEnemy(Actor enemy, Location location){
        boolean willSpawn = rand.nextInt(100) <= chanceToSpawn;
        for (Exit exit : location.getExits()){
            Location destination = exit.getDestination();
            if (willSpawn && !(destination.containsAnActor())){
                destination.addActor(enemy);
                return; //If an enemy has been added, return
            }
        }
        if (willSpawn && !(location.containsAnActor())){
            location.addActor(enemy);
        }
    }

    /**
     * Updates the chanceToSpawn
     *
     * @param newChanceToSpawn the new chance to spawn
     */
    protected void updateChanceToSpawn(double newChanceToSpawn){
        chanceToSpawn = (int) newChanceToSpawn;
    }
}
