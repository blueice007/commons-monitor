package com.blueice.commons.monitor.file.filter;

import com.blueice.commons.monitor.file.FileEntry;

/**
* @ClassName: SuffixFileEntryFilter
* @Description: 文件名后缀过滤器
* @author blueice
* @date 2018年9月27日 上午9:52:01
*
*/
public class SuffixFileEntryFilter implements FileEntryFilter
{
    /**
     * 过滤后缀数组
     */
    private String[] suffixs;
    /**
     * 是否大小些敏感
     */
    private boolean sensitivity;
    
    
    /**
     * 默认 大小写敏感
     */
    public SuffixFileEntryFilter(String suffix)
    {
        if(suffix==null){
            throw new IllegalArgumentException("The suffix must not be null");
        }
        this.suffixs = new String[]{suffix};
        this.sensitivity = true;
    }

    public SuffixFileEntryFilter(String[] suffixs, boolean sensitivity)
    {
        if(suffixs==null){
            throw new IllegalArgumentException("The suffixs-array must not be null");
        }
        for(String suffixTemp:suffixs){
            if(suffixTemp==null){
                throw new IllegalArgumentException("The element of suffixs-array must not be null");
            }
        }
        
        this.suffixs = new String[suffixs.length];
        System.arraycopy(suffixs, 0, this.suffixs, 0, suffixs.length);
        this.sensitivity = sensitivity;
    }

    /**
     * 默认 大小写敏感
     * @param suffixs
     */
    public SuffixFileEntryFilter(String[] suffixs)
    {
        this(suffixs,true);
    }

    public String[] getSuffixs()
    {
        return suffixs;
    }

    public void setSuffixs(String[] suffixs)
    {
        this.suffixs = suffixs;
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
        String fileName = fileEntry.getName();
        for(String nameTemp:suffixs){
            if(fileName.regionMatches(!sensitivity, 
                    fileName.length()-nameTemp.length(), 
                    nameTemp, 0, nameTemp.length()))
            {
                return true;
            }
        }
        
        return false;
    }
    
}
