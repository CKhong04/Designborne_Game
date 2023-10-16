package game.grounds.spawners;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.LivingBranch;

public class LivingBranchBush extends SpawningGround{
    private final static int CHANCE_TO_SPAWN = 90;
    /**
     * Constructor for the Living Branch bush.
     */
    public LivingBranchBush() {
        super(CHANCE_TO_SPAWN, 'm');
    }

    /**
     * The method which spawns a new enemy, taken from the Enemy super class.
     * @param location The location of the Bush.
     */
    @Override
    public void tick(Location location) {
        super.spawnEnemy(new LivingBranch(), location);
    }
}
