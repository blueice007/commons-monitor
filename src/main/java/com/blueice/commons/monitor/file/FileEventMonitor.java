package com.blueice.commons.monitor.file;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class FileEventMonitor extends ReentrantLock implements Runnable
{
    private static final long serialVersionUID = 1L;

    private final List<FileEventSource> sources = new CopyOnWriteArrayList<FileEventSource>();
    
    private final int interval;
    
    private Thread thread;
    
    private volatile boolean running = false; 
    
    public FileEventMonitor(int interval)
    {
        super();
        this.interval = interval;
        
    }
    
    public FileEventMonitor(int interval,FileEventSource... eventSources)
    {
        super();
        this.interval = interval;
        addFileEventSource(eventSources);
    }



    public void execute(){
        for(FileEventSource source:sources){
            source.checkAndNotify();
        }
    }

    @Override
    public void run()
    {
        while(running){
            try
            {
                Thread.sleep(interval*1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            execute();
        }
        
    }
    
    public void shutdown(){
        this.lock();
        try{
            running = false;
            thread.join(interval);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            this.unlock();
        }
    }
    
    public void startup(){
        this.lock();
        try{
            if(running){
                throw new IllegalStateException("The Monitor is already running!");
            }
            for(FileEventSource source:sources){
                source.initialize();
            }
            running = true;
            thread = new Thread(this);
            thread.start();
        }finally{
            this.unlock();
        }
        
    }
    
    public void addFileEventSource(FileEventSource... eventSources){
        if(eventSources==null){
            throw new IllegalArgumentException("The eventSources-array must not be null");
        }
        for(FileEventSource temp:eventSources){
            if(temp==null){
                throw new IllegalArgumentException("The element of eventSources-array must not be null");
            }
            sources.add(temp);
        }
    }
    
    public void addFileEventSource(FileEventSource eventSources){
        if(eventSources==null){
            throw new IllegalArgumentException("The eventSources must not be null");
        }
        sources.add(eventSources);
    }

    public List<FileEventSource> getSources()
    {
        return sources;
    }

    public int getInterval()
    {
        return interval;
    }
    
    
}
