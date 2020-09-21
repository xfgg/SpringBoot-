package com.xf.demo03.repository;


import com.xf.demo03.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xfgg
 */
public interface UserRepository extends JpaRepository<User,String> {
}
