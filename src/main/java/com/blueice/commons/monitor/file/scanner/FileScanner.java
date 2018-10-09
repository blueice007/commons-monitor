/**
 * @Description: TODO
 * @author blueice 
 * @date 2018年9月19日 下午3:51:18
 */
package com.blueice.commons.monitor.file.scanner;

import com.blueice.commons.monitor.file.FileEntry;
import com.blueice.commons.monitor.file.filter.FileEntryFilter;

/**
* @ClassName: FileScanner
* @Description: TODO
* @author blueice
* @date 2018年9月19日 下午3:51:18
*
*/
public interface FileScanner
{
    /**
     * 
    * <p>根据过滤器获取文件目录中的子文件(按文件名称顺序排序)，
    * 若扫描目录不存在则返回的FileEntry的 children为长度为0的空数组</p>
    * @param entryFilters 文件过滤器 可为null表示不加过滤器
    * @return
    * @author blueice
    * @date 2018年9月27日 下午4:52:27
     */
    FileEntry scan(final FileEntryFilter entryFilters);
}
