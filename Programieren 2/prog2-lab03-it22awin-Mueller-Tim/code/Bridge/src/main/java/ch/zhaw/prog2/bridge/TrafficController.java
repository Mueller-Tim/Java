package ch.zhaw.prog2.bridge;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Controls the traffic passing the bridge
 */
public class TrafficController {

    private boolean bridgeOccupied = false;
    private final ReentrantLock controllerLock = new ReentrantLock();
    private final Condition leftCarEnter = controllerLock.newCondition();
    private final Condition rightCarEnter = controllerLock.newCondition();

    /* Called when a car wants to enter the bridge form the left side */
    public void enterLeft() {
        controllerLock.lock();
        try{
            while(bridgeOccupied){
                leftCarEnter.await();
            }
            bridgeOccupied = true;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            controllerLock.unlock();
        }
    }

    /* Called when a wants to enter the bridge form the right side */
    public void enterRight() {
        controllerLock.lock();
        try{
            while (bridgeOccupied){
                rightCarEnter.await();
            }
            bridgeOccupied = true;
        } catch (InterruptedException e){
            throw new RuntimeException();
        } finally {
            controllerLock.unlock();
        }
    }

    /* Called when the car leaves the bridge on the left side */
    public void leaveLeft() {
        controllerLock.lock();
        try{
            bridgeOccupied = false;
            if(controllerLock.hasWaiters(rightCarEnter)){
                rightCarEnter.signal();
            } else {
                leftCarEnter.signal();
            }
        } finally {
            controllerLock.unlock();
        }
    }

    /* Called when the car leaves the bridge on the right side */
    public void leaveRight() {
        controllerLock.lock();
        try{
            bridgeOccupied = false;
            if(controllerLock.hasWaiters(leftCarEnter)){
                leftCarEnter.signal();
            } else{
                rightCarEnter.signal();
            }
        } finally {
            controllerLock.unlock();
        }
    }
}
