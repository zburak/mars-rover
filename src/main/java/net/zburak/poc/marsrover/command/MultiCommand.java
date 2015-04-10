package net.zburak.poc.marsrover.command;

import net.zburak.poc.marsrover.Rover;

import java.util.List;

/**
 * Created by buraq
 */
public class MultiCommand implements Command {

    private List<Command> commandList;

    public MultiCommand(List<Command> commandList) {
        this.commandList = commandList;
    }

    @Override
    public void execute(Rover rover) {
        for (Command command : commandList) {
            command.execute(rover);
        }
    }
}
