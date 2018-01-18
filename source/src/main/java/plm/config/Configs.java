package plm.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *  这里演示的是如果在项目中做配置。
 *  
 *  重点是要引入【中介】，业务代码不要直接和配置代码耦合。
 * 
 * @author 晓风轻  https://xwjie.github.io/PLMCodeTemplate/
 */
@Configuration
public class Configs {

	public static void main(String[] args) {
		Configs demo = new Configs();

		String json = toJson(demo.createTestBean());

		System.out.println(json);
	}

	private static String toJson(Object obj) {
		return JSON.toJSONString(obj);
	}

	// @Value("classpath:config/tomcat.xml")
	// File serverConfigXML;
	//
	// @Bean
	// public ServerCfg readServerConfig() throws IOException {
	// return XMLConfig.toBean(serverConfigXML, ServerCfg.class);
	// }
	//
	@Value("classpath:config/tomcat.json")
	File serverConfigJson;

	// @Bean
	public ServerCfg readServerConfig() throws IOException {
		return new ObjectMapper().readValue(serverConfigJson, ServerCfg.class);
	}

	/**
	 * 直接创建配置对象，测试通过之后在生成对应的json/xml
	 * 
	 * @return
	 */
	@Bean
	public ServerCfg createTestBean() {
		System.out.println("\nConfigs.createTestBean() --------- test data-------");
		ServerCfg server = new ServerCfg();

		//
		List<ServiceCfg> services = new ArrayList<ServiceCfg>();
		server.setServices(services);

		//
		ServiceCfg service = new ServiceCfg();
		services.add(service);

		service.setName("Kitty");

		//
		List<ConnectorCfg> connectors = new ArrayList<ConnectorCfg>();
		service.setConnectors(connectors);

		//
		ConnectorCfg connectorhttp11 = new ConnectorCfg();

		connectorhttp11.setPort(8088);
		connectorhttp11.setProtocol("HTTP/1.1");

		connectors.add(connectorhttp11);

		//
		ConnectorCfg connectorAJP = new ConnectorCfg();

		connectorAJP.setPort(8089);
		connectorAJP.setProtocol("AJP");

		connectors.add(connectorAJP);

		return server;
	}

}
