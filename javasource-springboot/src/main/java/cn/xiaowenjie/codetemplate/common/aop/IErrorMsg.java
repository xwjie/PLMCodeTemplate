package cn.xiaowenjie.codetemplate.common.aop;

import cn.xiaowenjie.codetemplate.common.beans.ResultBean;

/**
 * @Description TODO
 * @Date 2020/12/20 0020
 * @Author 晓风轻 https://github.com/xwjie
 */
public interface IErrorMsg {
    void setMsg(String msg);

    void setCode(int code);
}
