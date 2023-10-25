package game.actors.traders.conversations;

import game.actors.traders.conversations.conditions.Condition;

import java.util.List;

/**
 * Class representing a monologue.
 * Created by:
 * @author Minh Nguyen
 */
public class Monologue {
    /**
     * The phrase to be spoken.
     */
    private final String phrase;
    /**
     * The conditions that must be met for the phrase to be spoken.
     */
    private final List<Condition> conditions;

    /**
     * Constructor.
     * @param phrase the phrase to be spoken.
     */
    public Monologue(String phrase) {
        this.phrase = phrase;
        this.conditions = null;
    }

    /**
     * Constructor.
     * @param phrase the phrase to be spoken.
     * @param conditions the conditions that must be met for the phrase to be spoken.
     */
    public Monologue(String phrase, List<Condition> conditions) {
        this.phrase = phrase;
        this.conditions = conditions;
    }

    /**
     * Checks if the phrase can be spoken.
     * @return true if the phrase can be spoken, false otherwise.
     */
    public boolean canBeSpoken() {
        if (conditions != null) {
            for (Condition condition : conditions) {
                if (!condition.isMet()) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Gets the phrase.
     * @return the phrase.
     */
    public String getPhrase() {
        return phrase;
    }
}
