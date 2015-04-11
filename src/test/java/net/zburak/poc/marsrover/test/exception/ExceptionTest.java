package net.zburak.poc.marsrover.test.exception;

import net.zburak.poc.marsrover.MarsRover;
import net.zburak.poc.marsrover.Rover;
import net.zburak.poc.marsrover.move.NorthMove;
import net.zburak.poc.marsrover.surface.Coordinate;
import net.zburak.poc.marsrover.surface.Plateau;
import net.zburak.poc.marsrover.surface.Surface;
import net.zburak.poc.marsrover.util.MarsRoverManager;
import net.zburak.poc.marsrover.util.Operations;
import net.zburak.poc.marsrover.util.PrecedenceManager;
import net.zburak.poc.marsrover.util.RoverManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Created by buraq
 */
@RunWith(JUnit4.class)
public class ExceptionTest {

    @Mock
    private PrecedenceManager precedenceManager;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = IllegalArgumentException.class)
    public void boundCheckTest() {
        Surface plateau = new Plateau(new Coordinate(5, 5));
        Rover marsRover = new MarsRover(plateau, new Coordinate(10, 10), new NorthMove());
    }

    @Test(expected = IllegalArgumentException.class)
    public void wrongCommandTest() {
        Mockito.when(precedenceManager.initCheck(Matchers.any(Operations.class))).thenReturn(true);
        RoverManager manager = new MarsRoverManager(precedenceManager);
        manager.processStringCommands("D D D D D");
    }

    @Test(expected = IllegalArgumentException.class)
    public void lowPrecedenceCommandTest() {
        Mockito.when(precedenceManager.initCheck(Matchers.any(Operations.class))).thenReturn(false);
        RoverManager manager = new MarsRoverManager(precedenceManager);
        manager.processStringCommands("5 5");
    }

}
