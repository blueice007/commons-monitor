package com.blueice.commons.monitor.file.scanner;

import java.io.IOException;
//import java.io.PrintWriter;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.blueice.commons.monitor.file.FileEntry;
import com.blueice.commons.monitor.file.filter.FileEntryFilter;
import com.blueice.commons.utils.Util;

/**
* @Description: linux下 ftp文件扫描器
* @author blueice
* @date 2018年9月28日 上午10:36:19
*
*/
public class FtpFileScanner implements FileScanner
{
    public static final String separator = "/";
    private String ftpIp;
    private int ftpPort=21;
    private String ftpUser;
    private String ftpPwd;
    /**
     *  监控 相对目录
     */
    private String path;
    private final boolean keepAlive;
    private boolean hasLogin = false;
    private FTPClient ftpClient;
    
    public FtpFileScanner()
    {
        this.keepAlive=false;
    }

    public FtpFileScanner(String ftpIp, int ftpPort, String ftpUser, String ftpPwd, String path,boolean keepAlive)
    {
        this.keepAlive=keepAlive;
        this.ftpIp = ftpIp;
        this.ftpPort = ftpPort;
        this.ftpUser = ftpUser;
        this.ftpPwd = ftpPwd;
        this.path = path.startsWith("/")?path.substring(1):path;
    }
    public FtpFileScanner(String ftpIp, int ftpPort, String ftpUser, String ftpPwd, String path)
    {
        this(ftpIp, ftpPort, ftpUser, ftpPwd, path,false);
    }

    @Override
    public FileEntry scan(FileEntryFilter entryFilters)
    {
        FileEntry rootEntry = new FileEntry();
        rootEntry.setName(path);
        try{
            if(!hasLogin){
                login();
                hasLogin = true;
            }
            String homePath = ftpClient.printWorkingDirectory();
            if(ftpClient.changeWorkingDirectory(path)){
                rootEntry.setDirectory(true);
                rootEntry.setPath(ftpClient.printWorkingDirectory());
                FTPFile[] ftpFiles = ftpClient.listFiles();
                if(ftpFiles!=null&&ftpFiles.length>0)
                    buildFileEntry(rootEntry,ftpFiles,entryFilters);
                ftpClient.changeWorkingDirectory(homePath);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(ftpClient!=null){
                if(!keepAlive){
                    logout(ftpClient);
                    ftpClient = null;
                    hasLogin = false;
                }
            }
        }
        return rootEntry;
    }

    /**
    * <p>方法描述</p>
    * @return
    * @author blueice
    * @date 2018年9月28日 下午2:16:16
    */
    private FileEntry buildFileEntry(FTPFile ftpFile)
    {
        FileEntry fileEntry = new FileEntry();
        
        if(ftpFile!=null){
            fileEntry.setName(ftpFile.getName());
            fileEntry.setDirectory(ftpFile.isDirectory());
            fileEntry.setLastModified(ftpFile.getTimestamp().getTimeInMillis());
        }else{
            fileEntry.setName("--");
        }
        return fileEntry;
    }

    /**
    * <p>方法描述</p>
    * @param parent
    * @param entryFilters
    * @author blueice
     * @param ftpFiles 
     * @throws IOException 
    * @date 2018年9月28日 下午2:14:46
    */
    private void buildFileEntry(FileEntry parent, FTPFile[] ftpFiles, FileEntryFilter entryFilters) throws IOException
    {
        List<FileEntry> children = new ArrayList<FileEntry>();
        for(FTPFile fileTemp:ftpFiles){
            //if("./".equals(fileTemp.getName())||"../")
            FileEntry fileEntry = buildFileEntry(fileTemp);
            if(entryFilters.accept(fileEntry)){
                fileEntry.setPath(ftpClient.printWorkingDirectory()+separator+fileTemp.getName());
                if(fileTemp.isDirectory()){
                    ftpClient.changeWorkingDirectory(ftpClient.printWorkingDirectory()+separator+fileTemp.getName());
                    buildFileEntry(fileEntry,ftpClient.listFiles(),entryFilters);
                    ftpClient.changeToParentDirectory();
                }
                children.add(fileEntry);
            }
        }
        Collections.sort(children);
        parent.setChildren(children.toArray(new FileEntry[children.size()]));
    }

    /**
    * <p>方法描述</p>
    * @return
    * @author blueice
     * @throws IOException 
     * @throws SocketException 
    * @date 2018年9月28日 上午11:12:38
    */
    private void login() throws SocketException, IOException
    {
        if(Util.isEmpty(ftpIp)||Util.isEmpty(ftpUser)
                ||Util.isEmpty(ftpPwd)){
            throw new IllegalArgumentException("The param of ftp must not be empty!");
        }
        
        ftpClient = new FTPClient();
        //设置字符集
        ftpClient.setControlEncoding("utf-8");
        //设置连接超时时长
        ftpClient.setConnectTimeout(5*1000);
        //ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        ftpClient.connect(ftpIp, ftpPort);
        ftpClient.login(ftpUser, ftpPwd);
        ftpClient.enterLocalPassiveMode();
    }
    
    private void logout(FTPClient ftpClient){
        
        if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode()))
        {
            try
            {
                ftpClient.logout();
                ftpClient.disconnect();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            
        }
    }
    
}
