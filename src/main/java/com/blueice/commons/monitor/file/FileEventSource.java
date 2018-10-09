package com.blueice.commons.monitor.file;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.blueice.commons.monitor.file.filter.FileEntryFilter;
import com.blueice.commons.monitor.file.listener.FileEventListener;
import com.blueice.commons.monitor.file.scanner.FileScanner;

public class FileEventSource
{
    private final List<FileEventListener> listeners = new CopyOnWriteArrayList<FileEventListener>();
    private final FileScanner scanners;
    private final FileEntryFilter fileEntryFilter;
    private FileEntry rootEntry;
    
    public FileEventSource(FileScanner scanners,FileEntryFilter fileEntryFilter)
    {
        this.scanners = scanners;
        this.fileEntryFilter = fileEntryFilter;
    }

    public void addEventListener(FileEventListener listener){
        if(listener!=null){
            listeners.add(listener);
        }
    }
    
    public void initialize(){
        rootEntry = scanners.scan(fileEntryFilter);
    }
    private void checkAndNotify(FileEntry[] orgChildren,FileEntry[] curChildren){
        int curindex = 0;
        int curLen = curChildren.length;
        for(FileEntry orgTemp:orgChildren){
            while(curindex<curLen&&orgTemp.compareTo(curChildren[curindex])>0){
                //新增
                onCreate(curChildren[curindex]);
                curindex++;
            }
            if(curindex<curLen&&orgTemp.compareTo(curChildren[curindex])==0){
                //检测是否改动 判断依据为最后更新时间是否一致
                checkModify(orgTemp,curChildren[curindex]);
                checkAndNotify(orgTemp.getChildren(), curChildren[curindex].getChildren());
                curindex++;
            }else{
                //删除
                onDelete(orgTemp);
            }
        }
        while(curindex<curLen){
            onCreate(curChildren[curindex]);
            curindex++;
        }
    }
    

    public void checkAndNotify(){
        for(FileEventListener temp:listeners){
            temp.onStart();
        }
        //long t = System.currentTimeMillis();
        // 检测事件源事件
        FileEntry curFileEntry = scanners.scan(fileEntryFilter);
        checkAndNotify(rootEntry.getChildren(),curFileEntry.getChildren());
        rootEntry = curFileEntry;
        
        //System.out.println("耗时："+(System.currentTimeMillis()-t));
        for(FileEventListener temp:listeners){
            temp.onFinish();
        }
    }

    private void onDelete(FileEntry fileEntry)
    {
        FileEvent event = new FileEvent(fileEntry.getName(), fileEntry.getPath());
        for(FileEventListener listener:listeners){
            if(fileEntry.isDirectory()){
                listener.onDeleteDir(event);
            }else{
                listener.onDelete(event);
            }
        }
        for(FileEntry temp:fileEntry.getChildren()){
            onDelete(temp);
        }
    }

    private void onCreate(FileEntry fileEntry)
    {
        FileEvent event = new FileEvent(fileEntry.getName(), fileEntry.getPath());
        for(FileEventListener listener:listeners){
            if(fileEntry.isDirectory()){
                listener.onCreateDir(event);
            }else{
                listener.onCreate(event);
            }
        }
        for(FileEntry temp:fileEntry.getChildren()){
            onCreate(temp);
        }
    }
    /**
     * <p>方法描述</p>
     * @param orgTemp
     * @param fileEntry
     * @author blueice
     * @date 2018年9月27日 下午5:55:58
     */
     private void checkModify(FileEntry orgTemp, FileEntry fileEntry)
     {
         if(orgTemp.checkModifyBy(fileEntry)){
             //有改动
             FileEvent event = new FileEvent(fileEntry.getName(), fileEntry.getPath());
             for(FileEventListener listener:listeners){
                 if(fileEntry.isDirectory()){
                     listener.onChangeDir(event);
                 }else{
                     listener.onChange(event);
                 }
             }
         }
     }
}
