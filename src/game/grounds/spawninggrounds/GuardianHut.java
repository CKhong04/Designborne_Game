package game.grounds.spawninggrounds;

import edu.monash.fit2099.engine.positions.Location;
import game.spawners.Spawners;

/**
 * GuardianHut is a class which extends from the SpawningGround class. It is able to spawn new Eldentree Guardians with
 * a certain chance each turn.
 * Created by:
 * @author Carissa Khong
 */
public class GuardianHut extends SpawningGround{

    //Private attribute
    private final static int CHANCE_TO_SPAWN = 20;

    /**
     * Constructor for the GuardianHut class.
     */
    public GuardianHut() {
        super(CHANCE_TO_SPAWN, 'h');
    }

    /**
     * Calls the spawnEnemy method of the SpawningGround class, allowing a new EldentreeGuardian to be spawned.
     * @param location The location of the Hut.
     */
    @Override
    public void tick(Location location) {
        super.spawnEnemy(Spawners.createEnemy("Eldentree Guardian"), location);
    }
}
