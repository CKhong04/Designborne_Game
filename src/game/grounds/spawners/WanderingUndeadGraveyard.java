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

    //Private attributes
    private static final int CHANCE_TO_SPAWN = 25;

    /**
     * Constructor
     */
    public WanderingUndeadGraveyard() {
        super(CHANCE_TO_SPAWN, 'n');
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
