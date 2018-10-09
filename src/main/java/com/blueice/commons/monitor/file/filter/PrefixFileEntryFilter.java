package com.blueice.commons.monitor.file.filter;

import com.blueice.commons.monitor.file.FileEntry;

/**
* @ClassName: PrefixFileEntryFilter
* @Description: 文件名前缀过滤器
* @author blueice
* @date 2018年9月27日 上午9:52:01
*
*/
public class PrefixFileEntryFilter implements FileEntryFilter
{
    /**
     * 过滤前缀数组
     */
    private String[] prefixs;
    /**
     * 是否大小些敏感
     */
    private boolean sensitivity;
    
    
    /**
     * 默认 大小写敏感
     */
    public PrefixFileEntryFilter(String prefix)
    {
        if(prefix==null){
            throw new IllegalArgumentException("The prefix must not be null");
        }
        this.prefixs = new String[]{prefix};
        this.sensitivity = true;
    }

    public PrefixFileEntryFilter(String[] prefixs, boolean sensitivity)
    {
        if(prefixs==null){
            throw new IllegalArgumentException("The prefixs-array must not be null");
        }
        for(String prefixTemp:prefixs){
            if(prefixTemp==null){
                throw new IllegalArgumentException("The element of prefixs-array must not be null");
            }
        }
        
        this.prefixs = prefixs;
        this.sensitivity = sensitivity;
    }

    /**
     * 默认 大小写敏感
     * @param suffixs
     */
    public PrefixFileEntryFilter(String[] prefixs)
    {
        this(prefixs,true);
    }


    public String[] getPrefixs()
    {
        return prefixs;
    }

    public void setPrefixs(String[] prefixs)
    {
        this.prefixs = prefixs;
    }

    public boolean isSensitivity()
    {
        return sensitivity;
    }

    public void setSensitivity(boolean sensitivity)
    {
        this.sensitivity = sensitivity;
    }

    @Override
    public boolean accept(FileEntry fileEntry)
    {
        if(fileEntry!=null){
            String fileName = fileEntry.getName();
            for(String prefixTemp:prefixs){
                if(fileName.regionMatches(!sensitivity, 0, prefixTemp, 0, prefixTemp.length())){
                    return true;
                }
            }
        }
        return false;
    }
    
}
