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
 */
public class Hut extends SpawningGround implements AncientWoodEntity {

    //Private attributes
    private static final int NORMAL_CHANCE_TO_SPAWN = 15;
    private static final double SUNNY_SPAWNING_CHANCE = NORMAL_CHANCE_TO_SPAWN * 2;

    private final Display display = new Display();
    private final Weather weather = new Weather();

    /**
     * Constructor.
     * An instance of the Hut class may spawn an enemy, with a chance of 15% each turn. The display symbol of a hut is 'h'.
     */
    public Hut() {
        super(NORMAL_CHANCE_TO_SPAWN, 'h');
        weather.registerSubject(this);
    }

    /**
     * The tick method calls the spawnEnemy() method from the SpawningGround class. When called each turn, it can spawn
     * a new ForestKeeper, at the location of the Hut.
     * @param location The location of the Hut.
     */
    @Override
    public void tick(Location location) {
        ForestKeeper forestKeeper = new ForestKeeper();

        weather.registerSubject(forestKeeper);
        super.spawnEnemy(forestKeeper, location);

        if(!location.map().contains(forestKeeper)) {
            weather.unregisterSubject(forestKeeper);
        }
    }

    @Override
    public void sunnyUpdate() {
        this.updateChanceToSpawn(SUNNY_SPAWNING_CHANCE);
        display.println("Huts are more likely to spawn Forest Keepers in sunny weather.");
    }

    @Override
    public void rainyUpdate() {
        this.updateChanceToSpawn(NORMAL_CHANCE_TO_SPAWN);
        display.println("Huts are less likely to spawn Forest Keepers in sunny weather.");
    }
}