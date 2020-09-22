package com.xf.demo03.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author xfgg
 */
@Entity
@Data
@Table(name = "user_role_rel")
public class UserRoleRel {
    @Id
    private String userId;
    private String roleId;
}
