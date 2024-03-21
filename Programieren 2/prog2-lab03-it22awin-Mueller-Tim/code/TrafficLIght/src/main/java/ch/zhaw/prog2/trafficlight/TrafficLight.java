package ch.zhaw.prog2.trafficlight;

class TrafficLight {
    private boolean red;

    public TrafficLight() {
        red = true;
    }

    public synchronized void passby() {
        // ToDo: wait as long the light is red
        while (red){
            try{
                wait();
            } catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public synchronized void switchToRed() {
        // ToDo: set light to red
        red = true;
    }

    public synchronized void switchToGreen() {
        // Todo: set light to green
        // waiting cars can now pass by
        red = false;
        notifyAll();
    }
}
