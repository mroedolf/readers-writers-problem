package lab4;

import java.util.concurrent.locks.ReentrantLock;

public class Proxy
{
    private ReentrantLock mutex = new ReentrantLock();

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
            mutex.lock();
        } finally {

        }
        // TODO: synchronization before read goes here
    }

    public void postRead(int pid)
    {
        mutex.unlock();
        // TODO: synchronization after read goes here
    }

    public void preWrite(int pid)
    {
        try {
            mutex.lock();
        } finally {

        }
        // TODO: synchronization before write goes here
    }

    public void postWrite(int pid)
    {
        mutex.unlock();
        // TODO: synchronization after write goes here
    }
}
