# commons-monitor
A file-monitoring tool supporting FTP and local file
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
