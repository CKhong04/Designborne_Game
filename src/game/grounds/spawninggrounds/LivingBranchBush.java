package game.grounds.spawninggrounds;

import edu.monash.fit2099.engine.positions.Location;
import game.spawners.Spawners;

public class LivingBranchBush extends SpawningGround{
    private final static int CHANCE_TO_SPAWN = 90;
    /**
     * Constructor for the Living Branch bush.
     */
    public LivingBranchBush() {
        super(CHANCE_TO_SPAWN, 'm');
    }

    /**
     * The method which spawns a new enemy, taken from the SpawningGround super class.
     * @param location The location of the Bush.
     */
    @Override
    public void tick(Location location) {
        super.spawnEnemy(Spawners.createEnemy("Living Branch"), location);
    }
}
