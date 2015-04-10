package net.zburak.poc.marsrover.move;

import net.zburak.poc.marsrover.util.Compass;

/**
 * Created by buraq
 */
public interface MoveFactory {
    public Move determineMove(Compass direction);
}
