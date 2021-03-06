package net.zburak.poc.marsrover.util;

import net.zburak.poc.marsrover.MarsRover;
import net.zburak.poc.marsrover.Rover;
import net.zburak.poc.marsrover.command.*;
import net.zburak.poc.marsrover.move.Move;
import net.zburak.poc.marsrover.surface.Coordinate;
import net.zburak.poc.marsrover.surface.Plateau;
import net.zburak.poc.marsrover.surface.Surface;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by buraq
 * Process the string commands and executes generated command objects
 */
public class MarsRoverManager implements RoverManager {

    final static Logger logger = LoggerFactory.getLogger(MarsRover.class);

    private Rover marsRover;
    private Plateau plateau;
    private Move move;
    private PrecedenceManager precedenceManager;
    private Operations operation;

    public MarsRoverManager(PrecedenceManager precedenceManager) {
        this.precedenceManager = precedenceManager;
        operation = Operations.NONE;
    }

    private static Map<String, Command> commandMap = new HashMap<String, Command>(){{
        put("M", new MoveForwardCommand());
        put("L", new SpinLeftCommand());
        put("R", new SpinRightCommand());
    }};

    /**
     * Validate command and execute
     * @param command
     */
    @Override
    public void processStringCommands(String command) {
        command = StringUtils.upperCase(command.trim());
        if (Str2CommandOperations.checkPlateuCommand(command) && precedenceManager.initCheck(operation)) {
            plateau = new Plateau(Str2CommandOperations.extractCoordinates(command));
            setOperation(Operations.INIT);
        } else if(Str2CommandOperations.checkDeploymentCommand(command) && precedenceManager.deploymentCheck(operation)) {
            Coordinate deploymentPoint = Str2CommandOperations.extractCoordinates(command.substring(0, 3));
            move = Str2CommandOperations.extractMove(command.substring(4));
            marsRover = new MarsRover(plateau, deploymentPoint, move);
            setOperation(Operations.DEPLOY);
        } else if(Str2CommandOperations.checkMoveCommand(command) && precedenceManager.moveCheck(operation)){
            List<Command> commands = generateCommands(command);
            Command multiCommand = new MultiCommand(commands);
            multiCommand.execute(marsRover);
            setOperation(Operations.MOVE);
        } else {
            logger.error("Not supported or low precedence command");
            throw new IllegalArgumentException("Not supported or low precedence command: " + command);
        }
    }


    /**
     * Executes multiple string commands
     * @param commands
     */
    @Override
    public void processStringCommands(List<String> commands) {
        for(String command : commands){
            processStringCommands(command);
        }
    }

    @Override
    public Rover getRover() {
        return marsRover;
    }

    @Override
    public Surface getSurface() {
        return plateau;
    }

    /**
     * Generates command objects from string
     *
     * @param command
     * @return
     */
    private List<Command> generateCommands(String command) {
        List<Command> cmdList = new LinkedList<Command>();
        command = StringUtils.upperCase(command);
        char[] letters = command.toCharArray();
        for(char action : letters){
            cmdList.add(commandMap.get(String.valueOf(action)));
        }
        return cmdList;
    }

    public void setOperation(Operations operation) {
        this.operation = operation;
    }
}
