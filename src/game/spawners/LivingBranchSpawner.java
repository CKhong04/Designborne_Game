package game.spawners;

import game.actors.enemies.Enemy;
import game.actors.enemies.LivingBranch;
import game.weathers.Weather;

/**
 * LivingBranchSpawner implements the Spawners interface. It functions as an implementation class for the Factory Method
 * pattern. This class returns a new Living Branch.
 * Created by:
 * @author Carissa Khong
 */
public class LivingBranchSpawner implements Spawners {

    /**
     * getEnemy returns a new Living Branch instance.
     * @return A new Living Branch.
     */
    @Override
    public Enemy getEnemy() {
        return new LivingBranch();
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
