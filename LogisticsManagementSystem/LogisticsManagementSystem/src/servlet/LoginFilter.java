package servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebFilter(value="*.action")
public class LoginFilter implements Filter{
    //初始化
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}
    //拦截内容
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		String currentURL = req.getRequestURI();  
        // 取得根目录所对应的绝对路径:  
        String targetURL = currentURL.substring(currentURL.indexOf("/", 1),currentURL.length());  
        // 截取到当前文件名用于比较  
        HttpSession session = req.getSession(false);  
    	// 判断当前页是否是重定向以后的登录页面页面，如果是就不做session的判断，防止出现死循环  
        if (!"/login.html".equals(targetURL)) {
            if (session == null || session.getAttribute("loginName") == null) {  
                resp.sendRedirect(req.getContextPath() + "/login.html");  
                return;  
            }  
        }  
        // 加入filter链继续向下执行  
       chain.doFilter(req, resp);
		
	}
    //结束
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
