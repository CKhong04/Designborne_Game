package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.DeathAction;
import game.enums.Status;

/**
 * Class representing the Player.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Laura Zhakupova
 */
public class Player extends Actor {
    /**
     * Stamina recovery rate is 1% per turn
     */
    private int staminaRecoveryRate = 1;

    /**
     * Constructor.
     *
     * @param name Name to call the player in the UI.
     * @param displayChar Character to represent the player in the UI.
     * @param hitPoints Player's starting number of hit points.
     * @param staminaPoints Player's starting number of stamina.
     */
    public Player(String name, char displayChar, int hitPoints, int staminaPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addAttribute(BaseActorAttributes.STAMINA, new BaseActorAttribute(staminaPoints));
    }

    /**
     * Create a String to display player's name, current and maximum HP, current and maximum stamina.
     * @return a string with player's stats
     */
    public String displayStatistics(){
        return this.name +
                "\nHP: " + this.getAttribute(BaseActorAttributes.HEALTH) + "/" + this.getAttributeMaximum(BaseActorAttributes.HEALTH) +
                "\nStamina: " + this.getAttribute(BaseActorAttributes.STAMINA) + "/" + this.getAttributeMaximum(BaseActorAttributes.STAMINA);
    }

    /**
     * Add certain amount to the current stamina if it's lower than maximum stamina.
     */
    public void recoverStamina(){
        int currentStamina = this.getAttribute(BaseActorAttributes.STAMINA);
        int maxStamina = this.getAttributeMaximum(BaseActorAttributes.STAMINA);
        if (currentStamina < maxStamina){
            int recoverPercentage = staminaRecoveryRate * maxStamina / 100;
            this.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE,recoverPercentage);
        }
    }

    /**
     * Initiate a new intrinsic weapon "Limbs" for the player.
     *
     * @return player's intrinsic weapon.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(15, "bonks",80);
    }

    /**
     * Select and return an action to perform on the current turn if the player is conscious.
     *
     * @param actions collection of possible Actions for this Actor
     * @param lastAction the Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map the map containing the Actor
     * @param display the I/O object to which messages may be written
     * @return an action to be executed.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (!this.isConscious()){
            return new DeathAction();
        } else {
            recoverStamina();

            // Displaying player's statistics
            display.println(displayStatistics());

            // Handle multi-turn Actions
            if (lastAction.getNextAction() != null)
                return lastAction.getNextAction();

            // return/print the console menu
            Menu menu = new Menu(actions);
            return menu.showMenu(this, display);
        }
    }

    @Override
    public String toString() {
        String output = name + " health (" +
                this.getAttribute(BaseActorAttributes.HEALTH) + "/" +
                this.getAttributeMaximum(BaseActorAttributes.HEALTH) +
                ") ";

        output += "stamina (" +
                this.getAttribute(BaseActorAttributes.STAMINA) + "/" +
                this.getAttributeMaximum(BaseActorAttributes.STAMINA) +
                ")";

        return output;
    }
}
