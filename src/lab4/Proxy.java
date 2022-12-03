package lab4;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Proxy
{
    private final Semaphore orderMutex = new Semaphore(1);
    private final Semaphore accessMutex = new Semaphore(1);
    private final Semaphore readersMutex = new Semaphore(1);

    private static AtomicInteger activeReaders = new AtomicInteger(0);

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

    public void preRead(int pid)
    {
        try {
            orderMutex.acquire();
            if (activeReaders.get() == 0) accessMutex.acquire();
            activeReaders.getAndIncrement();
            orderMutex.release();

        } catch (InterruptedException e) {}
    }

    public void postRead(int pid)
    {
        activeReaders.getAndDecrement();
        if (activeReaders.get() == 0) accessMutex.release();
    }

    public void preWrite(int pid)
    {
        try {
            orderMutex.acquire();
            accessMutex.acquire();
            orderMutex.release();
        } catch (InterruptedException e){};
    }

    public void postWrite(int pid)
    {
        accessMutex.release();
    }
}
