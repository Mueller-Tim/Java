package ch.zhaw.prog2.circularbuffer;

public class GuardedCircularBuffer<T> implements Buffer<T> {
    private final CircularBuffer<T> buffer;

    public GuardedCircularBuffer(Class<T> clazz, int bufferSize) {
        buffer = new CircularBuffer<>(clazz, bufferSize);
    }

    public synchronized boolean put(T item) throws InterruptedException {
        while(full()){
            wait();
        }
        boolean successful = buffer.put(item);
        notify();
        return successful;
    }

    public synchronized T get() throws InterruptedException {
        while(empty()){
            wait();
        }
        T item = buffer.get();
        notify();
        return item;
    }

    public synchronized void printBufferSlots() {
        buffer.printBufferSlots();
    }

    public synchronized void printBufferContent() {
        buffer.printBufferContent();
    }

    public synchronized boolean empty() {
        return buffer.empty();
    }

    public synchronized boolean full() {
        return buffer.full();
    }

    public synchronized int count() {
        return buffer.count();
    }
}
