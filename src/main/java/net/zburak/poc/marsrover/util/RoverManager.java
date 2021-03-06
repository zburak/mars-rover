package net.zburak.poc.marsrover.util;

import net.zburak.poc.marsrover.Rover;
import net.zburak.poc.marsrover.surface.Surface;

import java.util.List;

/**
 * Created by buraq
 */
public interface RoverManager {

    public void processStringCommands(String command);
    public void processStringCommands(List<String> command);
    public Rover getRover();
    public Surface getSurface();
}
