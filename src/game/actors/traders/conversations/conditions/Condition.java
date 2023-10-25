package game.actors.traders.conversations.conditions;

/**
 * An interface for conditions that can be used to determine whether a conversation option is available.
 * Created by:
 * @author Minh Nguyen
 */
public interface Condition {
    /**
     * Checks if the condition is met.
     * @return true if the condition is met, false otherwise.
     */
    boolean isMet();
}
