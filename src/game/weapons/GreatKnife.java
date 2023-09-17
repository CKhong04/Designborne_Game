package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.weapons.skills.StabAndStepAction;

public class GreatKnife extends WeaponItem {
    private static final String NAME = "Great Knife";
    private static final char DISPLAY_CHAR = '>';
    private static final int DAMAGE = 75;
    private static final int HIT_RATE = 70;
    private static final String VERB = "slashes";
    private static final int STAMINA_DECREASE_PERCENTAGE = 25;

    public GreatKnife() {
        super(NAME, DISPLAY_CHAR, DAMAGE, VERB, HIT_RATE);
    }

    public StabAndStepAction getStabAndStepAction(Actor otherActor){
        return new StabAndStepAction(this, otherActor, STAMINA_DECREASE_PERCENTAGE);
    }

    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions =  super.allowableActions(otherActor, location);
        actions.add(this.getStabAndStepAction(otherActor));
        return actions;
    }
}
