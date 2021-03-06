package com.midland.base;

import com.midland.web.model.Category;
import com.midland.web.model.SiteMap;
import com.midland.web.service.CategoryService;
import com.midland.web.service.SiteMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by 'ms.x' on 2017/7/27.
 */
@Controller
public abstract class BaseFilter {
    @Autowired
    private CategoryService categoryServiceImpl;
    @Autowired
    private SiteMapService siteMapServiceImpl;

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        InitStringToNull stringEditor = new InitStringToNull();
        binder.registerCustomEditor(String.class, stringEditor);
    }


    @ExceptionHandler({Exception.class})
    public void handlerException(Exception e, HttpServletResponse response) throws IOException {
        e.printStackTrace();
        if (e instanceof DuplicateKeyException) {
            responseInfo(response, "数据已存在，请检查！...");
        }else if(e instanceof TransientDataAccessResourceException){
            responseInfo(response, "package too big！...");
        }
        else {
            responseInfo(response, "系统繁忙，请重试！...");
        }

    }

    private void responseInfo(HttpServletResponse response, String info) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().print(info);
    }


    // 把查询结果转换成JSON格式      type: 1-查询1-2级 ； 为空时查询所有
    public String getCategoryTree(String type, Category category) {
        // 避免数据库中存在换行符,进行菜单文字的过滤
        // String replaceStr = "(\r\n|\r|\n|\n\r)";
        List list = new ArrayList<>();
        if ("1".equals(type)) {
            try {
                list = categoryServiceImpl.findCategoryList(category);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                list = categoryServiceImpl.findCategoryList(category);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        StringBuffer ret = new StringBuffer("");
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Category cat = (Category) list.get(i);
                ret.append("{id:").append(cat.getId()).append(", pId:").append(cat.getParentId());
                if (cat.getParentId() == 0) {
                    if (cat.getSource() == 0) {
                        ret.append(", name:'").append(cat.getCateName() + "(网站)").append("',open:false,nocheck:true");
                    } else {
                        ret.append(", name:'").append(cat.getCateName() + "(微站)").append("',open:false,nocheck:true");
                    }
                } else {
                    ret.append(", name:'").append(cat.getCateName()).append("',open:false,nocheck:true");

                }
                if ("".equals(type)) {
                    ret.append(", chirdCount:").append(cat.getChirdCount());
                }
                if (!("0".equals(cat.getParentId().toString()))) {
                    ret.append(",iconSkin:'pIcon03'");
                }

                ret.append("},");
            }
            return ret.substring(0, ret.length() - 1);
        }

        return "";
    }

    public String getSiteMap(String type, Category category) {
        // 避免数据库中存在换行符,进行菜单文字的过滤
        // String replaceStr = "(\r\n|\r|\n|\n\r)";
        List<Category> list = new ArrayList<>();
        List<Category> listTemp = new ArrayList<>();
        List<Integer> arr = new ArrayList<>();
        if ("1".equals(type)) {
            try {
                list = categoryServiceImpl.findCategoryList(category);
                list.forEach(e -> {
                    arr.add(e.getId());
                });
                if (arr.size() > 0) {
                    List<SiteMap> siteMaps = siteMapServiceImpl.findSiteMapByList(arr);
                    siteMaps.forEach(e -> {
                        Category category1 = new Category();
                        category1.setType(1);
                        category1.setParentId(e.getCateId());
                        category1.setParentName(e.getCateName());
                        category1.setId(e.getId());
                        listTemp.add(category1);
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                list = categoryServiceImpl.findCategoryList(category);
                list.forEach(e -> {
                    arr.add(e.getId());
                });
                if (arr.size() > 0) {
                    List<SiteMap> siteMaps = siteMapServiceImpl.findSiteMapByList(arr);
                    siteMaps.forEach(e -> {
                        Category category1 = new Category();
                        category1.setType(1);
                        category1.setParentId(e.getCateId());
                        category1.setParentName(e.getCateName());
                        category1.setCateName(e.getName());
                        category1.setId(e.getId());
                        listTemp.add(category1);
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        list.addAll(listTemp);
        StringBuffer ret = new StringBuffer("");
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Category cat = list.get(i);
                ret.append("{id:").append(cat.getId()).append(", pId:").append(cat.getParentId())
                        .append(", name:'").append(cat.getCateName()).append("'")
                        .append(", pName:'").append(cat.getParentName()).append("'");
                if (cat.getType() != 1) {
                    ret.append(", type:").append("0");
                } else {
                    ret.append(", type:").append("1");
                }

                ret.append(",open:false,nocheck:true");
                if ("".equals(type)) {
                    ret.append(", chirdCount:").append(cat.getChirdCount());
                }
                if (!("0".equals(cat.getParentId().toString()))) {
                    ret.append(",iconSkin:'pIcon03'");
                }

                ret.append("},");
            }
            return ret.substring(0, ret.length() - 1);
        }

        return "";
    }


    public List<Tree> getSiteObject(String type, Category category) {
        // 避免数据库中存在换行符,进行菜单文字的过滤
        // String replaceStr = "(\r\n|\r|\n|\n\r)";
        List<Category> list = new ArrayList<>();
        List<Category> listTemp = new ArrayList<>();
        List<Integer> arr = new ArrayList<>();
        if ("1".equals(type)) {
            try {
                list = categoryServiceImpl.findCategoryList(category);
                list.forEach(e -> {
                    arr.add(e.getId());
                });
                if (arr.size() > 0) {
                    List<SiteMap> siteMaps = siteMapServiceImpl.findSiteMapByList(arr);
                    siteMaps.forEach(e -> {
                        Category category1 = new Category();
                        category1.setType(1);
                        category1.setParentId(e.getCateId());
                        category1.setParentName(e.getCateName());
                        category1.setId(e.getId());
                        listTemp.add(category1);
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                list = categoryServiceImpl.findCategoryList(category);
                list.forEach(e -> {
                    arr.add(e.getId());
                });
                if (arr.size() > 0) {
                    List<SiteMap> siteMaps = siteMapServiceImpl.findSiteMapByList(arr);
                    siteMaps.forEach(e -> {
                        Category category1 = new Category();
                        category1.setType(1);
                        category1.setParentId(e.getCateId());
                        category1.setParentName(e.getCateName());
                        category1.setCateName(e.getName());
                        category1.setId(e.getId());
                        listTemp.add(category1);
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        list.addAll(listTemp);
        List<Tree> treeList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Tree tree = new Tree();
                Category cat = list.get(i);
                tree.setId(cat.getId());
                tree.setpId(cat.getParentId());
                tree.setName(cat.getCateName());
                tree.setpName(cat.getParentName());
                if (cat.getType() != 1) {
                    tree.setType(0);
                } else {
                    tree.setType(1);
                }
                tree.setOpen(false);
                tree.setNocheck(true);
                if ("".equals(type)) {
                    tree.setChirdCount(cat.getChirdCount());
                }
                if (!("0".equals(cat.getParentId().toString()))) {
                    tree.setIconSkin("pIcon03");
                }
                treeList.add(tree);
            }
            return treeList;
        }

        return Collections.EMPTY_LIST;
    }

}

class Tree {
    private Integer id;
    private Integer pId;
    private String name;
    private String pName;
    private Integer type;
    private boolean open;
    private boolean nocheck;
    private Integer chirdCount;
    private String iconSkin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isNocheck() {
        return nocheck;
    }

    public void setNocheck(boolean nocheck) {
        this.nocheck = nocheck;
    }

    public Integer getChirdCount() {
        return chirdCount;
    }

    public void setChirdCount(Integer chirdCount) {
        this.chirdCount = chirdCount;
    }

    public String getIconSkin() {
        return iconSkin;
    }

    public void setIconSkin(String iconSkin) {
        this.iconSkin = iconSkin;
    }
}
