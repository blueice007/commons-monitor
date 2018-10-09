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
public class SuffixFileEntryFilterTest extends TestCase
{
    public void testAcceptTrue(){
        FileEntry fileEntry = new FileEntry();
        fileEntry.setName("test.txt");
        FileEntryFilter suffixFilter = FilterFactory.suffix(".txt");
        assertTrue(suffixFilter.accept(fileEntry));
    }
    public void testAcceptFalse(){
        FileEntry fileEntry = new FileEntry();
        fileEntry.setName("test.java");
        FileEntryFilter suffixFilter = FilterFactory.suffix(".txt");
        assertFalse(suffixFilter.accept(fileEntry));
    }
    
    public void testAcceptSensitivity(){
        FileEntry fileEntry = new FileEntry();
        fileEntry.setName("test.txt");
        FileEntryFilter suffixFilter = FilterFactory.suffix(true,".TxT");
        assertFalse(suffixFilter.accept(fileEntry));
    }
}
