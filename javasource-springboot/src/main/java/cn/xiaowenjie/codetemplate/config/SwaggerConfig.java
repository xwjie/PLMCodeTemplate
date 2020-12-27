package cn.xiaowenjie.codetemplate.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

/**
 * @Description TODO
 * @Date 2020/12/27 0027
 * @Author 晓风轻 https://github.com/xwjie
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.enable:true}")
    private boolean enableSwagger;

    @Bean
    public Docket customImplementation() {
        return new Docket(SWAGGER_2).enable(enableSwagger);
    }
}
