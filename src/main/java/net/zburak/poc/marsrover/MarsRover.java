package net.zburak.poc.marsrover;

import net.zburak.poc.marsrover.move.Move;
import net.zburak.poc.marsrover.surface.Coordinate;
import net.zburak.poc.marsrover.surface.Point;
import net.zburak.poc.marsrover.surface.Surface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by buraq
 */
public class MarsRover implements Rover {

    final static Logger logger = LoggerFactory.getLogger(MarsRover.class);

    private Surface plateau;
    private Point currentPoint;
    private Move move;

    public MarsRover(Surface plateau, Point deploymentPoint, Move move) {
        this.plateau = plateau;
        plateau.checkBounds(deploymentPoint);
        this.currentPoint = deploymentPoint;
        this.move = move;
        logger.debug("New Mars Rover is created and deployed on " + currentPoint.getX() + " " + currentPoint.getY() + " " + move.direction());
    }

    @Override
    public void moveForward() {
        int currX = currentPoint.getX(), currY = currentPoint.getY();
        int nextX = move.axisAction().getX(), nextY = move.axisAction().getY();
        Coordinate newCurrent = new Coordinate(currX + nextX, currY + nextY);
        plateau.checkBounds(newCurrent);
        currentPoint = newCurrent;
        logger.debug("Moving to the " + move.direction());
    }

    @Override
    public void spinLeft() {
        this.move = move.left();
        logger.debug("Left Turn. Facing to the " + move.direction());
    }

    @Override
    public void spinRight() {
        this.move = move.right();
        logger.debug("Right Turn. Facing to the " + move.direction());
    }

    @Override
    public String locationInfo() {
        StringBuilder locationBuilder = new StringBuilder();
        locationBuilder.append(currentPoint.getX()).append(" ").append(currentPoint.getY()).append(" ").append(move.direction());
        logger.debug("Mars Rover's current position: " + locationBuilder.toString());
        return locationBuilder.toString();
    }
}
