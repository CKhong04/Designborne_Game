package game.spawners;

import game.actors.enemies.Enemy;
import game.actors.enemies.ForestKeeper;
import game.weathers.Weather;

/**
 * ForestKeeperSpawner implements the Spawner interface. It overrides the relevant getEnemy method. As Forest Keepers
 * are affected by weather changes, the weather parameter must be passed in.
 * This is an implementation class in the Factory Method pattern.
 * Created by:
 * @author Carissa Khong
 */
public class ForestKeeperSpawner implements Spawners {

    /**
     * Irrelevant here. Is not overriden.
     * @return null
     */
    @Override
    public Enemy getEnemy() {
        return null;
    }

    /**
     * This method returns a new Forest Keeper instance.
     * @param weather The weather currently affecting the enemy to be spawned.
     * @return A Forest Keeper.
     */
    @Override
    public ForestKeeper getEnemy(Weather weather) {
        return new ForestKeeper(weather);
    }
}
