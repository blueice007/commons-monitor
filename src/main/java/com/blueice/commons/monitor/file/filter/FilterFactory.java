package com.blueice.commons.monitor.file.filter;

import java.util.ArrayList;
import java.util.Arrays;


/**
* @ClassName: FileEntryFilterFactory
* @Description: TODO
* @author blueice
* @date 2018年9月26日 下午5:06:58
*
*/
public class FilterFactory
{
    public static FileEntryFilter and(FileEntryFilter... entryFilters){
        return new AndFileEntryFilter(new ArrayList<FileEntryFilter>(Arrays.asList(entryFilters)));
    }
    public static FileEntryFilter or(FileEntryFilter... entryFilters){
        return new OrFileEntryFilter(new ArrayList<FileEntryFilter>(Arrays.asList(entryFilters)));
    }
    
    public static FileEntryFilter suffix(boolean sensitivity,String... suffixs){
        return new SuffixFileEntryFilter(suffixs, sensitivity);
    }
    
    /**
     * 
    * <p>默认大小写敏感</p>
    * @param suffixs
    * @return
    * @author blueice
    * @date 2018年9月27日 上午10:07:01
     */
    public static FileEntryFilter suffix(String... suffixs){
        return new SuffixFileEntryFilter(suffixs);
    }
    
    public static FileEntryFilter prefix(boolean sensitivity,String... prefixs){
        return new PrefixFileEntryFilter(prefixs, sensitivity);
    }
    public static FileEntryFilter prefix(String... prefixs){
        return new PrefixFileEntryFilter(prefixs);
    }
    
    public static FileEntryFilter file(){
        return new FileFileEntryFilter();
    }
    
    public static FileEntryFilter directroy(){
        return new DirectoryEntryFilter();
    }
    
    public static FileEntryFilter getFilter(String className) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
        if(className==null||className.trim().length()<=0){
            throw new IllegalArgumentException("The className must not be empty!");
        }
        Object obj = Class.forName(className).newInstance();
        if(obj instanceof FileEntryFilter){
            return (FileEntryFilter)obj;
        }else{
            throw new IllegalArgumentException("The Class is not implements FileEntryFilter Interface!");
        }
    }
    
    public static FileEntryFilter getFilter(Class<? extends FileEntryFilter> clazz) throws InstantiationException, IllegalAccessException{
        if(clazz==null){
            throw new IllegalArgumentException("The clazz must not be null!");
        }
        return clazz.newInstance();
    }
}
