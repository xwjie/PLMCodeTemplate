package cn.xiaowenjie.codetemplate.entity;

import lombok.Data;

/**
 * @Description TODO
 * @Date 2020/12/29 0029
 * @Author 晓风轻 https://github.com/xwjie
 */
@Data
public class User extends BaseEntity {

    private String username;

    private String passwordMd5;

    private String salt;
}
