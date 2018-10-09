package com.blueice.commons.monitor.file.filter;

import com.blueice.commons.monitor.file.FileEntry;

/**
* @Description: TODO
* @author blueice
* @date 2018年9月27日 下午6:49:23
*
*/
public class FileFileEntryFilter implements FileEntryFilter
{
    
    @Override
    public boolean accept(FileEntry fileEntry)
    {
        return !fileEntry.isDirectory();
    }
    
}
