package lab4;

public class Proxy
{
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
        // TODO: synchronization before read goes here
    }

    public void postRead(int pid)
    {
        // TODO: synchronization after read goes here
    }

    public void preWrite(int pid)
    {
        // TODO: synchronization before write goes here
    }

    public void postWrite(int pid)
    {
        // TODO: synchronization after write goes here
    }
}
