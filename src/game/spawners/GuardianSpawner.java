package game.spawners;

import game.actors.enemies.EldentreeGuardian;
import game.actors.enemies.Enemy;
import game.weathers.Weather;

/**
 * GuardianSpawner implements the Spawners interface and is used to get a new Eldentree Guardian. It is an implementation
 * class in the Factory Method pattern.
 * Created by:
 * @author Carissa Khong
 */
public class GuardianSpawner implements Spawners {

    /**
     * getEnemy returns a new Eldentree Guardian instance.
     * @return A new Eldentree Guardian.
     */
    @Override
    public Enemy getEnemy() {
        return new EldentreeGuardian();
    }

    /**
     * Irrelevant here.
     * @param weather The weather which could affect an enemy to be spawned.
     * @return null
     */
    @Override
    public Enemy getEnemy(Weather weather) {
        return null;
    }
}
