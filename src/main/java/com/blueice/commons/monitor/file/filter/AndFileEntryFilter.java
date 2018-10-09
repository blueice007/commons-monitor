package com.blueice.commons.monitor.file.filter;

import java.util.List;

import com.blueice.commons.monitor.file.FileEntry;

/**
* @ClassName: AndFileEntryFilter
* @Description: TODO
* @author blueice
* @date 2018年9月26日 下午5:13:13
*
*/
public class AndFileEntryFilter implements FileEntryFilter
{
    private List<FileEntryFilter> andFilters;
    
    public AndFileEntryFilter(){
        
    }
    
    public AndFileEntryFilter(List<FileEntryFilter> andFilters)
    {
        this.andFilters = andFilters;
    }


    @Override
    public boolean accept(FileEntry fileEntry)
    {
        if(this.andFilters!=null&&this.andFilters.size()>0){
            for(FileEntryFilter tempFilter:andFilters){
                if(!tempFilter.accept(fileEntry)){
                    return false;
                }
            }
        }
        return true;
    }

    public List<FileEntryFilter> getAndFilters()
    {
        return andFilters;
    }

    public void setAndFilters(List<FileEntryFilter> andFilters)
    {
        this.andFilters = andFilters;
    }
    
}
