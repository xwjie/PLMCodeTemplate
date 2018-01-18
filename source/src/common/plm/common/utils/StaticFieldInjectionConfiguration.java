package plm.common.utils;

import static plm.common.utils.StringUtil.isEmpty;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

/**
 * 工具类的注入
 * 
 * @author 肖文杰 https://xwjie.github.io/PLMCodeTemplate/
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