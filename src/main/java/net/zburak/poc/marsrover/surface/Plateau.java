package net.zburak.poc.marsrover.surface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by buraq
 */
public class Plateau implements Surface {

    final static Logger logger = LoggerFactory.getLogger(Plateau.class);

    private Point upperRight;
    private Point lowerLeft = new Coordinate(0, 0);

    public Plateau(Coordinate upperRight) {
        this.upperRight = upperRight;
        logger.debug("Plateau has been initialized");
    }

    @Override
    public void checkBounds(Point point) {
        if(!checkXBound(point.getX()) && !checkYBound(point.getY())){
            StringBuilder err = new StringBuilder("Given coordiates is out of Plateau area ").append("X:").append(point.getX()).append(" Y:").append(point.getY());
            logger.error(err.toString());
            throw new IllegalArgumentException(err.toString());
        }

    }

    private boolean checkYBound(int y) {
        return y <= upperRight.getY() && y >= lowerLeft.getY();
    }

    private boolean checkXBound(int x) {
        return x <= upperRight.getX() && x >= lowerLeft.getX();
    }
}
