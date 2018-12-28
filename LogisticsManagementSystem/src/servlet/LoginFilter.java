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
		Adm_ChcekOrd ac = new Adm_ChcekOrd();
		
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
            //��ȡ��Ҫ��֤�ĵ�ַ
            String needURL = targetURL.substring(1,3);
           //�õ�Ȩ��
            int permission = (Integer) session.getAttribute("getPermission");
            //��ͬҳ�����費ͬȨ�޵��ж�
            if("Us".equals(needURL)) {
            	if(permission < 0) {
            		resp.sendRedirect(req.getContextPath() + "/login.html");
            		System.out.println("Ȩ�޲���");
            	}
            }
            else if("Ad".equals(needURL)) {
            	if(permission < 1) {
            		resp.sendRedirect(req.getContextPath() + "/login.html");
            		System.out.println("Ȩ�޲���");
            	}
            }
            else if("He".equals(needURL)) {
            	if(permission < 2) {
            		resp.sendRedirect(req.getContextPath() + "/login.html");
            		System.out.println("Ȩ�޲���");
            	}
            }
            
        }  
        // ����filter����������ִ��  `
       chain.doFilter(req, resp);
		
	}
    //����
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
