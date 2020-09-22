package com.xf.demo03.repository;

import com.xf.demo03.model.UserRoleRel;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xfgg
 */
public interface UserRoleRelRepository extends JpaRepository<UserRoleRel,String> {
    /**
     * 查询关联用户的角色数据
     * @param userId
     * @return
     */
    List<UserRoleRel> findByUserId(@Param("userId") String userId);
}
