package cn.xiaowenjie.codetemplate.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * configs
 * @author 
 */
@Data
public class Config implements Serializable {
    private Integer id;

    private String name;

    private String value;

    private String description;

    private Integer creator;

    private Date creatTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}