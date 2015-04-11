package net.zburak.poc.marsrover.util;

/**
 * Created by buraq on 11.04.2015.
 */
public interface PrecedenceManager {

    public boolean initCheck(Operations operation);
    public boolean deploymentCheck(Operations operation);
    public boolean moveCheck(Operations operation);

}
