package net.zburak.poc.marsrover.test.marsrover;

import net.zburak.poc.marsrover.MarsRover;
import net.zburak.poc.marsrover.Rover;
import net.zburak.poc.marsrover.move.EastMove;
import net.zburak.poc.marsrover.move.Move;
import net.zburak.poc.marsrover.move.WestMove;
import net.zburak.poc.marsrover.surface.Coordinate;
import net.zburak.poc.marsrover.surface.Plateau;
import net.zburak.poc.marsrover.surface.Point;
import net.zburak.poc.marsrover.surface.Surface;
import net.zburak.poc.marsrover.util.Compass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Created by buraq
 */
@RunWith(JUnit4.class)
public class MarsRoverTest {

    private Rover marsRover;
    private Surface plateau;
    private Point deploymentPoint;

    @Mock
    private Move move;

    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);
        plateau = new Plateau(new Coordinate(5, 5));
        deploymentPoint = new Coordinate(1, 2);
    }

    @Test
    public void moveNorthTest() {
        Mockito.when(move.left()).thenReturn(new WestMove());
        Mockito.when(move.right()).thenReturn(new EastMove());
        Mockito.when(move.axisAction()).thenReturn(new Coordinate(0, 1));
        Mockito.when(move.direction()).thenReturn(Compass.N);

        marsRover = new MarsRover(plateau, deploymentPoint, move);
        marsRover.moveForward();
        Assert.assertEquals("1 3 N", marsRover.locationInfo());
    }

    @Test
    public void moveSouthTest() {
        Mockito.when(move.left()).thenReturn(new WestMove());
        Mockito.when(move.right()).thenReturn(new EastMove());
        Mockito.when(move.axisAction()).thenReturn(new Coordinate(0, 1));
        Mockito.when(move.direction()).thenReturn(Compass.N);

        marsRover = new MarsRover(plateau, deploymentPoint, move);
        marsRover.spinLeft();
        marsRover.spinLeft();
        marsRover.moveForward();
        Assert.assertEquals("1 1 S", marsRover.locationInfo());
    }

    @Test
    public void moveEastTest() {
        Mockito.when(move.left()).thenReturn(new WestMove());
        Mockito.when(move.right()).thenReturn(new EastMove());
        Mockito.when(move.axisAction()).thenReturn(new Coordinate(0, 1));
        Mockito.when(move.direction()).thenReturn(Compass.N);

        marsRover = new MarsRover(plateau, deploymentPoint, move);
        marsRover.spinRight();
        marsRover.moveForward();
        Assert.assertEquals("2 2 E", marsRover.locationInfo());
    }

    @Test
    public void moveWestTest() {
        Mockito.when(move.left()).thenReturn(new WestMove());
        Mockito.when(move.right()).thenReturn(new EastMove());
        Mockito.when(move.axisAction()).thenReturn(new Coordinate(0, 1));
        Mockito.when(move.direction()).thenReturn(Compass.N);

        marsRover = new MarsRover(plateau, deploymentPoint, move);
        marsRover.spinLeft();
        marsRover.moveForward();
        Assert.assertEquals("0 2 W", marsRover.locationInfo());
    }
}
