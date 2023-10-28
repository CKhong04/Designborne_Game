package game.grounds.spawninggrounds;

import edu.monash.fit2099.engine.positions.Location;
import game.spawners.Spawners;

/**
 * LivingBranchBush is a class which extends from SpawningGround. It is able to spawn Living Branch enemies with a
 * certain chance each turn.
 * Created by:
 * @author Carissa Khong
 */
public class LivingBranchBush extends SpawningGround{

    //Private attribute
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
