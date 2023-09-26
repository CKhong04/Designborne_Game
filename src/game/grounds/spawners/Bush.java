package game.grounds.spawners;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.RedWolf;
import game.weathers.AncientWoodEntity;
import game.weathers.Weather;

import java.security.PrivilegedAction;

/**
 * The Bush class extends the SpawningGround class and has a chance of spawning Red Wolves from it each turn.
 * Created by:
 * @author Carissa Khong
 */
public class Bush extends SpawningGround implements AncientWoodEntity {

    //Private attributes
    private static final int CHANCE_TO_SPAWN = 30;

    private static final double SUNNY_SPAWNING_CHANCE = 0.67;

    private Weather weather = new Weather();


    /**
     * This is the constructor for the Bush class.
     *
     * Bushes have a 30% chance of spawning an enemy each turn and are displayed with the character 'm' on the map.
     */
    public Bush() {
        super(CHANCE_TO_SPAWN,'m');
        weather.registerSubject(this);

    }

    /**
     * The tick method of the Bush class is called every turn. It will call the spawnEnemy() method which will be able
     * to spawn a new Red Wolf enemy.
     * @param location The location of the Bush.
     */
    @Override

    public void tick(Location location) {
        RedWolf redWolf = new RedWolf();
        weather.registerSubject(redWolf);
        super.spawnEnemy(redWolf, location);
        if(!location.map().contains(redWolf)){
            weather.unregisterSubject(redWolf);
        }
    }

    @Override
    public void sunnyUpdate() {
        this.updateChancetoSpawn(SUNNY_SPAWNING_CHANCE);

    }

    @Override
    public void rainyUpdate(){
    }
}
