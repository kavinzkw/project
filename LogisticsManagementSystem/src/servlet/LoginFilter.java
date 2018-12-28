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

import DaoDemo.Adm_ChcekOrd;
@WebFilter(value="*.html")
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
		Adm_ChcekOrd ac = new Adm_ChcekOrd();
		
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
            //截取需要验证的地址
            String needURL = targetURL.substring(1,3);
           //得到权限
            int permission = (Integer) session.getAttribute("getPermission");
            //不同页面所需不同权限的判断
            if("Us".equals(needURL)) {
            	if(permission < 0) {
            		resp.sendRedirect(req.getContextPath() + "/login.html");
            		System.out.println("权限不够");
            	}
            }
            else if("Ad".equals(needURL)) {
            	if(permission < 1) {
            		resp.sendRedirect(req.getContextPath() + "/login.html");
            		System.out.println("权限不够");
            	}
            }
            else if("He".equals(needURL)) {
            	if(permission < 2) {
            		resp.sendRedirect(req.getContextPath() + "/login.html");
            		System.out.println("权限不够");
            	}
            }
            
        }  
        // 加入filter链继续向下执行  `
       chain.doFilter(req, resp);
		
	}
    //结束
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
