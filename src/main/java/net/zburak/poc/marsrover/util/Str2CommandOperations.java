package net.zburak.poc.marsrover.util;

import net.zburak.poc.marsrover.move.Move;
import net.zburak.poc.marsrover.move.MoveFactory;
import net.zburak.poc.marsrover.move.MoveFactoryImpl;
import net.zburak.poc.marsrover.surface.Coordinate;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by buraq
 *
 * Validation class for String commands
 */
public class Str2CommandOperations {


    private static boolean checkCommandLength(String command, int commandLength) {
        if (StringUtils.isNotEmpty(command) && command.split(CommandConstants.COMMAND_SEPERATOR).length == commandLength) {
            return true;
        }
        return false;
    }

    public static boolean checkPlateuCommand(String command){
        command = command.trim();
        if(checkCommandLength(command, CommandConstants.PLATEU_COMMAND_LENGTH) && checkAllNumeric(command)){
            return true;
        }
        return false;
    }

    public static boolean checkDeploymentCommand(String command){
        command = command.trim();
        if(checkCommandLength(command, CommandConstants.DEPLOYMENT_COMMAND_LENGTH)){

            String[] commandParts = command.split(CommandConstants.COMMAND_SEPERATOR);

            if(checkAllNumeric(command.substring(0, 2)) && isDirection(commandParts[2])){
                return true;
            }
        }
        return false;
    }

    public static boolean checkMoveCommand(String command) {
        if (checkCommandLength(command, CommandConstants.MOVE_COMMAND_LENGTH)) {
            char[] commands = command.toCharArray();
            for (char c : commands) {
                if (c != CommandConstants.LEFT && c != CommandConstants.RIGHT && c != CommandConstants.MOVE) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private static boolean isDirection(String commandPart) {
        if(Compass.valueOf(commandPart) != null){
            return true;
        }
        return false;
    }

    private static boolean checkAllNumeric(String command) {
        String[] commandParts = command.split(CommandConstants.COMMAND_SEPERATOR);
        for(String part : commandParts){
            if(!StringUtils.isNumeric(part)){
                return false;
            }
        }
        return true;
    }

    public static Coordinate extractCoordinates(String command) {
        command = command.trim();
        String[] xy = command.split(CommandConstants.COMMAND_SEPERATOR);
        int x = Integer.parseInt(xy[0]);
        int y = Integer.parseInt(xy[1]);
        return new Coordinate(x, y);
    }

    public static Move extractMove(String moveDirection) {
        MoveFactory moveFactory = new MoveFactoryImpl();
        return moveFactory.determineMove(Compass.valueOf(moveDirection));
    }
}
