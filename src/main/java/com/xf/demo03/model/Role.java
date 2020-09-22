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
@Table(name="role")
public class Role {
    @Id
    private String id;
    private String name;
}
