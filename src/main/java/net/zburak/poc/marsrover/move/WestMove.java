package net.zburak.poc.marsrover.move;

import net.zburak.poc.marsrover.surface.Coordinate;
import net.zburak.poc.marsrover.util.Compass;

/**
 * Created by buraq
 */
public class WestMove implements Move {
    @Override
    public Move left() {
        return new SouthMove();
    }

    @Override
    public Move right() {
        return new NorthMove();
    }

    @Override
    public Coordinate axisAction() {
        return new Coordinate(-1, 0);
    }

    @Override
    public Compass direction() {
        return Compass.W;
    }
}
