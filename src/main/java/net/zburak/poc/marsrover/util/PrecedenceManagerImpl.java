package net.zburak.poc.marsrover.util;

/**
 * Created by buraq
 * Checks the operations order
 *
 * NONE, INIT, MOVE allow new plateu initialization
 * INIT, MOVE allow new rover deployment
 * DEPLOY, MOVE allow new move
 */
public class PrecedenceManagerImpl implements PrecedenceManager {


    @Override
    public boolean initCheck(Operations operation) {
        if(Operations.INIT.equals(operation) || Operations.MOVE.equals(operation) || Operations.NONE.equals(operation)){
            return true;
        }
        return false;
    }

    @Override
    public boolean deploymentCheck(Operations operation) {
        if(Operations.INIT.equals(operation) || Operations.MOVE.equals(operation)){
            return true;
        }
        return false;
    }

    @Override
    public boolean moveCheck(Operations operation) {
        if(Operations.DEPLOY.equals(operation) || Operations.MOVE.equals(operation)){
            return true;
        }
        return false;
    }
}
