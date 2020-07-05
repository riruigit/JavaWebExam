package cn.edu.lingnan.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 18364
 */
@WebFilter(filterName = "AuthorityFilter", urlPatterns = "/admin/*")
public class AuthorityFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //1.��ȡ��¼�û�Ȩ�� session
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession s = request.getSession();
        Integer superuser = (Integer) s.getAttribute("superuser");
        System.out.println("��ǰ�û�����ԱȨ��" + superuser);

        //2.�����û�Ȩ��ȥ����Ӧ��ҳ�档1.��ȥ�����ȥ����2.authority.html 3.index.html
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if (superuser != null) {
            if (superuser == 1) {
                filterChain.doFilter(servletRequest, servletResponse);

            } else {
                resp.sendRedirect(request.getContextPath() + "/StudentModule/NotAdminStudent.jsp");

            }

        } else {
            resp.sendRedirect(request.getContextPath() + "/index.html");

        }


    }
}
