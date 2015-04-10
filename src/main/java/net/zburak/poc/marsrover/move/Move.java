package net.zburak.poc.marsrover.move;

import net.zburak.poc.marsrover.surface.Coordinate;
import net.zburak.poc.marsrover.util.Compass;

/**
 * Created by buraq
 * Defines the facing and step size in x-y axis
 */
public interface Move {
    public Move left();
    public Move right();
    public Coordinate axisAction();
    public Compass direction();
}
