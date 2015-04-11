package net.zburak.poc.marsrover.test.precedence;

import net.zburak.poc.marsrover.util.Operations;
import net.zburak.poc.marsrover.util.PrecedenceManager;
import net.zburak.poc.marsrover.util.PrecedenceManagerImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by buraq
 */
@RunWith(JUnit4.class)
public class PrecedenceTest {

    private Operations operation;
    private PrecedenceManager manager;

    @Before
    public void init(){
        manager = new PrecedenceManagerImpl();
    }

    @Test
    public void initCheckTest(){
        operation = Operations.NONE;
        Assert.assertTrue(manager.initCheck(operation));

        operation = Operations.INIT;
        Assert.assertTrue(manager.initCheck(operation));

        operation = Operations.MOVE;
        Assert.assertTrue(manager.initCheck(operation));
    }

    @Test
    public void deployCheckTest(){
        operation = Operations.INIT;
        Assert.assertTrue(manager.deploymentCheck(operation));

        operation = Operations.MOVE;
        Assert.assertTrue(manager.deploymentCheck(operation));
    }


    @Test
    public void moveCheckTest(){
        operation = Operations.DEPLOY;
        Assert.assertTrue(manager.moveCheck(operation));

        operation = Operations.MOVE;
        Assert.assertTrue(manager.moveCheck(operation));
    }
}
