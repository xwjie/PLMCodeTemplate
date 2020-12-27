package cn.xiaowenjie.codetemplate.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Description TODO
 * @Date 2020/12/27 0027
 * @Author 晓风轻 https://github.com/xwjie
 */
@Configuration
@MapperScan("cn.xiaowenjie.codetemplate.daos")
public class MyBatisConfig {

}
