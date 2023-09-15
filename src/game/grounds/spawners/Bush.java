package game.grounds.spawners;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.RedWolf;

/**
 * The Bush class extends the SpawningGround class and has a chance of spawning Red Wolves from it each turn.
 * Created by:
 * @author Carissa Khong
 */
public class Bush extends SpawningGround{

    /**
     * This is the constructor for the Bush class.
     *
     * Bushes have a 30% chance of spawning an enemy each turn and are displayed with the character 'm' on the map.
     */
    public Bush(int chanceToSpawn) {
        super(chanceToSpawn, 'm');
    }

    /**
     * The tick method of the Bush class is called every turn. It will call the spawnEnemy() method which will be able
     * to spawn a new Red Wolf enemy.
     * @param location The location of the Bush.
     */
    @Override
    public void tick(Location location) {
        super.spawnEnemy(new RedWolf(), location);
    }
}
