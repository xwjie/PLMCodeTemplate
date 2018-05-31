package plm.beans;

import lombok.Data;

import java.io.Serializable;

/**
 * 配置对象类
 * 
 * @author 晓风轻 https://github.com/xwjie/PLMCodeTemplate
 */
@Data
public class Config implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name, description, value;

	private long id;

	/**
	 * 创建者，demo使用字符串，实际上应该用对象id
	 */
	private String creator;
}
