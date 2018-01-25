package com.midland.web.model.role;


public class RoleAuth {

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long roleId;
    private Long authId;
    private String roleName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getAuthId() {
        return authId;
    }

    public void setAuthId(Long authId) {
        this.authId = authId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }


}
