package plm.config;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import lombok.Data;

@Data
@XStreamAlias("Server")
public class ServerCfg {

	@XStreamAsAttribute
	private int port = 8005;

	@XStreamAsAttribute
	private String shutDown = "SHUTDOWN";

	private List<ServiceCfg> services;

}
