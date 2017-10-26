package plm.common.utils;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

/**
 * 工具类的注入
 * 
 * @author  肖文杰
 * 
 */
@Component
public class StaticFieldInjectionConfiguration {

    @Autowired
    MessageSource resources;

    @PostConstruct
    private void init() {
    	System.out.println("\n\n-----StaticFieldInjectionConfiguration----");
		CheckUtil.setResources(resources);
    }
}