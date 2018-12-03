package org.ibase4j.mapper;

import java.util.List;
import java.util.Map;

import org.ibase4j.model.SysMenu;

interface SysMenuMapper {
	/** 获取所有权限 */
	public List<Map<String, String>> getPermissions();
}