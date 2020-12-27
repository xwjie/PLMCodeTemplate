package cn.xiaowenjie.codetemplate.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * configs
 * @author 
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Config extends BaseEntity {
    private String name;

    private String value;

    private String description;
}