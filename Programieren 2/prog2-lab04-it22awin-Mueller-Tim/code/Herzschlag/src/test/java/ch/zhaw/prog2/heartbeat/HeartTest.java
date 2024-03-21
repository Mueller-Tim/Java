/*
 * Test Class for Heart
 */
package ch.zhaw.prog2.heartbeat;

import ch.zhaw.prog2.heartbeat.Heart.State;
import ch.zhaw.prog2.heartbeat.parts.Valve;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HeartTest {

    @Mock Half rightHalf;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }


    /**
     * This is a very simple test to check if Junit and Mockito are properly set up.
     */
    @Test
    void testTheTest() {
        Heart classUnderTest = new Heart();
        assertNotNull(classUnderTest.getState(), "The heart must have a state.");
    }

    /**
     * Tests a single heartbeat
     */
    @Test
    void testHeartBeat() throws Heart.HeartBeatDysfunctionException, Valve.IllegalValveStateException {
        Heart heart = new Heart();
        State startState = heart.getState();

        heart.executeHeartBeat();

        // after one heartbeat, the heart must be in the same state as before
        assertEquals(startState, heart.getState());
    }

    /**
     * Tests if the valves are open or closed depending on the status of the heart
     */
    @Test
    void testValveStatus() throws Heart.HeartBeatDysfunctionException, Valve.IllegalValveStateException {
        Heart heart = new Heart();

        heart.executeHeartBeat();

        State state = heart.getState();

        if (state.equals(Heart.State.DIASTOLE)) {
            for (Half half : heart.getHalves()) {
                assertFalse(half.isAtrioventricularValveOpen());
                assertTrue(half.isSemilunarValveOpen());
            }
        } else if ((state.equals(Heart.State.SYSTOLE))) {
            for (Half half : heart.getHalves()) {
                assertTrue(half.isAtrioventricularValveOpen());
                assertFalse(half.isSemilunarValveOpen());
            }
        }
    }

    /**
     * Tests if the hart throws the appropriate Exception, when malfunction was detected during hartBeat
     */
    @Test
    void testExecuteHeartBeatErrorBehavior() {
        Heart heart = new Heart();
        // prepare error situation due to wrong initialization
        heart.setState(State.SYSTOLE);

        assertThrows(Heart.HeartBeatDysfunctionException .class, // verification using lambda
            () -> heart.executeHeartBeat());
    }

    /**
     * Tests if the hart throws the appropriate Exception, when malfunction was detected during hartBeat
     * with exception Stubbing using Mock-Objekts. Exercise 2.3 b.
     */
    @Test
    void testExecuteHeartBeatErrorBehaviorWithMocking() {
        //TODO implement
        Heart heart = mock(Heart.class);
        try{
            doThrow(new Heart.HeartBeatDysfunctionException()).when(heart).executeHeartBeat();
        } catch (Heart.HeartBeatDysfunctionException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * We test if Heart::executeHeartbeat() sends the right signals to both of its
     * halves. Exercise 2.3 a
     *
     * When Half::contractVentricle() is called, Half::closeAtrioventricularValve()
     * and Half::openSemilunarValve() must have been called earlier.
     *
     */
    @Test
    void testValvesBehavior() {
        //TODO: implement
        Half leftHalf = mock(Half.class);
        Heart heart = new Heart(leftHalf, rightHalf);

        when(leftHalf.isAtrioventricularValveOpen()).thenReturn(false,  true);
        when(leftHalf.isSemilunarValveOpen()).thenReturn(true, false);
        when(rightHalf.isAtrioventricularValveOpen()).thenReturn(false, true);
        when(rightHalf.isSemilunarValveOpen()).thenReturn(true,  false);



        InOrder inOrder;

        try{
            heart.executeDiastole();
        } catch (Valve.IllegalValveStateException | Valve.InvalidValvePositionException e){
            System.err.println(e.getMessage());
        }

        try{
            for(Half mockedHalf : heart.getHalves()){
                inOrder = inOrder(mockedHalf);
                inOrder.verify(mockedHalf).initializeState(State.DIASTOLE);
                inOrder.verify(mockedHalf).isAtrioventricularValveOpen();
                inOrder.verify(mockedHalf).isSemilunarValveOpen();
                inOrder.verify(mockedHalf).openAtrioventricularValve();
                inOrder.verify(mockedHalf).closeSemilunarValve();
                inOrder.verify(mockedHalf).relaxAtrium();
                inOrder.verify(mockedHalf).relaxVentricle();
                inOrder.verifyNoMoreInteractions();
            }
        } catch (Valve.IllegalValveStateException e){
            System.err.println(e.getMessage());
        }

        try{
            heart.executeSystole();
        } catch (Valve.IllegalValveStateException | Valve.InvalidValvePositionException e){
            System.err.println(e.getMessage());
        }

        try{
            for(Half mockedHalf : heart.getHalves()){
                inOrder = inOrder(mockedHalf);
                inOrder.verify(mockedHalf).isAtrioventricularValveOpen();
                inOrder.verify(mockedHalf).isSemilunarValveOpen();
                inOrder.verify(mockedHalf).closeAtrioventricularValve();
                inOrder.verify(mockedHalf).openSemilunarValve();
                inOrder.verify(mockedHalf).contractVentricle();
                inOrder.verify(mockedHalf).contractAtrium();
                Mockito.verifyNoMoreInteractions(ignoreStubs(mockedHalf));
            }
        } catch (Valve.IllegalValveStateException e){
            System.err.println(e.getMessage());
        }
    }


    /**
     * Tests if an exception is thrown in the diastole if the valve position is wrong.
     * Exercise 2.3 c
     */
    @Test
    void testDiastoleException () {
        // TODO: implement
        Heart heart = mock(Heart.class);
        try{
            doThrow(new Valve.InvalidValvePositionException()).when(heart).executeDiastole();
            doThrow(new Valve.IllegalValveStateException()).when(heart).executeDiastole();
        } catch (Valve.IllegalValveStateException | Valve.InvalidValvePositionException e){
            System.err.println(e.getMessage());
        }
    }

    /**
     * Tests if an exception is thrown in the systole if the valve position is wrong.
     * Exercise 2.3 c
     */
    @Test
    void testSystoleException () {
        // TODO: implement
        Heart heart = mock(Heart.class);
        try{
            doThrow(new Valve.InvalidValvePositionException()).when(heart).executeSystole();
            doThrow(new Valve.IllegalValveStateException()).when(heart).executeSystole();
        } catch (Valve.IllegalValveStateException | Valve.InvalidValvePositionException e){
            System.err.println(e.getMessage());
        }
    }

}
