package com.midland.core.backup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * MySQL数据库备份
 * 
 */
public class MySQLDatabaseBackup {

	/**
	 * MySQL数据库备份
	 * 
	 * @param hostIP
	 *            备份数据库IP
	 * @param userName
	 *            备份数据库帐号
	 * @param password
	 *            备份数据库密码
	 * @param backupAbspath
	 *            备份文件路径
	 * @param fileName
	 *            备份文件名称
	 * @param databaseName
	 *            备份数据库名称
	 * @param runPath
	 *            MYSQL执行相对路径
	 * @return 返回true表示导出成功，否则返回false
	 * @throws InterruptedException
	 */
	public static boolean databaseBackup(String hostIP, String userName, String password, String backupAbspath, String fileName, String databaseName,
			String runPath) throws InterruptedException {
		File saveFile = new File(backupAbspath);
		if (!saveFile.exists()) {// 如果目录不存在
			saveFile.mkdirs();// 创建文件夹
		}
		if (!backupAbspath.endsWith(File.separator)) {
			backupAbspath = backupAbspath + File.separator;
		}

		PrintWriter printWriter = null;
		BufferedReader bufferedReader = null;
		try {
			printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(backupAbspath + fileName), "utf8"));
			Process process = Runtime.getRuntime().exec(runPath + "mysqldump -h" + hostIP + " -u" + userName + " -p" + password + " " + databaseName);
			InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(), "utf8");
			bufferedReader = new BufferedReader(inputStreamReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				printWriter.println(line);
			}
			printWriter.flush();
			if (process.waitFor() == 0) {// 0 表示线程正常终止。
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
				if (printWriter != null) {
					printWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * MySQL数据库还原
	 * 
	 * @param hostIP
	 *            备份数据库IP
	 * @param userName
	 *            备份数据库帐号
	 * @param password
	 *            备份数据库密码
	 * @param backupAbsPath
	 *            备份文件路径
	 * @param databaseName
	 *            备份数据库名称
	 * @param runPath
	 *            MYSQL执行相对路径
	 * @return 返回true表示导出成功，否则返回false
	 * @throws IOException
	 */
	public static boolean databaseRestore(String hostIP, String userName, String password, String backupAbsPath, String databaseName, String runPath)
			throws IOException {
		
		boolean result = true;
		OutputStream outputStream = null;
		BufferedReader br = null;
		OutputStreamWriter writer = null;
		try {
			
			Runtime runtime = Runtime.getRuntime();
			Process process = runtime.exec(runPath + "mysql -h" + hostIP + " -u" + userName + " -p" + password + " --default-character-set=utf8 "
					+ databaseName);
			outputStream = process.getOutputStream();
			br = new BufferedReader(new InputStreamReader(new FileInputStream(backupAbsPath), "utf-8"));
			String str = null;
			StringBuffer sb = new StringBuffer();
			while ((str = br.readLine()) != null) {
				sb.append(str + "\r\n");
			}
			str = sb.toString();
			writer = new OutputStreamWriter(outputStream, "utf-8");
			writer.write(str);
			writer.flush();

		} catch (UnsupportedEncodingException e) {
			result = false;
			throw e;
		} catch (FileNotFoundException e) {
			result = false;
			throw e;
		} catch (IOException e) {
			result = false;
			throw e;
		} finally {
			outputStream.close();
			br.close();
			writer.close();
		}
		return result;
	}

}