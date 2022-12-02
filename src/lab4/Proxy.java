package lab4;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Proxy
{
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

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
            readLock.lock();
        } finally {

        }
    }

    public void postRead(int pid)
    {
        readLock.unlock();
    }

    public void preWrite(int pid)
    {
        try {
            writeLock.lock();
        } finally {

        }
    }

    public void postWrite(int pid)
    {
        writeLock.unlock();
    }
}
