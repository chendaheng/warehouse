package org.ibase4j.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SysDeptMapper {
	void deleteUserByDeptId(@Param("deptId") Long deptId);
	
	void deleteJobByDeptId(@Param("deptId") Long deptId);
	
	List<Long> selectDeptByParentId(@Param("deptId") Long deptId);
}