package net.zburak.poc.marsrover.test.marsrover;

import net.zburak.poc.marsrover.util.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by buraq
 */
@RunWith(JUnit4.class)
public class MarsRoverManagerTest {

    private RoverManager marsRoverManager;

    @Mock
    private PrecedenceManager precedenceManager;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        marsRoverManager = new MarsRoverManager(precedenceManager);
        Mockito.when(precedenceManager.initCheck(Matchers.any(Operations.class))).thenReturn(true);
        Mockito.when(precedenceManager.deploymentCheck(Matchers.any(Operations.class))).thenReturn(true);
        Mockito.when(precedenceManager.moveCheck(Matchers.any(Operations.class))).thenReturn(true);
    }

    @Test
    public void initSurfaceTest(){
        marsRoverManager.processStringCommands("5 5");
        Assert.assertNotNull(marsRoverManager.getSurface());
    }

    @Test
    public void deploymentTest(){
        marsRoverManager.processStringCommands("5 5");
        marsRoverManager.processStringCommands("1 2 N");
        Assert.assertNotNull(marsRoverManager.getRover());
        Assert.assertEquals("1 2 N", marsRoverManager.getRover().locationInfo());
    }

    @Test
    public void deployAndMoveTest(){
        marsRoverManager = new MarsRoverManager(new PrecedenceManagerImpl());
        List<String> stringCommands = new LinkedList<String>();
        stringCommands.add("5 5");//init plateu
        stringCommands.add("1 2 N");//deploy rover
        stringCommands.add("LMLMLMLMM"); //move rover
        marsRoverManager.processStringCommands(stringCommands);
        Assert.assertEquals("1 3 N", marsRoverManager.getRover().locationInfo());

        stringCommands.clear();
        stringCommands.add("3 3 E");//deploy rover
        stringCommands.add("MMRMMRMRRM");//move rover
        marsRoverManager.processStringCommands(stringCommands);
        Assert.assertEquals("5 1 E", marsRoverManager.getRover().locationInfo());
    }
}
