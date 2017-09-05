//package com.midland.core.util;
//
//import java.awt.Image;
//import java.awt.image.BufferedImage;
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.imageio.ImageIO;
//
//import org.apache.commons.lang.math.RandomUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.web.multipart.MultipartFile;
//
//public class UploadImgUtil {
//
//
//	public static Map<String,String> upload(Map<String, MultipartFile> fileMap,String path) throws IOException{
//
//		Map<String,String> map = new HashMap<>();
//		for (String key : fileMap.keySet()) {
//			MultipartFile file = fileMap.get(key);
//			String fileName = file.getOriginalFilename();
//			SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
//	        String format1 = format.format(new Date());
//	        int i = RandomUtils.nextInt(1000);
//	        String newfileName = null;
//	        if (StringUtils.isNotBlank(fileName)) {
//	        newfileName =  format1 + StringUtils.leftPad(i + "", 2, '0') +fileName;
//	        }else{
//	        	newfileName = "";
//	        }
//			File fl = new File(path +"pc/"+ newfileName);
//			File fl2 = new File(path +"mobile/"+newfileName);
//			InputStream files = file.getInputStream();
//			if (!fl.getParentFile().exists()) {
//				fl.getParentFile().mkdirs();
//			}
//			if (!fl2.getParentFile().exists()) {
//				fl2.getParentFile().mkdirs();
//			}
//			if (!fl.exists()) {
//				fl.createNewFile();
//			}
//			if (!fl2.exists()) {
//				fl2.createNewFile();
//			}
//			if (StringUtils.isNotBlank(fileName)) {
//				//pc端图片不做处理
//					file.transferTo(fl);
//					map.put("file1", "/upload/"+"pc/"+newfileName);
//				//手机端将图片尺寸改变
//				Image bi =ImageIO.read(files);
//		        //获取图像的高度，宽度
//		        int height=bi.getHeight(null);
//		        int width =bi.getWidth(null);
//		        //构建图片流
//		        BufferedImage tag = new BufferedImage(width / 2, height / 2, BufferedImage.TYPE_INT_RGB);
//		        //绘制改变尺寸后的图
//		        tag.getGraphics().drawImage(bi, 0, 0,width / 2, height / 2, null);
//		        //输出流
//		        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(path+"mobile/"+newfileName));
//		        //JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//		        //encoder.encode(tag);
//		        ImageIO.write(tag, "PNG",out);
//		        files.close();
//		        out.close();
//		        map.put("file2", "/upload/"+"mobile/"+newfileName);
//
//			}
//
//		}
//		return map;
//	};
//
//	public static String uploadImgFile(MultipartFile file,String path){
//		String str=File.separator+"upload"+File.separator;
//		if(file==null) return str;
//		try {
//			String fileName = file.getOriginalFilename();
//			String prefix=fileName.substring(fileName.lastIndexOf("."));
//			fileName = System.currentTimeMillis()+prefix;
//			File fl = new File(path + fileName);
//			if (!fl.getParentFile().exists()) {
//				fl.getParentFile().mkdirs();
//			}
//			if (!fl.exists()) {
//				fl.createNewFile();
//			}
//			if (StringUtils.isNotBlank(fileName)) {
//				file.transferTo(fl);
//			}
//			str=str+fileName;
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return str;
//	}
//
//
//	public static List<ProductPic> upload2(Map<String, MultipartFile> fileMap,String path) throws IOException{
//
//
//		List<ProductPic> list = new ArrayList<ProductPic>();
//		for (String key : fileMap.keySet()) {
//			ProductPic p = new ProductPic();
//			MultipartFile file = fileMap.get(key);
//			String fileName = file.getOriginalFilename();
//			SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
//	        String format1 = format.format(new Date());
//	        int i = RandomUtils.nextInt(1000);
//	        String newfileName = null;
//	        if (StringUtils.isNotBlank(fileName)) {
//	        	newfileName =  format1 + StringUtils.leftPad(i + "", 2, '0') +fileName;
//	        }else{
//	        	newfileName = "";
//	        }
//			File fl = new File(path +"pc1/"+ newfileName);
//			File fl2 = new File(path +"pc2/"+ newfileName);
//			File fl3 = new File(path +"mobile/"+newfileName);
//			File fl4 = new File(path +"original/"+newfileName);
//			InputStream files = file.getInputStream();
//			if (!fl.getParentFile().exists()) {
//				fl.getParentFile().mkdirs();
//			}
//			if (!fl2.getParentFile().exists()) {
//				fl2.getParentFile().mkdirs();
//			}
//			if (!fl3.getParentFile().exists()) {
//				fl3.getParentFile().mkdirs();
//			}
//			if (!fl4.getParentFile().exists()) {
//				fl4.getParentFile().mkdirs();
//			}
//			if (!fl.exists()) {
//				fl.createNewFile();
//			}
//			if (!fl2.exists()) {
//				fl2.createNewFile();
//			}
//			if (!fl3.exists()) {
//				fl3.createNewFile();
//			}
//			if (!fl4.exists()) {
//				fl4.createNewFile();
//			}
//			if (StringUtils.isNotBlank(fileName)) {
//				//pc端图片不做处理
//				file.transferTo(fl4);
//				//map.put("file1", "/upload/"+"pc/"+fileName);
//				p.setPicOriginal("/upload/original/"+newfileName);
//
//				//手机端将图片尺寸改变
//				Image bi =ImageIO.read(files);
//		        //获取图像的高度，宽度
//		        int height=bi.getHeight(null);
//		        int width =bi.getWidth(null);
//
//
//		        //构建图片流 - PC1
//		        BufferedImage tag1 = new BufferedImage(310, 310, BufferedImage.TYPE_INT_RGB);
//		        //绘制改变尺寸后的图
//		        tag1.getGraphics().drawImage(bi, 0, 0,310, 310, null);
//
//
//		        //构建图片流 - PC2
//		        BufferedImage tag2 = new BufferedImage(265 , 265 , BufferedImage.TYPE_INT_RGB);
//		        //绘制改变尺寸后的图
//		        tag2.getGraphics().drawImage(bi, 0, 0,265 , 265 , null);
//
//
//
//		        //构建图片流 - mobile
//		        BufferedImage tag3 = new BufferedImage(80,80, BufferedImage.TYPE_INT_RGB);
//		        //绘制改变尺寸后的图
//		        tag3.getGraphics().drawImage(bi, 0, 0,80,80, null);
//
//
//		        BufferedOutputStream out1 = new BufferedOutputStream(new FileOutputStream(path+"pc1/"+newfileName));
//		        //JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//		        //encoder.encode(tag);
//		        ImageIO.write(tag1, "PNG",out1);
//		        out1.close();
//		        p.setThumbUrl("/upload/pc1/"+newfileName);
//		        p.setImg1("/upload/pc1/"+newfileName);
//
//
//		        BufferedOutputStream out2 = new BufferedOutputStream(new FileOutputStream(path+"pc2/"+newfileName));
//		        //JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//		        //encoder.encode(tag);
//		        ImageIO.write(tag2, "PNG",out2);
//		        out2.close();
//		        p.setThumbUrl2("/upload/pc2/"+newfileName);
//		        p.setImg2("/upload/pc2/"+newfileName);
//
//		        BufferedOutputStream out3 = new BufferedOutputStream(new FileOutputStream(path+"mobile/"+newfileName));
//		        //JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//		        //encoder.encode(tag);
//		        ImageIO.write(tag3, "PNG",out3);
//		        out3.close();
//		        files.close();
//		        p.setPicUrl("/upload/mobile/"+newfileName);
//
//			}
//			if(StringUtils.isNotEmpty(p.getPicOriginal())){
//				list.add(p);
//			}
//
//		}
//		return list;
//	}
//
//
//}
