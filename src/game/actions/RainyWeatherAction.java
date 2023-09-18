package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class RainyWeatherAction extends Action {

    @Override
    public String execute(Actor actor, GameMap map) {

        return actor + " changes the weather to Rainy";
    }

    @Override
    public String menuDescription(Actor actor) {
        return " ";
    }
}
