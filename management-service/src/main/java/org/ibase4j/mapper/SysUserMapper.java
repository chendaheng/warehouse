package org.ibase4j.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.ibase4j.model.SysMenu;
import org.ibase4j.model.SysRole;
import org.ibase4j.model.SysUser;

public interface SysUserMapper {
	
//	List<SysUser> queryUserPage();
	
	List<Long> selectIdByDept(RowBounds rowBounds, @Param("cm") Map<String, Object> params);
	List<Long> selectIdByJob(RowBounds rowBounds, @Param("cm") Map<String, Object> params);
	List<Long> selectUserIdByLike(RowBounds rowBounds, @Param("cm") Map<String, Object> params);
	
	void deleteDeptByUserId(@Param("id") Long id);
	void deleteJobByUserId(@Param("id") Long id);
	void deleteRoleByUserId(@Param("id") Long id);
	
	List<Long> selectIdByDept(@Param("cm") Map<String, Object> params);
	List<SysUser> selectAttenceUser(@Param("cm") Map<String, Object> params);
	List<SysRole> queryRoleByUserAccount(@Param("userId") String userId);
	List<SysUser> queryUserByAccount(@Param("userId") String userId);
	Long queryRoleType(@Param("userId") Long userId);

}
