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
    //��ʼ��
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}
    //��������
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		String currentURL = req.getRequestURI();  
        // ȡ�ø�Ŀ¼����Ӧ�ľ���·��:  
        String targetURL = currentURL.substring(currentURL.indexOf("/", 1),currentURL.length());  
        // ��ȡ����ǰ�ļ������ڱȽ�  
        HttpSession session = req.getSession(false);  
    	// �жϵ�ǰҳ�Ƿ����ض����Ժ�ĵ�¼ҳ��ҳ�棬����ǾͲ���session���жϣ���ֹ������ѭ��  
        if (!"/login.html".equals(targetURL)) {
            if (session == null || session.getAttribute("loginName") == null) {  
                resp.sendRedirect(req.getContextPath() + "/login.html");  
                return;  
            }  
        }  
        // ����filter����������ִ��  
       chain.doFilter(req, resp);
		
	}
    //����
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
