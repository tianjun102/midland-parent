package com.midland.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.midland.base.BaseFilter;
import com.midland.web.api.ApiHelper;
import com.midland.web.api.mailSender.MailProperties;
import com.midland.web.model.Area;
import com.midland.web.model.ResumeManager;
import com.midland.web.model.user.User;
import com.midland.web.service.ResumeManagerService;
import com.midland.web.service.SettingService;
import com.midland.web.util.MidlandHelper;
import org.apache.commons.io.IOUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@SuppressWarnings("all")
/**
 * 简历管理控制层
 */
@RequestMapping("/resumeManager/")
public class ResumeManagerController extends BaseFilter {

    private Logger log = LoggerFactory.getLogger(ResumeManagerController.class);
    @Autowired
    private ResumeManagerService resumeManagerServiceImpl;
    @Autowired
    private SettingService settingService;
    @Autowired
    private ApiHelper apiHelper;
    @Autowired
    private MailProperties mailProperties;

    /**
     *
     **/
    @RequestMapping("index")
    public String resumeManagerIndex(ResumeManager resumeManager, Model model, HttpServletRequest request) throws Exception {
        settingService.getAllProvinceList(model);
        User user = MidlandHelper.getCurrentUser(request);
        if (user.getIsSuper() == null) {
            model.addAttribute("cityId", user.getCityId());
        }
        model.addAttribute("isSuper", user.getIsSuper());
        return "resumeManager/resumeManagerIndex";
    }

    /**
     *
     **/
    @RequestMapping("to_add")
    public String toAddResumeManager(ResumeManager resumeManager, Model model, HttpServletRequest request) throws Exception {
        Map<String, String> parem = new HashMap<>();
        parem.put("flag", "city");
        parem.put("id", "*");
        Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
        List<Area> cityList=new ArrayList<>();
        if (cityMap !=null){
            cityList = cityMap.get("city");
        }
        model.addAttribute("cityList", cityList);
        User user = MidlandHelper.getCurrentUser(request);
        if (user.getIsSuper() == null) {
            model.addAttribute("cityId", user.getCityId());
            model.addAttribute("cityName", user.getCityName());
        }
        model.addAttribute("isSuper", user.getIsSuper());
        return "resumeManager/addResumeManager";
    }

    /**
     * 新增
     **/
    @RequestMapping("add")
    @ResponseBody
    public Object addResumeManager(ResumeManager resumeManager) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            log.debug("addResumeManager {}", resumeManager);
            resumeManagerServiceImpl.insertResumeManager(resumeManager);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("addResumeManager异常 {}", resumeManager, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     * 查询
     **/
    @RequestMapping("get_resumeManager")
    public String getResumeManagerById(Integer id, Model model) {
        log.debug("getResumeManagerById  {}", id);
        ResumeManager result = resumeManagerServiceImpl.selectResumeManagerById(id);
        model.addAttribute("item", result);
        return "resumeManager/updateResumeManager";
    }

    /**
     * 删除
     **/
    @RequestMapping("delete")
    @ResponseBody
    public Object deleteResumeManagerById(Integer id) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            log.debug("deleteResumeManagerById  {}", id);
            resumeManagerServiceImpl.deleteResumeManagerById(id);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("deleteResumeManagerById  {}", id, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     *
     **/
    @RequestMapping("to_update")
    public String toUpdateResumeManager(Integer id, Model model) throws Exception {
        ResumeManager result = resumeManagerServiceImpl.selectResumeManagerById(id);
        model.addAttribute("item", result);
        return "resumeManager/updateResumeManager";
    }

    /**
     * 更新
     **/
    @RequestMapping("update")
    @ResponseBody
    public Object updateResumeManagerById(ResumeManager resumeManager) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            log.debug("updateResumeManagerById  {}", resumeManager);
            resumeManagerServiceImpl.updateResumeManagerById(resumeManager);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(mailProperties.getUserName());
            message.setTo("977543176@qq.com");
            message.setSubject(resumeManager.getTitle());
            message.setText(resumeManager.getReply());
            if (resumeManager.getIsDelete() == null) {
                apiHelper.emailSender("sendSimpleMail", message);
            }
            map.put("state", 0);
        } catch (Exception e) {
            log.error("updateResumeManagerById  {}", resumeManager, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @RequestMapping("list")
    public String findResumeManagerList(ResumeManager resumeManager, Model model, HttpServletRequest request) {
        try {
            log.debug("findResumeManagerList  {}", resumeManager);
            MidlandHelper.doPage(request);
            Page<ResumeManager> result = (Page<ResumeManager>) resumeManagerServiceImpl.findResumeManagerList(resumeManager);
            Paginator paginator = result.getPaginator();
            model.addAttribute("paginator", paginator);
            model.addAttribute("items", result);
        } catch (Exception e) {
            log.error("findResumeManagerList  {}", resumeManager, e);
            model.addAttribute("paginator", null);
            model.addAttribute("items", null);
        }
        return "resumeManager/resumeManagerList";
    }

    @RequestMapping(value = "/fileDownload", method = RequestMethod.GET)
    @ResponseBody
    public void fileDownload(String filePath, HttpServletRequest request,
                             HttpServletResponse response) {
        try {
            File file = new File("D://bg_new.jpg");
            if (!file.exists()) {
                System.out.println("文件不存在");
            }
            response.reset(); //设置ContentType
            response.setContentType("application/octet-stream; charset=utf-8");
            String fileName = new String(file.getName().getBytes("utf-8"), "ISO-8859-1"); //处理中文文件名中文乱码问题
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            IOUtils.copy(new FileInputStream(file), response.getOutputStream());
        } catch (Exception e) {
            System.out.println("文件下载失败");
        }

    }


    /*
     * 批量下载另存为
     */
    @RequestMapping("/batDownload")
    @ResponseBody
    public void batDownload(String filePaths, String fileNames, HttpServletRequest request, HttpServletResponse response) {
        /*if(StringUtils.isEmpty(filePaths)) {
			filePaths = "D:/upload/1.txt|D:/upload/2.doc|D:/upload/3.xls";
		}
		if(StringUtils.isEmpty(fileNames)){
			fileNames = "1.txt|2.doc|3.xls";
		}*/

        String tmpFileName = "work.zip";
        byte[] buffer = new byte[1024];
        String strZipPath = ("/home/upload/work/" + tmpFileName);
        try {
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(strZipPath));
            String[] files = filePaths.split(",", -1);
            String[] names = fileNames.split(",", -1);
            // 下载的文件集合
            for (int i = 0; i < files.length; i++) {
                FileInputStream fis = new FileInputStream((files[i]));
                out.putNextEntry(new ZipEntry(names[i]));
                //设置压缩文件内的字符编码，不然会变成乱码
                out.setEncoding("GBK");
                int len;
                // 读入需要下载的文件的内容，打包到zip文件
                while ((len = fis.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                }
                out.closeEntry();
                fis.close();
            }
            out.close();
            saveAs("/home/upload/work/" + tmpFileName, tmpFileName, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*
     * 另存为
     */
    public void saveAs(String filePath, String fileName, HttpServletResponse response) {

        try {
            File file = new File(filePath);
            // 设置文件MIME类型
            response.setContentType(getMIMEType(file));
            // 设置Content-Disposition
            response.setHeader(
                    "Content-Disposition",
                    "attachment;filename="
                            + URLEncoder.encode(fileName, "UTF-8"));
            // 获取目标文件的绝对路径
            String fullFileName = (filePath);
            // System.out.println(fullFileName);
            // 读取文件
            InputStream ins = new FileInputStream(fullFileName);
            // 放到缓冲流里面
            BufferedInputStream bins = new BufferedInputStream(ins);
            // 获取文件输出IO流
            // 读取目标文件，通过response将目标文件写到客户端
            OutputStream outs = response.getOutputStream();
            BufferedOutputStream bouts = new BufferedOutputStream(outs);
            // 写文件
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            // 开始向网络传输文件流
            while ((bytesRead = bins.read(buffer, 0, 8192)) != -1) {
                bouts.write(buffer, 0, bytesRead);
            }
            bouts.flush();// 这里一定要调用flush()方法
            ins.close();
            bins.close();
            outs.close();
            bouts.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private final String[][] MIME_MapTable = {
            // {后缀名， MIME类型}
            {".doc", "application/msword"},
            {".docx",
                    "application/vnd.openxmlformats-officedocument.wordprocessingml.document"},
            {".xls", "application/vnd.ms-excel"},
            {".xlsx",
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"},
            {".pdf", "application/pdf"},
            {".ppt", "application/vnd.ms-powerpoint"},
            {".pptx",
                    "application/vnd.openxmlformats-officedocument.presentationml.presentation"},
            {".txt", "text/plain"}, {".wps", "application/vnd.ms-works"},
            {"", "*/*"}};


    /**
     * 根据文件后缀名获得对应的MIME类型。
     *
     * @param file
     */
    private String getMIMEType(File file) {
        String type = "*/*";
        String fName = file.getName();
        // 获取后缀名前的分隔符"."在fName中的位置。
        int dotIndex = fName.lastIndexOf(".");
        if (dotIndex < 0) {
            return type;
        }
		         /* 获取文件的后缀名 */
        String end = fName.substring(dotIndex, fName.length()).toLowerCase();
        if (end == "")
            return type;
        // 在MIME和文件类型的匹配表中找到对应的MIME类型。
        for (int i = 0; i < MIME_MapTable.length; i++) {
            if (end.equals(MIME_MapTable[i][0]))
                type = MIME_MapTable[i][1];
        }
        return type;
    }

    /**
     * 批量更新
     **/
    @RequestMapping("batchUpdate")
    @ResponseBody
    public Object batchUpdate(String ids, ResumeManager resumeManager) throws Exception {
        List<ResumeManager> commentList = new ArrayList<>();
        String[] ides = ids.split(",", -1);
        for (String id : ides) {
            ResumeManager comment1 = new ResumeManager();
            comment1.setId(Integer.valueOf(id));
            comment1.setIsDelete(resumeManager.getIsDelete());
            commentList.add(comment1);
        }
        Map<String, Object> map = new HashMap<>();
        try {
            log.debug("updateCategoryById  {}", commentList);
            resumeManagerServiceImpl.batchUpdate(commentList);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("updateCategoryById  {}", commentList, e);
            map.put("state", -1);
        }
        return map;
    }

}
