package com.bluemobi.core.util;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

//import Decoder.BASE64Decoder;
import sun.misc.BASE64Decoder;  
import sun.misc.BASE64Encoder;

public class UploadImgUtil {

	public static String upload(Map<String, MultipartFile> fileMap, String path) throws IOException {

		Map<String, String> map = new HashMap<>();
		for (String key : fileMap.keySet()) {
			MultipartFile file = fileMap.get(key);
			String fileName = file.getOriginalFilename();
			SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
			String format1 = format.format(new Date());
			int i = RandomUtils.nextInt(1000);
			String newfileName = null;
			if (StringUtils.isNotBlank(fileName)) {
				newfileName = format1 + StringUtils.leftPad(i + "", 2, '0') + fileName;
			} else {
				newfileName = "";
			}
			File fl = new File(path + "head/" + newfileName);
			InputStream files = file.getInputStream();
			if (!fl.getParentFile().exists()) {
				fl.getParentFile().mkdirs();
			}

			if (!fl.exists()) {
				fl.createNewFile();
			}

			if (StringUtils.isNotBlank(fileName)) {
				// pc端图片不做处理
				file.transferTo(fl);
				return "/upload/" + "head/" + newfileName;
				// 手机端将图片尺寸改变
			}

		}
		return null;
	};

	public static String uploadImgFile(MultipartFile file, String path) {
		String str = File.separator + "upload" + File.separator;
		if (file == null)
			return str;
		try {
			String fileName = file.getOriginalFilename();
			String prefix = fileName.substring(fileName.lastIndexOf("."));
			fileName = System.currentTimeMillis() + prefix;
			File fl = new File(path + fileName);
			if (!fl.getParentFile().exists()) {
				fl.getParentFile().mkdirs();
			}
			if (!fl.exists()) {
				fl.createNewFile();
			}
			if (StringUtils.isNotBlank(fileName)) {
				file.transferTo(fl);
			}
			str = str + fileName;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}

	// base64字符串转化成图片

	public static String GenerateImage(Map<String, String> map) { // 对字节数组字符串进行Base64解码并生成图片
		
		if (StringUtils.isEmpty(map.get("userName")) || StringUtils.isEmpty(map.get("imgContent"))) // 图像数据为空
			return "";
		String imgContent = map.get("imgContent");
		String userName = map.get("userName");
		String path = map.get("path");
		String oldImg = map.get("oldImg");
		
		String type = imgContent.substring(imgContent.indexOf("/")+1,imgContent.indexOf(";"));
		String img = imgContent.substring(imgContent.indexOf(",")+1,imgContent.length());
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			if(StringUtils.isNotEmpty(oldImg)){
				boolean bool = FileUtils.deleteQuietly(new File("/home/"+oldImg));
			}
			// Base64解码
			byte[] b = decoder.decodeBuffer(img);

			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			String fileName = "head/"+userName+"."+type;// 新生成的图片;
			String imgFilePath = path + fileName ;
			File fl = new File(imgFilePath);
			if (!fl.getParentFile().exists()) {
				fl.getParentFile().mkdirs();
			}
			if (!fl.exists()) {
				fl.createNewFile();
			}
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			return "/upload/"+fileName;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static void main(String[] args) {
		/*	UploadImgUtil u = new UploadImgUtil();
			String  str = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACkAAAAaCAYAAAAqjnX1AAAD4klEQVRYhc2XSSj1bRjGL3MyhZBhQZkyRGFBMksiIYoispCyoMhGRIbMZR4TkYiQkiELc8YIJRmizCELK8Lbfdc5/c9neM9xzuf7rs3p/j/T7xnu63mOkpub2zvkkIWFBfLz81FYWIiLiwt5uvpSyvI0trS0RHt7O4N2dHRw/G/ox5C2trZoa2vD1tYWIiIisLCwgNbWVlhbWyuSj6ViZmZWIGsjBwcHNDY2YmVlhbf69fWVIXV0dJCTk4O1tTXc3d39d5Curq6or69nqIKCAry9vYnLNjY28PLywuCbm5u4vb39fUgPDw/U1dVhZmYGRUVFEoAi7e3tMRyV7+zs4Pr6+vcgvb29UV1djcnJSRQXF+P9/WtTODo6wsnJCUpLS3FwcCB31kuVOAEBAaisrMT4+PgHQHNzc04gynChFhcXkZWVxdZEE5RHf13J0NBQBhsZGUFZWZlEGVkOAVJGBwYGYmlpCY+Pj+Lym5sbTi7aetr209PTH0F+u5KRkZGcHENDQygvL5coIzCyHCMjI47pVwQsFG19amoq0tPTERISoljIuLg45ObmYmBggLdaKHt7ewY0NDTkWGQ3BgYG/J3Khbq6umLQhIQEhIeHKwYyOTkZ2dnZ6O3tRU1NjUSZi4sLWlpaoKenx/H09DQPPDU1xTF9p3KqJ9TDwwPS0tK4bkxMjEyQH86klZUVqqqq0N3dzXYjlLu7O2pra6GlpcXxxMSE2MxnZ2c5eWxsbKCuro7g4GDs7u7i8vJS3J48lCaVlJTEu0B29SPI2NhY9je6UYTy9PTkVdXU1OSYMl1o5pTxc3NzMDU15StTTU0NQUFB2N/fx/n5ubgfmhD5bFRUFC/I9va27JCUAP39/RKVfH19+VxqaGhwPDY29qmZE+j8/DxMTExgZ2cHVVVVXlFKnrOzM4l6NCEfHx+srq7KDnl8fCxRgQYhU6aVIY2Ojn5r5iJQY2NjTiAVFRW2J4IkgxdKGsBPIYUKCwtjM6YVIQ0PD6OkpESqjgmUzh09RpSVleHv789Zfnh4KFV7qSCjo6ORl5fHA5AGBwc/mPnfRLeOvr4+HB0duR8/Pz/c39/zOZUbMj4+np9cSkpKHJNXVlRUyNSxSHQL6erqwsnJifujK/Lp6UnqzP4UkjwyMzNTDNjX18cPC3m0vLwMbW1tODs7c79eXl54fn6WKrNJSv/8j0NvQpF6enrYFxWljIwMJCYmimPyXWn05bVIZq5IQBL119XVJXM71c8+dnZ2oqmpSV6mT9XQ0MD+mpKSInWbD2eSsrC5uVnRbBJaX1/ncYRH6zt9OJP/R8n1v/u39AfXU7boQU4iVgAAAABJRU5ErkJggg==";
			System.out.println(u.GenerateImage("e:/","ys1",str));*/
	}

}
