package net.zburak.poc.marsrover.move;

import net.zburak.poc.marsrover.surface.Coordinate;
import net.zburak.poc.marsrover.util.Compass;

/**
 * Created by buraq
 */
public class NorthMove implements Move {
    @Override
    public Move left() {
        return new WestMove();
    }

    @Override
    public Move right() {
        return new EastMove();
    }

    @Override
    public Coordinate axisAction() {
        return new Coordinate(0, 1);
    }

    @Override
    public Compass direction() {
        return Compass.N;
    }

}
