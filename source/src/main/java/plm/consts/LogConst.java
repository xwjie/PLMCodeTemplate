package plm.consts;

/**
 *  日志注解里面的常量，自己增加即可
 *
 * @author  晓风轻 https://github.com/xwjie/PLMCodeTemplate
 */
public interface LogConst {
    /**
     *  操作
     *  为了节省日志文件大小，这些常量可以使用单字母代替
     */
    String ACTION_ADD = "add";

    String ACTION_DELETE = "del";

    String ACTION_UPDATE = "update";

    String ACTION_QUERY= "query";

    /**
     *  对象类型
     */
    String ITEM_TYPE_CONFIG = "config";


}
