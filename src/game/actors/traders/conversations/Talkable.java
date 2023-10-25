package game.actors.traders.conversations;

/**
 * An interface for entities that can be talked to.
 * Created by:
 * @author Minh Nguyen
 */
public interface Talkable {
    /**
     * Adds a talking material to the entity.
     * @return the talking material that was added.
     */
    String talked();
}
