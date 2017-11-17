package plm.common.utils;

import java.io.File;

import com.thoughtworks.xstream.XStream;

/**
 * XML工具类
 * 
 * @author 肖文杰
 *
 */
public class XMLConfig {

	public static String toXML(Object obj) {
		XStream xstream = new XStream();

		xstream.autodetectAnnotations(true);
		// xstream.processAnnotations(Server.class);

		return xstream.toXML(obj);
	}

	public static <T> T toBean(String xml, Class<T> cls) {
		XStream xstream = new XStream();

		xstream.processAnnotations(cls);
		T obj = (T) xstream.fromXML(xml);

		return obj;
	}

	public static <T> T toBean(File file, Class<T> cls) {
		XStream xstream = new XStream();

		xstream.processAnnotations(cls);
		T obj = (T) xstream.fromXML(file);

		return obj;
	}

}
