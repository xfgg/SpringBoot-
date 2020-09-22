package com.xf.demo03.repository;

import com.xf.demo03.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xfgg
 */
public interface RoleRepository extends JpaRepository<Role,String> {
}
