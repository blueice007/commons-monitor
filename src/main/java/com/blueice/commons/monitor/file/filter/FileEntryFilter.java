/**
 * @Description: TODO
 * @author blueice  
 * @date 2018年9月26日 下午4:47:21
 */
package com.blueice.commons.monitor.file.filter;

import com.blueice.commons.monitor.file.FileEntry;

/**
* @ClassName: FileEntryFilter
* @Description: TODO
* @author blueice
* @date 2018年9月26日 下午4:47:21
*
*/
public interface FileEntryFilter
{
    boolean accept(FileEntry fileEntry);
}
