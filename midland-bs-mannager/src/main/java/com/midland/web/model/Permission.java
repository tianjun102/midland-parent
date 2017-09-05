package com.midland.web.model;

import java.util.List;

/**
 * 权限模型
 * 
 * @author 
 * @since 2016年7月17日 下午1:02:55
 **/
public class Permission {
    private Integer id;

    private String permissionName;

    private String permissionSign;
    
    private String description;

    private Integer parentId;
    private String parentSign;
    private String permissionPath;
    private Integer permissionType;
    private Integer orderNo;
    private Integer status;
    
    private List<Permission> permissionList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName == null ? null : permissionName.trim();
    }

    public String getPermissionSign() {
        return permissionSign;
    }

    public void setPermissionSign(String permissionSign) {
        this.permissionSign = permissionSign == null ? null : permissionSign.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
    
    public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getParentSign() {
		return parentSign;
	}

	public void setParentSign(String parentSign) {
		this.parentSign = parentSign;
	}

	public String getPermissionPath() {
		return permissionPath;
	}

	public void setPermissionPath(String permissionPath) {
		this.permissionPath = permissionPath;
	}

	public Integer getPermissionType() {
		return permissionType;
	}

	public void setPermissionType(Integer permissionType) {
		this.permissionType = permissionType;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<Permission> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<Permission> permissionList) {
		this.permissionList = permissionList;
	}

	@Override
	public String toString() {
		return "Permission [id=" + id + ", permissionName=" + permissionName + ", permissionSign=" + permissionSign
				+ ", description=" + description + ", parentId=" + parentId + ", parentSign=" + parentSign
				+ ", permissionPath=" + permissionPath + ", permissionType=" + permissionType + ", orderNo=" + orderNo
				+ ", status=" + status + "]";
	}

}