package game.grounds.spawners;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.HollowSoldier;

/**
 * Class representing a Graveyard for Hollow Soldier.
 * Created by:
 * @author Laura Zhakupova
 * Modified by:
 * Carissa Khong
 */
public class HollowSoldierGraveyard extends SpawningGround {

    //Private attributes
    private static final int CHANCE_TO_SPAWN = 10;

    /**
     * A constructor for the HollowSoldierGraveyard class. The chance this has of spawning an enemy each turn is 10%.
     * It is always displayed with an 'n' on a map.
     */
    public HollowSoldierGraveyard() {
        super(CHANCE_TO_SPAWN,'n');
    }

    /**
     * Calls spawnEnemy every turn and passes a monster as an argument.
     *
     * @param location The location of the Ground
     */
    public void tick(Location location) {
        super.spawnEnemy(new HollowSoldier(),location);
    }
}
