package com.midland.web.util.sftp;

import com.jcraft.jsch.*;
import com.midland.config.SftpProperties;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Properties;
import java.util.Vector;
/** 
* 类说明 sftp工具类
*/
@Component
public class SFTPClient {
    private transient Logger log = LoggerFactory.getLogger(this.getClass());  
    
    private  ChannelSftp sftp;
    private static SFTPClient sftpClient;
    private static SftpProperties sftpProperties = SftpProperties.getInstance();


    private Session session;


    private SFTPClient(){}



    public static SFTPClient getInstance(){
        if (sftpClient==null){
            synchronized (SFTPClient.class){
                if (sftpClient==null){
                    sftpClient=new SFTPClient();
                }
            }
        }
        return sftpClient;
    }


    public void loginUploadLogout(String directory, String sftpFileName, InputStream input) throws SftpException {
        login();
        upload(directory,sftpFileName,input);
        logout();
    }


    /** 
     * 连接sftp服务器 
     */  
    public void login(){  
        try {

            JSch jsch = new JSch();  
            if (StringUtils.isNotEmpty(sftpProperties.getPrivateKey())) {
                jsch.addIdentity(sftpProperties.getPrivateKey());// 设置私钥
            }  
    
            session = jsch.getSession(sftpProperties.getUsername(), sftpProperties.getHost(), sftpProperties.getPort());
           
            if (sftpProperties.getPassword() != null) {
                session.setPassword(sftpProperties.getPassword());
            }  
            Properties config = new Properties();  
            config.put("StrictHostKeyChecking", "no");  
                
            session.setConfig(config);  
            session.connect();  
              
            Channel channel = session.openChannel("sftp");  
            channel.connect();  
    
            sftp = (ChannelSftp) channel;  
        } catch (JSchException e) {  
            e.printStackTrace();
        }  
    }    
    
    /** 
     * 关闭连接 server  
     */  
    public void logout(){  
        if (sftp != null) {  
            if (sftp.isConnected()) {  
                sftp.disconnect();  
            }  
        }  
        if (session != null) {  
            if (session.isConnected()) {  
                session.disconnect();  
            }  
        }  
    }

    /**
     * 根目录取配置里的 basePath
     * @param directory
     * @param sftpFileName
     * @param input
     * @throws SftpException
     */
    public void upload(String directory, String sftpFileName, InputStream input) throws SftpException{
       upload(sftpProperties.getBasePath(),directory,sftpFileName,input);
    }


    /**  
     * 将输入流的数据上传到sftp作为文件。文件完整路径=basePath+directory
     * @param basePath  服务器的基础路径 
     * @param directory  上传到该目录  
     * @param sftpFileName  sftp端文件名  
     * @param input   输入流
     */  
    public void upload(String basePath,String directory, String sftpFileName, InputStream input) throws SftpException{  
        try {   
            sftp.cd(basePath);
            sftp.cd(directory);  
        } catch (SftpException e) { 
            //目录不存在，则创建文件夹
            String [] dirs=directory.split("/");
            String tempPath=basePath;
            for(String dir:dirs){
                if(null== dir || "".equals(dir)) continue;
            	tempPath+="/"+dir;
            	try{ 
            		sftp.cd(tempPath);
            	}catch(SftpException ex){
            		sftp.mkdir(tempPath);
            		sftp.cd(tempPath);
            	}
            }
        }  
        sftp.put(input, sftpFileName);  //上传文件
    } 
    

    /** 
     * 下载文件。
     * @param directory 下载目录  
     * @param downloadFile 下载的文件 
     * @param saveFile 存在本地的路径 
     */    
    public void download(String directory, String downloadFile, String saveFile) throws SftpException, FileNotFoundException{  
        if (directory != null && !"".equals(directory)) {  
            sftp.cd(directory);  
        }  
        File file = new File(saveFile);  
        sftp.get(downloadFile, new FileOutputStream(file));  
    }  
    
    /**  
     * 下载文件 
     * @param directory 下载目录 
     * @param downloadFile 下载的文件名 
     * @return 字节数组 
     */  
    public byte[] download(String directory, String downloadFile) throws SftpException, IOException{  
        if (directory != null && !"".equals(directory)) {  
            sftp.cd(directory);  
        }  
        InputStream is = sftp.get(downloadFile);  
          
        byte[] fileData = IOUtils.toByteArray(is);  
          
        return fileData;  
    }  
    
    
    /** 
     * 删除文件 
     * @param directory 要删除文件所在目录 
     * @param deleteFile 要删除的文件 
     */  
    public void delete(String directory, String deleteFile) throws SftpException{  
        sftp.cd(directory);  
        sftp.rm(deleteFile);  
    }  
    
    
    /** 
     * 列出目录下的文件 
     * @param directory 要列出的目录 
     */
    public Vector<?> listFiles(String directory) throws SftpException {  
        return sftp.ls(directory);  
    }  
      
    //上传文件测试
    public static void main(String[] args) throws SftpException, IOException {  
        SFTPClient sftp = new SFTPClient();
        sftp.login();  
        File file = new File("D:\\图片\\t0124dd095ceb042322.jpg");  
        InputStream is = new FileInputStream(file);  
          
        sftp.upload("基础路径","文件路径", "test_sftp.jpg", is);  
        sftp.logout();  
    }

    public SftpProperties getSftpProperties() {
        return sftpProperties;
    }
}