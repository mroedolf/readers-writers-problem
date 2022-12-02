package lab4;

import java.util.concurrent.locks.ReentrantLock;

public class Proxy
{
    private ReentrantLock mutex = new ReentrantLock();
    private int readerCount, writerCount;

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
        while (writerCount > 0) {
            Wait();
        }
        readerCount++;
    }

    public synchronized void postRead(int pid)
    {
        readerCount--;
        if (readerCount == 0) {
            notify();
        }
    }

    public synchronized void preWrite(int pid)
    {
        while (readerCount > 0 || writerCount > 0) {
            Wait();
        }
        writerCount++;
    }

    public synchronized void postWrite(int pid)
    {
        writerCount--;
        if (writerCount == 0) {
            notifyAll();
        }
    }

    private void Wait() {
        try {
            wait();
        } catch (InterruptedException e){};
    }
}
