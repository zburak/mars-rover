package net.zburak.poc.marsrover.test.exception;

import net.zburak.poc.marsrover.MarsRover;
import net.zburak.poc.marsrover.Rover;
import net.zburak.poc.marsrover.move.NorthMove;
import net.zburak.poc.marsrover.surface.Coordinate;
import net.zburak.poc.marsrover.surface.Plateau;
import net.zburak.poc.marsrover.surface.Surface;
import net.zburak.poc.marsrover.util.MarsRoverManager;
import net.zburak.poc.marsrover.util.RoverManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by buraq
 */
@RunWith(JUnit4.class)
public class ExceptionTest {

    @Test(expected = IllegalArgumentException.class)
    public void boundCheckTest() {
        Surface plateau = new Plateau(new Coordinate(5, 5));
        Rover marsRover = new MarsRover(plateau, new Coordinate(10, 10), new NorthMove());
    }

    @Test(expected = IllegalArgumentException.class)
    public void wrongCommandTest() {
        RoverManager manager = new MarsRoverManager();
        manager.processStringCommands("D D D D D");
    }

}
