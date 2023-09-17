package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enums.Ability;
import game.weapons.skills.GreatSlamAction;


public class GiantHammer extends WeaponItem {
    private static final String name = "Giant Hammer";
    private static final char displayChar = 'P';
    private static final int damage = 160;
    private static final int hitRate = 90;
    private static final String verb = "smashes";
    private static final int staminaDecreasePercentage = 5;

    public GiantHammer() {
        super(name, displayChar, damage, verb, hitRate);
        this.addCapability(Ability.USED_AS_WEAPON);
    }

    public int getDamage() {
        return damage;
    }

    public GreatSlamAction getGreatSlamAction(Actor otherActor) {
        return new GreatSlamAction(this, otherActor, staminaDecreasePercentage);
    }

    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = super.allowableActions(otherActor, location);
        actions.add(this.getGreatSlamAction(otherActor));
        return actions;
    }
}
