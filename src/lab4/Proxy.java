package lab4;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Proxy
{
    private ReentrantLock mutex = new ReentrantLock();
    private static AtomicInteger readerCount = new AtomicInteger(0);
    private static AtomicInteger writerCount = new AtomicInteger(0);

    private static Proxy instance = null;


    private Proxy()
    {

    }

    public static synchronized Proxy getInstance()
    {
        if (instance == null)
            instance = new Proxy();
        return instance;
    }

    public synchronized void preRead(int pid)
    {
        while (writerCount.get() > 0) {
            Wait();
        }
        readerCount.incrementAndGet();
    }

    public synchronized void postRead(int pid)
    {
        readerCount.decrementAndGet();
        if (readerCount.get() == 0) {
            notify();
        }
    }

    public synchronized void preWrite(int pid)
    {
        while (readerCount.get() > 0 || writerCount.get() > 0) {
            Wait();
        }
        writerCount.incrementAndGet();
    }

    public synchronized void postWrite(int pid)
    {
        writerCount.decrementAndGet();
        if (writerCount.get() == 0) {
            notifyAll();
        }
    }

    private void Wait() {
        try {
            wait();
        } catch (InterruptedException e){};
    }
}
