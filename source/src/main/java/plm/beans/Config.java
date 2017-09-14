package plm.beans;

import java.io.Serializable;

import lombok.Data;

@Data
public class Config implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name, description, value;

	private long id;
}
