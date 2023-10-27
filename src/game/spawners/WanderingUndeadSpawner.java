package game.spawners;

import game.actors.enemies.Enemy;
import game.actors.enemies.WanderingUndead;
import game.weathers.Weather;

/**
 * WanderingUndeadSpawner implements the Spawners interface, as it is an implementation class in the Factory Method
 * pattern. This classes' purpose is to return a new Wandering Undead instance.
 * Created by:
 * @author Carissa Khong
 */
public class WanderingUndeadSpawner implements Spawners {

    /**
     * getEnemy returns a new Wandering Undead instance.
     * @return A new Wandering Undead.
     */
    @Override
    public Enemy getEnemy() {
        return new WanderingUndead();
    }

    /**
     * Irrelevant here.
     * @param weather The weather
     * @return null
     */
    @Override
    public Enemy getEnemy(Weather weather) {
        return null;
    }
}
