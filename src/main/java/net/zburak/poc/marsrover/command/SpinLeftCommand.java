package net.zburak.poc.marsrover.command;

import net.zburak.poc.marsrover.Rover;

/**
 * Created by buraq
 */
public class SpinLeftCommand implements Command{

    @Override
    public void execute(Rover rover) {
        rover.spinLeft();
    }
}
