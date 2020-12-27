package cn.xiaowenjie.codetemplate.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Date 2020/12/27 0027
 * @Author 晓风轻 https://github.com/xwjie
 */
@Configuration
@Slf4j
public class DruidConfig {

    @Value("${spring.datasource.url}")
    String dbUrl;

    /**
     * 配置Druid的属性，和DataSource进行绑定，前缀设置为：spring.datasource
     * 不配置的话，很多初始化的属性是没有绑定的
     *
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druid() {
        log.info("db url: {}", dbUrl);
        return new DruidDataSource();
    }

    /**
     * 配置druid监控
     * 配置一个管理后台的servlet
     * 访问地址：http://localhost:8080/druid/
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");

        Map<String, String> initParameters = new HashMap<>();

        //属性见：com.alibaba.druid.support.http.ResourceServlet
        initParameters.put("loginUsername", "admin");
        initParameters.put("loginPassword", "123456");
        initParameters.put("allow", "");
        initParameters.put("deny", "");
        bean.setInitParameters(initParameters);

        return bean;
    }

    /**
     * 配置一个web监控的filter
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean filterBean = new FilterRegistrationBean();

        filterBean.setFilter(new WebStatFilter());
        filterBean.setUrlPatterns(Arrays.asList("/*"));

        Map<String, String> initParameters = new HashMap<String, String>();
        //属性见：com.alibaba.druid.support.http.WebStatFilter
        initParameters.put("exclusions", "*.js,*.css,/druid/*");

        filterBean.setInitParameters(initParameters);

        return filterBean;
    }

}