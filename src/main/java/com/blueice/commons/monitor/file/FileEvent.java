package com.blueice.commons.monitor.file;


/**
* @Description: TODO
* @author blueice
* @date 2018年9月27日 下午5:33:52
*
*/
public class FileEvent
{
    private String fileName;
    private String filePath;
    
    public FileEvent()
    {
    }
    public FileEvent(String fileName, String filePath)
    {
        this.fileName = fileName;
        this.filePath = filePath;
    }
    public String getFileName()
    {
        return fileName;
    }
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }
    public String getFilePath()
    {
        return filePath;
    }
    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }
    
    
}
