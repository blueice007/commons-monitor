/**
 * @Description: TODO
 * @author blueice  
 * @date 2018年9月19日 下午3:53:26
 */
package com.blueice.commons.monitor.file.scanner;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.blueice.commons.monitor.file.FileEntry;
import com.blueice.commons.monitor.file.filter.FileEntryFilter;

/**
* @ClassName: LocalFileScanner
* @Description: 本地文件扫描器
* @author blueice
* @date 2018年9月19日 下午3:53:26
*
*/
public class LocalFileScanner implements FileScanner
{
    private final String path;
    
    public LocalFileScanner(String path)
    {
        if(path==null||path.trim().length()==0){
            throw new IllegalArgumentException("The path must not be empty");
        }
        this.path = path;
    }

    public String getPath()
    {
        return path;
    }

    @Override
    public FileEntry scan(final FileEntryFilter entryFilter)
    {
        File file = new File(path);
        FileEntry rootEntry = buildFileEntry(file);
        if(!file.exists()){
            return rootEntry;
        }
        buildFileEntry(rootEntry,file,entryFilter);
        
        return rootEntry;
    }

    private FileEntry buildFileEntry(File file)
    {
        FileEntry result = new FileEntry();
        result.setPath(file.getPath());
        result.setName(file.getName());
        if(!file.exists()){
            return result;
        }
        result.setDirectory(file.isDirectory());
        result.setLastModified(file.lastModified());
        return result;
    }

    private void buildFileEntry(FileEntry parent,File file, FileEntryFilter entryFilter)
    {
        File[] files = file.listFiles();
        List<FileEntry> children = new ArrayList<FileEntry>();
        for(File temp:files){
            FileEntry fileEntry = buildFileEntry(temp);
            if(entryFilter.accept(fileEntry)){
                if(temp.isDirectory()){
                    buildFileEntry(fileEntry,temp,entryFilter);
                }
                children.add(fileEntry);
            }
        }
        Collections.sort(children);
        parent.setChildren(children.toArray(new FileEntry[children.size()]));
    }
    
}
