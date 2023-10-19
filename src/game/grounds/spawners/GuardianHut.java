package game.grounds.spawners;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.EldentreeGuardian;

public class GuardianHut extends SpawningGround{
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
        super.spawnEnemy(new EldentreeGuardian(), location);
    }
}
