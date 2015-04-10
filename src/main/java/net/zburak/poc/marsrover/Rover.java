package net.zburak.poc.marsrover;

/**
 * Created by buraq
 */
public interface Rover {

    public void moveForward();
    public void spinLeft();
    public void spinRight();
    public String locationInfo();
}
