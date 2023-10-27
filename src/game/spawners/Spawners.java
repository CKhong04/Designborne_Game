package game.spawners;

import game.actors.enemies.Enemy;
import game.weathers.Weather;

/**
 * This interface encompasses any Enemy Spawners, which act to implement the Factory Method. This acts as both the
 * interface and concrete class required in the Factory Method pattern.
 * Created by:
 * @author Carissa Khong
 */
public interface Spawners {

    /**
     * This method acts for any enemies which are unaffected by any weather changes. This method uses the appropriate
     * getEnemy method, which is provided in this interface, to retrieve an enemy.
     * @param enemyType The type of enemy to be created.
     * @return The enemy created using the getEnemy method linked in.
     */
    static Enemy createEnemy(String enemyType) {
        if (enemyType.equalsIgnoreCase("WANDERING UNDEAD")) {
            return new WanderingUndeadSpawner().getEnemy();
        } else if (enemyType.equalsIgnoreCase("HOLLOW SOLDIER")){
            return new HollowSoldierSpawner().getEnemy();
        } else if (enemyType.equalsIgnoreCase("LIVING BRANCH")) {
            return new LivingBranchSpawner().getEnemy();
        } else if (enemyType.equalsIgnoreCase("ELDENTREE GUARDIAN")) {
            return new GuardianSpawner().getEnemy();
        } else {return null;}
    }

    /**
     * This method is for any enemies which are affected by weather changes in the game. Uses the appropriate getEnemy
     * method below to retrieve the appropriate enemy.
     * @param enemyType The enemy that is to be returned.
     * @param weather The current state of the weather affecting the enemies.
     * @return The enemy retrieved from the getEnemy method.
     */
    static Enemy createEnemy(String enemyType, Weather weather){
        if (enemyType.equalsIgnoreCase("FOREST KEEPER")) {
            return new ForestKeeperSpawner().getEnemy(weather);
        } else if (enemyType.equalsIgnoreCase("RED WOLF")) {
            return new RedWolfSpawner().getEnemy(weather);
        } else {return null;}
    }

    /**
     * To be overriden by child classes. This is for any enemies unaffected by weather changes.
     * @return An enemy.
     */
    Enemy getEnemy();

    /**
     * To be overriden by child classes. This is for any enemies affected by weather changes.
     * @param weather The weather currently affecting the enemy to be spawned.
     * @return An enemy.
     */
    Enemy getEnemy(Weather weather);
}
