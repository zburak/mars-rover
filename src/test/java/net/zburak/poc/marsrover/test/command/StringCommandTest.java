package net.zburak.poc.marsrover.test.command;

import net.zburak.poc.marsrover.util.Str2CommandOperations;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by buraq
 */
@RunWith(JUnit4.class)
public class StringCommandTest {

    @Test
    public void plateuCommandSuccesTest(){
        String command = "5 5";
        Assert.assertTrue(Str2CommandOperations.checkPlateuCommand(command));
    }

    @Test
    public void plateuCommandFailTest(){
        String command = "5 A 5";
        Assert.assertFalse(Str2CommandOperations.checkPlateuCommand(command));
    }

    @Test
    public void deploymentCommandSuccesTest(){
        String command = "1 2 N";
        Assert.assertTrue(Str2CommandOperations.checkDeploymentCommand(command));
    }

    @Test
    public void deploymentCommandFailTest(){
        String command = "5  5 5";
        Assert.assertFalse(Str2CommandOperations.checkDeploymentCommand(command));
    }

    @Test
    public void moveCommandSuccesTest(){
        String command = "LMLMLMLMM";
        Assert.assertTrue(Str2CommandOperations.checkMoveCommand(command));
    }

    @Test
    public void moveCommandFailTest(){
        String command = "LMLMLMLMMA";
        Assert.assertFalse(Str2CommandOperations.checkMoveCommand(command));
    }
}
