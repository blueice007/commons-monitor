package com.blueice.commons.monitor.filter;

import junit.framework.TestCase;

import com.blueice.commons.monitor.file.FileEntry;
import com.blueice.commons.monitor.file.filter.FileEntryFilter;
import com.blueice.commons.monitor.file.filter.FilterFactory;

/**
* @Description: TODO
* @author blueice
* @date 2018年9月27日 上午11:07:05
*
*/
public class PrefixFileEntryFilterTest extends TestCase
{
    public void testAcceptTrue(){
        FileEntry fileEntry = new FileEntry();
        fileEntry.setName("test-lyq.txt");
        FileEntryFilter filter = FilterFactory.prefix("test");
        assertTrue(filter.accept(fileEntry));
    }
    public void testAcceptFalse(){
        FileEntry fileEntry = new FileEntry();
        fileEntry.setName("atest-lyq.txt");
        FileEntryFilter filter = FilterFactory.prefix("test");
        assertFalse(filter.accept(fileEntry));
    }
}
