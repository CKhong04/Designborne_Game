package game.spawners;

import game.actors.enemies.Enemy;
import game.actors.enemies.RedWolf;
import game.weathers.Weather;

/**
 * RedWolfSpawner implements the Spawners interface. It acts as an implementation class in the Factory Method pattern.
 * This returns a new Red Wolf.
 * Created by:
 * @author Carissa Khong
 */
public class RedWolfSpawner implements Spawners {

    /**
     * Irrelevant here.
     * @return null
     */
    @Override
    public Enemy getEnemy() {
        return null;
    }

    /**
     * getEnemy retrieves a new Red Wolf instance. As a Red Wolf is affected by the weather, this parameter must be
     * passed in upon instantiation.
     * @param weather The weather currently affecting the enemy to be spawned.
     * @return A Red Wolf instance.
     */
    @Override
    public Enemy getEnemy(Weather weather) {
        return new RedWolf(weather);
    }
}
