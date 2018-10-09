package com.blueice.commons.monitor.file.listener;

import com.blueice.commons.monitor.file.FileEvent;


public class DefaultFileEventListenner extends AbstractFileEventListener
{
    @Override
    public void onCreate(final FileEvent event)
    {
        System.out.println(event.getFilePath()+":onCreate...");
    }

    @Override
    public void onChange(final FileEvent event)
    {
        System.out.println(event.getFilePath()+":onChange...");
    }

    @Override
    public void onDelete(final FileEvent event)
    {
        System.out.println(event.getFilePath()+":onDelete...");
    }

    @Override
    public void onCreateDir(final FileEvent event)
    {
        System.out.println(event.getFilePath()+":onCreateDir...");
    }

    @Override
    public void onChangeDir(final FileEvent event)
    {
        System.out.println(event.getFilePath()+":onChangeDir...");
    }

    @Override
    public void onDeleteDir(final FileEvent event)
    {
        System.out.println(event.getFilePath()+":onDeleteDir...");
    }

    @Override
    public void onStart()
    {
        System.out.println("onstart:"+System.currentTimeMillis());
    }

    @Override
    public void onFinish()
    {
        System.out.println("onfinish:"+System.currentTimeMillis());
    }
    
}
