package plm.common.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import plm.common.utils.UserUtil;

/**
 * 用户信息相关的filter
 * 
 * @author 晓风轻 https://xwjie.github.io/PLMCodeTemplate/
 *
 */
public class UserFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
      FilterChain chain) throws IOException, ServletException {

    // 得到用户个人相关的信息（登陆的用户，用户的语言）
    fillUserInfo((HttpServletRequest) request);

    try {
      chain.doFilter(request, response);
    } finally {
      // 由于tomcat线程重用，记得清空
      clearAllUserInfo();
    }
  }

  private void clearAllUserInfo() {
    UserUtil.clearAllUserInfo();
  }

  private void fillUserInfo(HttpServletRequest request) {
    // 用户信息
    String user = getUserFromSession(request);

    if (user != null) {
      UserUtil.setUser(user);
    }

    // 语言信息
    String locale = getLocaleFromCookies(request);

    // 放入到threadlocal，同一个线程任何地方都可以拿出来
    if (locale != null) {
      UserUtil.setLocale(locale);
    }
  }

  private String getLocaleFromCookies(HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();

    if (cookies == null) {
      return null;
    }

    for (int i = 0; i < cookies.length; i++) {
      if (UserUtil.KEY_LANG.equals(cookies[i].getName())) {
        return cookies[i].getValue();
      }
    }

    return null;
  }

  private String getUserFromSession(HttpServletRequest request) {
    HttpSession session = request.getSession(false);

    if (session == null) {
      return null;
    }

    // 从session中获取用户信息放到工具类中
    return (String) session.getAttribute(UserUtil.KEY_USER);
  }

  @Override
  public void destroy() {

  }

}
