package com.midland.web.model.role;

/**
 * 角色模型
 *
 * @author
 * @since 2016年7月17日 下午1:02:25
 **/
public class Role {
    private Integer id;


    private String roleName;

    private String roleSign;

    private String description;

    private Integer roleType;
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleSign() {
        return roleSign;
    }

    public void setRoleSign(String roleSign) {
        this.roleSign = roleSign == null ? null : roleSign.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }


    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Role [id=" + id + ", roleName=" + roleName + ", roleSign=" + roleSign + ", description=" + description
                + ", roleType=" + roleType + ", state=" + state + "]";
    }

}