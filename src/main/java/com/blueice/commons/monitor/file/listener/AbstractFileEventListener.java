package com.blueice.commons.monitor.file.listener;

import com.blueice.commons.monitor.file.FileEvent;

/**
 * 
* @ClassName: AbstractEventListener
* @Description: 接口抽象类,用空操作实现所有接口
* @author blueice
* @date 2018年9月12日 下午5:27:08
*
 */
public abstract class AbstractFileEventListener implements FileEventListener
{

    @Override
    public void onStart()
    {
        
    }

    @Override
    public void onFinish()
    {
        
    }

    @Override
    public void onCreate(final FileEvent event)
    {
        
    }

    @Override
    public void onChange(final FileEvent event)
    {
        
    }

    @Override
    public void onDelete(final FileEvent event)
    {
        
    }

    @Override
    public void onCreateDir(final FileEvent event)
    {
        
    }

    @Override
    public void onChangeDir(final FileEvent event)
    {
        
    }

    @Override
    public void onDeleteDir(final FileEvent event)
    {
        
    }
}
