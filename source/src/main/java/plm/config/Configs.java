package plm.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import plm.common.utils.XMLConfig;

@Configuration
public class Configs {

//	@Value("classpath:config/tomcat.xml")
//	File serverConfigXML;
//
//	@Bean
//	public ServerCfg readServerConfig() throws IOException {
//		return XMLConfig.toBean(serverConfigXML, ServerCfg.class);
//	}
//	
	@Value("classpath:config/tomcat.json")
	File serverConfigJson;
	
	@Bean
	public ServerCfg readServerConfig() throws IOException {
		return new ObjectMapper().readValue(serverConfigJson, ServerCfg.class); 
	}
	
	//@Bean
	public ServerCfg createTestBean() {
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
