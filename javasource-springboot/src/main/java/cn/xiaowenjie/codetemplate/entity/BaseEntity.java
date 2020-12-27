package cn.xiaowenjie.codetemplate.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * configs
 * @author 
 */
@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer creator;

    private Date createTime;

    private Date updateTime;

}