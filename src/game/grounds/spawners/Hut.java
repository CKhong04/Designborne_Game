package game.grounds.spawners;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.ForestKeeper;
import game.weathers.AncientWoodEntity;
import game.weathers.Weather;

/**
 *The Hut class extends from the abstract class SpawningGround and has the chance of spawning a Forest Keeper on each
 * turn.
 * Created by:
 * @author Carissa Khong
 * Modified By: Ishita Gupta, Khoi Nguyen
 */
public class Hut extends SpawningGround implements AncientWoodEntity {
    //Private attributes
    private static final int NORMAL_CHANCE_TO_SPAWN = 15;
    private static final double SUNNY_SPAWNING_CHANCE = NORMAL_CHANCE_TO_SPAWN * 2;
    private final Display display = new Display();
    private final Weather weather;

    /**
     * Constructor.
     * An instance of the Hut class may spawn an enemy, with a chance of 15% each turn. The display symbol of a hut is 'h'.
     */
    public Hut(Weather weather) {
        super(NORMAL_CHANCE_TO_SPAWN, 'h');

        this.weather = weather;
        this.weather.registerEntity(this);
    }

    /**
     * The tick method calls the spawnEnemy() method from the SpawningGround class. When called each turn, it can spawn
     * a new ForestKeeper, at the location of the Hut.
     * @param location The location of the Hut.
     */
    @Override
    public void tick(Location location) {
        ForestKeeper forestKeeper = new ForestKeeper(weather);

        super.spawnEnemy(forestKeeper, location);

        if (location.containsAnActor()) {
            weather.registerEntity(forestKeeper);
        }
    }

    /**
     * Update the ancient wood's entities when the weather is sunny. It will increase the chance of spawning for Huts
     */
    @Override
    public void sunnyUpdate() {
        this.updateChanceToSpawn(SUNNY_SPAWNING_CHANCE);
        display.println("Huts are more likely to spawn Forest Keepers in sunny weather.");
    }
    /**
     * Update the ancient wood's entities when the weather is rainy.It will decrease the chance of spawning for Huts
     */
    @Override
    public void rainyUpdate() {
        this.updateChanceToSpawn(NORMAL_CHANCE_TO_SPAWN);
        display.println("Huts are less likely to spawn Forest Keepers in rainy weather.");
    }
}