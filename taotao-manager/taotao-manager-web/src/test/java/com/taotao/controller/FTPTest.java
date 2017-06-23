package com.taotao.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import com.sun.tools.classfile.Annotation.element_value;
import com.sun.tools.doclets.internal.toolkit.util.DocFinder.Input;
import com.taotao.common.utils.FtpUtil;

public class FTPTest {

	@Test
	public void testFtpClient() throws Exception {
		// 创建一个FtpClient对象
		FTPClient ftpClient = new FTPClient();
		// 创建ftp连接。默认是21端口
		ftpClient.connect("192.168.83.133", 21);
		// 登录ftp服务器，使用用户名和密码
		ftpClient.login("ftpuser", "ftpuser");
		// 上传文件。
		// 读取本地文件
		FileInputStream inputStream = new FileInputStream(new File("d:\\scp\\login.jpg"));
		// 设置上传的路径
		ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
		ftpClient.enterLocalPassiveMode();
		// 修改上传文件的格式
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		// 第一个参数：服务器端文档名
		// 第二个参数：上传文档的inputStream
		ftpClient.storeFile("5.jpg", inputStream);
		// 关闭连接
		ftpClient.logout();
	}

	@Test
	public void testFtpUtil() throws Exception {
		// File file=new
		// File("‪D:"+File.separator+"scp"+File.separator+"login.jpg");
		File file = new File("d:\\scp\\login.jpg");
		FileInputStream inputStream = new FileInputStream(file);
		FtpUtil.uploadFile("192.168.83.133", 21, "ftpuser", "ftpuser", "/home/ftpuser/www/images", "/2015/04/04",
				"0.jpg", inputStream);

	}

	@Test
	public void testtxt() throws Exception {
		File file = new File("d:\\scp\\1.txt");
		// if(file.exists()){
		InputStream input = new FileInputStream(file);
		byte data[] = new byte[1024];
		int foot = 0;
		int temp = 0;
		while ((temp = input.read()) != -1) {
			data[foot++] = (byte) temp;
		}
		input.close();
		System.out.println(new String(data, 0, foot));
		// }
	}

}
