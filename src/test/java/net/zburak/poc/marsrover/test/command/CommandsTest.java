package net.zburak.poc.marsrover.test.command;

import net.zburak.poc.marsrover.MarsRover;
import net.zburak.poc.marsrover.Rover;
import net.zburak.poc.marsrover.command.Command;
import net.zburak.poc.marsrover.command.MoveForwardCommand;
import net.zburak.poc.marsrover.command.MultiCommand;
import net.zburak.poc.marsrover.command.SpinLeftCommand;
import net.zburak.poc.marsrover.move.NorthMove;
import net.zburak.poc.marsrover.surface.Coordinate;
import net.zburak.poc.marsrover.surface.Plateau;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by buraq
 *
 * Tests the move and spin commands
 */
@RunWith(JUnit4.class)
public class CommandsTest {

    final static Logger logger = LoggerFactory.getLogger(CommandsTest.class);

    private Plateau plateau;
    private Rover marsRover;
    private Coordinate coordinate;

    @Before
    public void init(){
        plateau = new Plateau(new Coordinate(5, 5));
        coordinate = new Coordinate(1, 2);
        marsRover = new MarsRover(plateau, coordinate, new NorthMove());
    }

    @Test
    public void moveCommandTest(){
        Command moveForward = new MoveForwardCommand();
        moveForward.execute(marsRover);
        logger.debug(marsRover.locationInfo());
        Assert.assertEquals("1 3 N", marsRover.locationInfo());
    }

    @Test
    public void multipleMoveCommandTest(){
        List<Command>  commands = new ArrayList<Command>();
        commands.add(new MoveForwardCommand());
        commands.add(new MoveForwardCommand());
        for(Command cmd : commands){
            cmd.execute(marsRover);
        }
        logger.debug(marsRover.locationInfo());
        Assert.assertEquals("1 4 N", marsRover.locationInfo());
    }

    @Test
    public void multipleMoveCommandWithSpinTest(){
        List<Command>  commands = new ArrayList<Command>();
        commands.add(new SpinLeftCommand());
        commands.add(new MoveForwardCommand());
        commands.add(new SpinLeftCommand());
        commands.add(new MoveForwardCommand());
        commands.add(new SpinLeftCommand());
        commands.add(new MoveForwardCommand());
        commands.add(new SpinLeftCommand());
        commands.add(new MoveForwardCommand());
        commands.add(new MoveForwardCommand());
        for(Command cmd : commands){
            cmd.execute(marsRover);
        }
        logger.debug("Current Position: " + marsRover.locationInfo());
        Assert.assertEquals("1 3 N", marsRover.locationInfo());
    }

    @Test
    public void multiCommandTest(){
        List<Command>  commands = new ArrayList<Command>();
        commands.add(new SpinLeftCommand());
        commands.add(new MoveForwardCommand());
        commands.add(new SpinLeftCommand());
        commands.add(new MoveForwardCommand());
        commands.add(new SpinLeftCommand());
        commands.add(new MoveForwardCommand());
        commands.add(new SpinLeftCommand());
        commands.add(new MoveForwardCommand());
        commands.add(new MoveForwardCommand());

        Command multiCommand = new MultiCommand(commands);
        multiCommand.execute(marsRover);
        logger.debug("Current Position: " + marsRover.locationInfo());
        Assert.assertEquals("1 3 N", marsRover.locationInfo());
    }


}
