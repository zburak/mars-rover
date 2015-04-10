package net.zburak.poc.marsrover.move;

import net.zburak.poc.marsrover.util.Compass;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by buraq
 */
public class MoveFactoryImpl implements MoveFactory {

    private static Map<Compass, Move> directionMoveMap = new HashMap<Compass, Move>() {{
        put(Compass.E, new EastMove());
        put(Compass.W, new WestMove());
        put(Compass.S, new SouthMove());
        put(Compass.N, new NorthMove());
    }};

    @Override
    public Move determineMove(Compass direction) {
        return directionMoveMap.get(direction);
    }
}
