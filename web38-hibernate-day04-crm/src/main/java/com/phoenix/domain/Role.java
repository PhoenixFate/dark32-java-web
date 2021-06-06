package com.phoenix.domain;

import java.util.HashSet;
import java.util.Set;

//角色对象
public class Role {
	/*
	 * 
	  CREATE TABLE `sys_role` (
		  `role_id` bigint(32) NOT NULL AUTO_INCREMENT,
		  `role_name` varchar(32) NOT NULL COMMENT '角色名称',
		  `role_memo` varchar(128) DEFAULT NULL COMMENT '备注',
		  PRIMARY KEY (`role_id`)
		) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
	 */
	
	private Long roleId;
	private String roleName;
	private String roleMemo;
	//表达多对多
	private Set<User> users = new HashSet<User>();

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleMemo() {
		return roleMemo;
	}

	public void setRoleMemo(String roleMemo) {
		this.roleMemo = roleMemo;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
}
