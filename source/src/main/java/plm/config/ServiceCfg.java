package plm.config;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import lombok.Data;

@Data
@XStreamAlias("Service")
public class ServiceCfg {

	@XStreamAsAttribute
	private String name;

	private List<ConnectorCfg> connectors;
}
