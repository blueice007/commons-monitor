package com.blueice.commons.monitor.file.listener;

import com.blueice.commons.monitor.file.FileEvent;

/**
 * 
* @ClassName: FileEventListener
* @Description: 文件事件监听器
* @author blueice
* @date 2018年9月12日 下午5:18:18
*
 */
public interface FileEventListener
{
    /**
     * 
    * <p>文件创建事件</p>
    * @author blueice
    * @date 2018年9月12日 下午5:22:08
     */
    void onCreate(final FileEvent event);
    /**
     * 
    * <p>文件修改事件</p>
    * @author blueice
    * @date 2018年9月12日 下午5:22:08
     */
    void onChange(final FileEvent event);
    /**
     * 
    * <p>文件删除事件</p>
    * @author blueice
    * @date 2018年9月12日 下午5:22:08
     */
    void onDelete(final FileEvent event);
    /**
     * 
    * <p>目录创建事件</p>
    * @author blueice
    * @date 2018年9月12日 下午5:22:08
     */
    void onCreateDir(final FileEvent event);
    /**
     * 
    * <p>目录修改事件，指目录名修改</p>
    * @author blueice
    * @date 2018年9月12日 下午5:22:08
     */
    void onChangeDir(final FileEvent event);
    /**
     * 
    * <p>目录删除事件</p>
    * @author blueice
    * @date 2018年9月12日 下午5:22:08
     */
    void onDeleteDir(final FileEvent event);
    
    void onStart();
    
    void onFinish();
}
