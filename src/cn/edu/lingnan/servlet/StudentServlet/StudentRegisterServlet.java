package cn.edu.lingnan.servlet.StudentServlet;

import cn.edu.lingnan.dao.StudentDao;
import cn.edu.lingnan.dto.StudentCalss;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 18364
 */
@WebServlet("/StudentRegisterServlet")
public class StudentRegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }

    /*ѧ��ע���servlet
     *ҳ���ȡ��������ȡ�˲���֮����ж����ѧ���治���ڣ�
     * ������ڵĻ���ֱ����תע�������棬Ȼ��������ע�ᣡ
     *��������ڵĻ����Ǿ�ֱ�ӻص���½����*/

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid = request.getParameter("sid");
        String name = request.getParameter("sname");
        String age = request.getParameter("age");
        String depart = request.getParameter("depart");
        String gender = request.getParameter("gender");
        String superuser = request.getParameter("superuser");
        String password = request.getParameter("password");
        System.out.println("ע��ҳ���ȡ�Ĳ���" + sid + name + age + depart + gender + superuser + password);
        StudentDao sd = new StudentDao();
        StudentCalss st = new StudentCalss();
        st.setSid(sid);
        st.setSname(name);
        st.setAge(Integer.parseInt(age));
        st.setDepart(depart);
        st.setGender(gender);
        st.setSuperuser(Integer.parseInt(superuser));
        st.setPassword(password);

        //ѧ����Ĳ���Ĳ���һ���࣬�������������һ����ӽ�ȥ
        //�жϣ��������ѧ������ȥע�������棬��Ȼ��ֱ�Ӳ���ɹ�

        if (sd.findStuBySid(sid)) {
            response.setCharacterEncoding("GBK");
            PrintWriter out = response.getWriter();
            out.print("<script>alert('���ѧ���Ѿ���ע�ᣡ������ѡ����������'); window.location='index.html' </script>");
            out.flush();
            out.close();
        } else {
            System.out.println("ѧ��ע�᷵��ֵ" + sd.insertStu(st));
            response.setCharacterEncoding("GBK");
            PrintWriter out = response.getWriter();
            out.print("<script>alert('ע��ɹ�'); window.location='index.html' </script>");
            out.flush();
            out.close();
            //response.sendRedirect(request.getContextPath() + "index.html");
        }

    }
}
