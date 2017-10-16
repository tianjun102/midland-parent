package com.midland.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.Paginator;
import com.midland.base.BaseFilter;
import com.midland.web.enums.ContextEnums;
import com.midland.web.model.Permission;
import com.midland.web.model.role.Role;
import com.midland.web.model.role.RolePermission;
import com.midland.web.model.user.User;
import com.midland.web.service.RoleService;
import com.midland.web.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController extends BaseFilter {

	@Resource
	private RoleService roleService;
	
	@Resource
    private UserService userService;


	/**
	 * 进入列表搜索页面
	 * @param
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/roleIndex", method = {RequestMethod.GET,RequestMethod.POST})
	public String findRoleIndex(Role role, Model model, HttpServletRequest request){
		
		return "role/roleIndex";
	}

	/**
	 * 角色列表查询 
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "/roleList", method={RequestMethod.GET, RequestMethod.POST})
	public String selectRoleList(Role role, Model model, HttpServletRequest request){

		String pageSize = request.getParameter("pageSize");
		String pageNo = request.getParameter("pageNo");
		if(pageNo==null||pageNo.equals("")){
			pageNo = ContextEnums.PAGENO;
		}
		if(pageSize==null||pageSize.equals("")){
			pageSize = ContextEnums.PAGESIZE;
		}

		role.setState(1);//生效的
		PageHelper.startPage(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
		Page<Role> roleList=(Page<Role>)roleService.selectByExampleAndPage(role);
		Paginator paginator = roleList.getPaginator();
		model.addAttribute("paginator", paginator);
		model.addAttribute("roles", roleList);
		return "role/rolelist";
	}

	/**
	 * 跳转到新增角色页面
	 * @return
	 */
	@RequestMapping(value = "/toAddPage", method={RequestMethod.GET, RequestMethod.POST})
	public String toAddPage(){
		return "role/addRole";
	}


	/**
	 * 新增角色
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "/addRole", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Object addRole(Role role){
		Map<String, Object> map = new HashMap<String, Object>();
    	map.put("flag", 0);
		if(roleService.insert(role)>0){
			map.put("flag",1);
		}
		return map;
	}

	/**
	 * 查询角色
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/findRole", method={RequestMethod.GET, RequestMethod.POST})
	public String findRole(Model model,HttpServletRequest request){
		Integer roleId=Integer.valueOf(request.getParameter("roleId"));
		Role roleInfo = roleService.selectById(roleId);
		model.addAttribute("role",roleInfo);
		return "role/roleInfo";
	}
	
	/**
	 * 角色用户列表
	 * @param roleId
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/roleUser", method = {RequestMethod.GET,RequestMethod.POST})
    public String  roleUserList(Integer roleId,Model model,HttpServletRequest request){
		Role roleInfo = roleService.selectById(roleId);
		
		String pageSize = request.getParameter("pageSize");
		String pageNo = request.getParameter("pageNo");
		if(pageNo==null||pageNo.equals("")){
			pageNo =  ContextEnums.PAGENO;
		}
		if(pageSize==null||pageSize.equals("")){
			pageSize =  ContextEnums.PAGESIZE;
		}
		PageHelper.startPage(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
		Page<User> users=(Page<User>)userService.selectByExampleAndPage(null);//所有用户
    	
		
    	
    	List<User> roleUsers= userService.selectUsersByRoleId(roleId);//用户的角色
    	model.addAttribute("role",roleInfo);
    	model.addAttribute("users",users);
		Paginator paginator = users.getPaginator();
		model.addAttribute("paginator", paginator);
		
    	model.addAttribute("roleUsers",roleUsers);
    	return "role/roleUserList";
    }
	
	/**
	 * 保存角色用户关系
	 * @param roleId
	 * @param userIds
	 * @return
	 */
	@RequestMapping(value = "/saveRoleUser", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Object saveRoleUser(Integer roleId,String userIds){
		Map<String, Object> map = new HashMap<String, Object>();
    	map.put("flag", 0);
    	int n=roleService.updateRoleUser(roleId,userIds);
    	if(n>0){
    		map.put("flag", 1);
    	}
    	return map;
	}

	/**
	 * 修改角色
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "/editRole", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Object updateRole(Role role){
		Map<String, Object> map = new HashMap<String, Object>();
    	map.put("flag", 0);
    	if(roleService.modifyRole(role)>0){
    		map.put("flag", 1);
    	}
    	return map;
	}

	/**
	 * 删除
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/deleteRole", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Object deleteRole(Integer roleId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", 0);
		Role role=new Role();
		role.setId(roleId);
		role.setState(0);
		if(roleService.update(role)>0){
			map.put("flag",1);
		}
		return map;
	}

	/**
	 * 检查角色标识或名称的唯一性 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/checkRoleUnique", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Object checkRoleUnique(Role role){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", 1);

		if(role!=null){
			Role data=roleService.selectByExampleOne(role);
			if(data!=null){
				map.put("flag", 0);
			}
		}
		return map;
	}

	

	@RequestMapping(value = "toAllocation", method={RequestMethod.GET, RequestMethod.POST})
	public String allocation(Integer roleId,Model model){
		if(roleId!=null){
			String fmtData=this.permiTree(roleId);
			Role role = roleService.selectById(roleId);
			model.addAttribute("dataFmt", fmtData);
			model.addAttribute("roles", role);
			model.addAttribute("defaultUrl", "content");
		}
		return "role/allocation";
	}

	/**
	 * 保存角色与权限关系
	 * @param roleId
	 * @param
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveRolePermissions",method = {RequestMethod.POST})
	@ResponseBody
	public Object saveRolePermissions(String roleId,String permissionIds) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer  saveFlag = roleService.saveRolePermissions(roleId, permissionIds);
		
		map.put("saveFlag",saveFlag);
		return map;
	}
	
	/**
	 * 分配角色权限
	 * @param roleId
	 * @param permissionIds
	 * @return
	 */
	@RequestMapping(value = "/allocation", method={RequestMethod.GET, RequestMethod.POST})
	public String allocationRolePermission(Integer roleId,List<Integer> permissionIds){



		return "role/roleIndex";
	}

	/**
	 * 授权用户
	 * @param roleId
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/authorize", method={RequestMethod.GET, RequestMethod.POST})
	public String authorizeRoleToUser(Integer roleId,List<Integer> userIds){
		return "role/roleIndex";
	}

	/*private String encapsulateTree(Integer roleId) {
		// 避免数据库中存在换行符,进行菜单文字的过滤
		String replaceStr = "(\r\n|\r|\n|\n\r)";
		StringBuffer ret = new StringBuffer(""), retSon = new StringBuffer("");
		RoleAuth roleAuth = new RoleAuth();
		if (roleId != null) {
			roleAuth.setRoleId(Long.valueOf(roleId));
			// 获取所有该角色已有的权限
			List<RoleAuth> roleauth = roleService.getListAuthid(roleAuth);
			// 封装菜单级别信息,
			List<AuthRelation> list = roleService.getAuths();

			for (int i = 0; i < list.size(); i++) {
				AuthRelation dto = list.get(i);
				if("2".equals(dto.getTagLevel())) {
					boolean hasParentAuth =false;
					for(RoleAuth e:roleauth){
						if(e.getAuthId().compareTo(dto.getAuthId())==0){
							hasParentAuth = true;
							break;
						}
					}
					ret.append("{id:").append(dto.getAuthId()).append(", pId:0, name:\"")
					.append(dto.getTagName().replaceAll(replaceStr, "")).append("\"");

					// 子菜单集合
					List<AuthRelation> childAuth = dto.getAuthList();
					if(childAuth!=null&&childAuth.size()>0){
						for(AuthRelation childE :childAuth){
							if(childE!=null){
								boolean hasChildAuth =false;
								for(RoleAuth e:roleauth){
									if(e.getAuthId().compareTo(childE.getAuthId())==0){
										hasChildAuth = true;
										hasParentAuth = true;
										break;
									}
								}
								retSon.append("{id:").append(childE.getAuthId()).append(", pId:")
								.append(dto.getAuthId()).append(", name:\"")
								.append(childE.getTagName().replaceAll(replaceStr, "")).append("\"");
								if (hasChildAuth) {
									retSon.append(", checked:true");
								}
								retSon.append("},");
							}
						}
					}
					if (hasParentAuth) {
						ret.append(", checked:true");
					}
					ret.append(", open:true},");
					ret.append(retSon);
					retSon = new StringBuffer();
				}
			}
		}
		return ret.substring(0, ret.length() - 1);
	}*/

	
	private String permiTree(Integer roleId) {
		// 避免数据库中存在换行符,进行菜单文字的过滤
		String replaceStr = "(\r\n|\r|\n|\n\r)";
		StringBuffer ret = new StringBuffer(""), retSon = new StringBuffer("");
		RolePermission rp = new RolePermission();
		if (roleId != null) {
			rp.setRoleId(roleId);
			// 获取所有该角色已有的权限
			List<RolePermission> rolePermission= roleService.getListPermission(rp);
			// 封装菜单级别信息,
			List<Permission> list = roleService.getPermissions();

			for (int i = 0; i < list.size(); i++) {
				Permission dto = list.get(i);
				if(dto.getPermissionType().compareTo(0)==0) {
					boolean hasParentAuth =false;
					for(RolePermission e:rolePermission){
						if(e.getPermissionId().compareTo(dto.getId())==0){
							hasParentAuth = true;
							break;
						}
					}
					ret.append("{id:").append(dto.getId()).append(", pId:0, name:\"")
					.append(dto.getPermissionName().replaceAll(replaceStr, "")).append("\"");

					// 子菜单集合
					List<Permission> childAuth = dto.getPermissionList();
					if(childAuth!=null&&childAuth.size()>0){
						for(Permission childE :childAuth){
							if(childE!=null){
								boolean hasChildAuth =false;
								for(RolePermission e:rolePermission){
									if(e.getPermissionId().compareTo(childE.getId())==0){
										hasChildAuth = true;
										hasParentAuth = true;
										break;
									}
								}
								retSon.append("{id:").append(childE.getId()).append(", pId:")
								.append(dto.getId()).append(", name:\"")
								.append(childE.getPermissionName().replaceAll(replaceStr, "")).append("\"");
								if (hasChildAuth) {
									retSon.append(", checked:true");
								}
								retSon.append("},");
							}
						}
					}
					if (hasParentAuth) {
						ret.append(", checked:true");
					}
					ret.append(", open:true},");
					ret.append(retSon);
					retSon = new StringBuffer();
				}
			}
		}
		return ret.substring(0, ret.length() - 1);
	}
}
