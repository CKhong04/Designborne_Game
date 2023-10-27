package game.spawners;

import game.actors.enemies.Enemy;
import game.actors.enemies.HollowSoldier;
import game.weathers.Weather;

/**
 * HollowSoldierSpawner implements the Spawner interface. It is an implementation class in the Factory Method pattern.
 * This returns a new Hollow Soldier instance.
 * Created by:
 * @author Carissa Khong
 */
public class HollowSoldierSpawner implements Spawners {

    /**
     * Creates and returns a new Hollow Soldier instance.
     * @return A Hollow Soldier.
     */
    @Override
    public Enemy getEnemy() {
        return new HollowSoldier();
    }

    /**
     * Irrelevant here.
     * @param weather The weather.
     * @return null
     */
    @Override
    public Enemy getEnemy(Weather weather) {
        return null;
    }
}
