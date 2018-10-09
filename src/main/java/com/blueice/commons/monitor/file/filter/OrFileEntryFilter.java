package com.blueice.commons.monitor.file.filter;

import java.util.List;

import com.blueice.commons.monitor.file.FileEntry;

/**
* @ClassName: OrFileEntryFilter
* @Description: TODO
* @author blueice
* @date 2018年9月27日 上午9:46:08
*
*/
public class OrFileEntryFilter implements FileEntryFilter
{
    private List<FileEntryFilter> orFilters;
    
    
    public OrFileEntryFilter()
    {
    }

    public OrFileEntryFilter(List<FileEntryFilter> orFilters)
    {
        this.orFilters = orFilters;
    }


    public List<FileEntryFilter> getOrFilters()
    {
        return orFilters;
    }


    public void setOrFilters(List<FileEntryFilter> orFilters)
    {
        this.orFilters = orFilters;
    }


    @Override
    public boolean accept(FileEntry fileEntry)
    {
        if(this.orFilters!=null&&this.orFilters.size()>0){
            for(FileEntryFilter tempFilter:orFilters){
                if(tempFilter.accept(fileEntry)){
                    return true;
                }
            }
            return false;
        }else{
            return true;
        }
    }
    
}
