package com.blueice.commons.monitor.filter;

import com.blueice.commons.monitor.file.FileEntry;
import com.blueice.commons.monitor.file.filter.FileEntryFilter;
import static com.blueice.commons.monitor.file.filter.FilterFactory.*;

import junit.framework.TestCase;

/**
* @Description: TODO
* @author blueice
* @date 2018年9月27日 上午11:33:20
*
*/
public class OrFileEntryFilterTest extends TestCase
{
    public void testAcceptTrue(){
        FileEntry fileEntry = new FileEntry();
        fileEntry.setName("test.txt");
        FileEntryFilter filter = or(suffix(".txt"),prefix("atest"));
        assertTrue(filter.accept(fileEntry));
        
    }
    
    public void testAcceptFalse(){
        FileEntry fileEntry = new FileEntry();
        fileEntry.setName("atest.txt");
        FileEntryFilter filter = or(suffix(".atxt"),prefix("test"));
        assertFalse(filter.accept(fileEntry));
        
    }
}
