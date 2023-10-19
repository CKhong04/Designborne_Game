package game.grounds.spawners;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.RedWolf;
import game.weathers.AncientWoodEntity;
import game.weathers.Weather;

/**
 * The Bush class extends the SpawningGround class and has a chance of spawning Red Wolves from it each turn.
 * Created by:
 * @author Carissa Khong
 * Modified By:
 * Ishita Gupta, Khoi Nguyen
 */
public class RedWolfBush extends SpawningGround implements AncientWoodEntity {
    private static final int NORMAL_CHANCE_TO_SPAWN = 30;
    private static final double RAINY_SPAWNING_CHANCE = NORMAL_CHANCE_TO_SPAWN * 1.5;
    private final Weather weather;
    private final Display display = new Display();

    /**
     * Constructor
     * @param weather The weather of the AncientWoodMap.
     */
    public RedWolfBush(Weather weather) {
        super(NORMAL_CHANCE_TO_SPAWN,'m');

        this.weather = weather;
        this.weather.registerEntity(this);
    }

    /**
     * The tick method of the Bush class is called every turn. It will call the spawnEnemy() method which will be able
     * to spawn a new Red Wolf enemy.
     * @param location The location of the Bush.
     */
    @Override

    public void tick(Location location) {
        RedWolf redWolf = new RedWolf(weather);

        super.spawnEnemy(redWolf, location);

        if (location.containsAnActor()) {
            weather.registerEntity(redWolf);
        }
    }

    /**
     * This method is called when the weather is sunny. It will decrease the chance of spawning a Red Wolf from the Bush.
     */
    @Override
    public void sunnyUpdate() {
        this.updateChanceToSpawn(NORMAL_CHANCE_TO_SPAWN);
        display.println("Bushes are less likely to spawn Red Wolves in sunny weather.");
    }

    /**
     * This method is called when the weather is rainy. It will increase the chance of spawning a Red Wolf from the Bush.
     */
    @Override
    public void rainyUpdate(){
        this.updateChanceToSpawn(RAINY_SPAWNING_CHANCE);
        display.println("Bushes are more likely to spawn Red Wolves in rainy weather.");
    }
}
