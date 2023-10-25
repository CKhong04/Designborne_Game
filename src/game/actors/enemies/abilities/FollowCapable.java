package game.actors.enemies.abilities;

/**
 * FollowCapable allows any enemies who are able to follow actors with the status HOSTILE_TO_ENEMY to do so.
 * Created by:
 * @author Carissa Khong
 */
public interface FollowCapable {

    /**
     * The canFollow() method allows enemies that implement FollowCapable to add a behaviour which allows them to follow.
     */
    void canFollow();
}
