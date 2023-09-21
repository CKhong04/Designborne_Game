package game.weapons.skills;

/**
 * This interface represents weapons that are capable of activating a skill.
 * Created by:
 * @author Laura Zhakupova
 */
public interface FocusCapable {
    FocusAction getFocusAction();
    /**
     * This method checks if the skill is activated, if it is activated, method manages its effects.
     */
    void activateSkill();
}
