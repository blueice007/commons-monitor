/**
 * @Description: TODO
 * @author blueice  
 * @date 2018年9月19日 下午3:47:04
 */
package com.blueice.commons.monitor.file;


/**
* @ClassName: FileEntry
* @Description: TODO
* @author blueice
* @date 2018年9月19日 下午3:47:04
*
*/
public class FileEntry implements Comparable<FileEntry>
{
    static final FileEntry[] EMPTY_CHILDREN = new FileEntry[0];
    private String path;
    private String name;
    // 若 目录不存在 children需为长度为0的空数组
    private FileEntry[] children = EMPTY_CHILDREN;
    private boolean isDirectory;
    
    /**
     *  file maybe change fields
     */
    private long lastModified;
    
    /**
     * 
    * <p>刷新文件实体的最后更新时间，判断是否有改动</p>
    * @param curFileEntry
    * @return true有改动，false未有改动
    * @author blueice
    * @date 2018年9月27日 下午3:54:15
     */
    public boolean checkModifyBy(FileEntry curFileEntry){
        return lastModified!=curFileEntry.lastModified;
    }
    
    public boolean isDirectory()
    {
        return isDirectory;
    }
    public void setDirectory(boolean isDirectory)
    {
        this.isDirectory = isDirectory;
    }
    public String getPath()
    {
        return path;
    }
    public void setPath(String path)
    {
        this.path = path;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    
    public FileEntry[] getChildren()
    {
        return children;
    }

    public void setChildren(FileEntry[] children)
    {
        this.children = children;
    }

    public long getLastModified()
    {
        return lastModified;
    }
    public void setLastModified(long lastModified)
    {
        this.lastModified = lastModified;
    }

    @Override
    public int compareTo(FileEntry target)
    {
        return this.name.compareTo(target.name);
    }
    
}
