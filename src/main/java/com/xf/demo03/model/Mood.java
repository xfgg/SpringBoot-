package com.xf.demo03.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author xfgg
 */
@Data
@Entity
@Table(name="mood")
public class Mood implements Serializable {
    @Id
    private String id;
    private String content;
    private String userId;
    private Integer praiseNum;
    private Date publishTime;
}
