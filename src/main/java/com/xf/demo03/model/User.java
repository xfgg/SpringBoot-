package com.xf.demo03.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;


/**
 * @author xfgg
 */
@Entity
@Data
public class User implements Serializable {
    @Id
    private String id;
    private String username;
    private String password;
}
