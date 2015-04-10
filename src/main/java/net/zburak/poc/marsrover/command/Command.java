package net.zburak.poc.marsrover.command;

import net.zburak.poc.marsrover.Rover;

/**
 * Created by buraq
 */
public interface Command {
    public void execute(Rover rover);
}
