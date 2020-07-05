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

    /*学生注册的servlet
     *页面获取参数，获取了参数之后就判断这个学生存不存在，
     * 如果存在的话就直接跳转注册错误界面，然后在重新注册！
     *如果不存在的话，那就直接回到登陆界面*/

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid = request.getParameter("sid");
        String name = request.getParameter("sname");
        String age = request.getParameter("age");
        String depart = request.getParameter("depart");
        String gender = request.getParameter("gender");
        String superuser = request.getParameter("superuser");
        String password = request.getParameter("password");
        System.out.println("注册页面获取的参数" + sid + name + age + depart + gender + superuser + password);
        StudentDao sd = new StudentDao();
        StudentCalss st = new StudentCalss();
        st.setSid(sid);
        st.setSname(name);
        st.setAge(Integer.parseInt(age));
        st.setDepart(depart);
        st.setGender(gender);
        st.setSuperuser(Integer.parseInt(superuser));
        st.setPassword(password);

        //学生表的插入的参数一个类，所以这里得设置一个类加进去
        //判断，存在这个学生，就去注册错误界面，不然就直接插入成功

        if (sd.findStuBySid(sid)) {
            response.setCharacterEncoding("GBK");
            PrintWriter out = response.getWriter();
            out.print("<script>alert('这个学号已经被注册！！！请选择其他操作'); window.location='index.html' </script>");
            out.flush();
            out.close();
        } else {
            System.out.println("学生注册返回值" + sd.insertStu(st));
            response.setCharacterEncoding("GBK");
            PrintWriter out = response.getWriter();
            out.print("<script>alert('注册成功'); window.location='index.html' </script>");
            out.flush();
            out.close();
            //response.sendRedirect(request.getContextPath() + "index.html");
        }

    }
}
