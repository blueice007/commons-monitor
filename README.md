# commons-monitor
A file-monitoring tool supporting FTP and local file
该组件有2个features：
*组件是基于事件的，通过自定义文件listenner(实现FileEventListenner接口) 来响应监听目标目录中文件以及子目录的新增、删除、修改等事件；
*支持对本地文件目录的监听，同时也支持对远程ftp目录的监听，当然也可以自定义FileScanner实现来监听特定的文件对象

## start monitor for local file
```
import static com.blueice.commons.monitor.file.filter.FilterFactory.directroy;
import static com.blueice.commons.monitor.file.filter.FilterFactory.file;
import static com.blueice.commons.monitor.file.filter.FilterFactory.or;

import com.blueice.commons.monitor.file.FileEventMonitor;
import com.blueice.commons.monitor.file.FileEventSource;
import com.blueice.commons.monitor.file.filter.FileEntryFilter;
import com.blueice.commons.monitor.file.listener.DefaultFileEventListenner;
import com.blueice.commons.monitor.file.scanner.FileScanner;
import com.blueice.commons.monitor.file.scanner.LocalFileScanner;

public class LocalFileMonitorLancher
{
    public static void main(String[] args)
    {
        
        FileEntryFilter filter = or(directroy(), file());
        FileScanner scaner = new LocalFileScanner("D://demo");
        // 监控扫描间隔时间 单位s
        final int interval = 5;
        FileEventSource source = new FileEventSource(scaner, filter);
        source.addEventListener(new DefaultFileEventListenner());
        FileEventMonitor monitor = new FileEventMonitor(interval, new FileEventSource[] { source });
        monitor.startup();
    }
}
```
## start monitor for ftp file
```

import static com.blueice.commons.monitor.file.filter.FilterFactory.directroy;
import static com.blueice.commons.monitor.file.filter.FilterFactory.file;
import static com.blueice.commons.monitor.file.filter.FilterFactory.or;

import com.blueice.commons.monitor.file.FileEventMonitor;
import com.blueice.commons.monitor.file.FileEventSource;
import com.blueice.commons.monitor.file.filter.FileEntryFilter;
import com.blueice.commons.monitor.file.listener.DefaultFileEventListenner;
import com.blueice.commons.monitor.file.scanner.FileScanner;
import com.blueice.commons.monitor.file.scanner.FtpFileScanner;


public class FtpFileMonitorLancher
{
    public static void main(String[] args)
    {
        FileEntryFilter filter = or(directroy(), file());
        final String ftpIp = "192.168.1.1";
        final int ftpPort = 21;
        final String ftpUser = "root";
        final String ftpPwd = "root";
        final String path = "demo";
        // 监控扫描间隔时间 单位s
        final int interval = 5;
        FileScanner scaner = new FtpFileScanner(ftpIp, ftpPort, ftpUser, ftpPwd, path);
        FileEventSource source = new FileEventSource(scaner, filter);
        source.addEventListener(new DefaultFileEventListenner());
        FileEventMonitor monitor = new FileEventMonitor(interval, new FileEventSource[] { source });
        monitor.startup();
    }
    
}

```
