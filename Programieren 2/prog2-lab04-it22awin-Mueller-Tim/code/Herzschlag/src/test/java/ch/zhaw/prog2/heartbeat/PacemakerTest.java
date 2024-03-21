package ch.zhaw.prog2.heartbeat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

/*
 * Test Class for Pacemaker
 */
public class PacemakerTest {

    @Mock Heart heart = mock(Heart.class);

    @BeforeEach
    void setUp(){
        when(heart.setHeartRate(anyInt())).thenAnswer(new Answer<Boolean>() {
            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                if((int) invocation.getArgument(0) < 30 || (int) invocation.getArgument(0) > 220){
                    return false;
                } else{
                    when(heart.getHeartRate()).thenReturn((invocation.getArgument(0)));
                    return true;
                }
            }
        });
    }


    /**
     * Test if setHeartRate does throw correct exception when rate is rejected (because frequency is out of range)
     * Exercise 2.3 d
     */
    @Test
    void testSetHeartRateRejectsFrequenciesOutOfRange() {
        //TODO implement
        Pacemaker pacemaker = new Pacemaker(heart);
        assertThrows(IllegalArgumentException.class, () -> pacemaker.setHeartRate(29));
        assertThrows(IllegalArgumentException.class, () -> pacemaker.setHeartRate(221));
    }


    /**
     * Test if setHeartRate does correctly set the rate when frequency is inside range
     * Exercise 2.3 d
     */
    @Test
    void testSetHeartRateAppliesFrequenciesInsideRange() {
        //TODO implement
        Pacemaker pacemaker = new Pacemaker(heart);
        assertEquals(30, pacemaker.setHeartRate(30));
        assertEquals(220, pacemaker.setHeartRate(220));
        assertEquals(100, pacemaker.setHeartRate(100));
    }

}
