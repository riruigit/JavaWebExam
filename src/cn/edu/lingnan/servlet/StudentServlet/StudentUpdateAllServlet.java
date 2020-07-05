package cn.edu.lingnan.servlet.StudentServlet;

import cn.edu.lingnan.dao.StudentDao;
import cn.edu.lingnan.dto.StudentCalss;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 18364
 */
@WebServlet("/StudentUpdateAllServlet")
public class StudentUpdateAllServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid = request.getParameter("sid");
        String name = request.getParameter("sname");
        String age = request.getParameter("age");
        String depart = request.getParameter("depart");
        String gender = request.getParameter("gender");
        String superuser = request.getParameter("superuser");
        String password = request.getParameter("password");
        System.out.println("所有管理员修改页面获取的参数" + sid + name + age + depart + gender + superuser + password);
        StudentDao sd = new StudentDao();
        StudentCalss ust = new StudentCalss();
        ust.setSid(sid);
        ust.setSname(name);
        ust.setAge(Integer.parseInt(age));
        ust.setDepart(depart);
        ust.setGender(gender);
        ust.setSuperuser(Integer.parseInt(superuser));
        ust.setPassword(password);
        System.out.println("学生修改返回值" + sd.updateStu(ust));
        response.setCharacterEncoding("GBK");
        PrintWriter out = response.getWriter();
        out.print("<script>alert('修改成功'); window.location='/StudentFindAllServlet' </script>");
        out.flush();
        out.close();
        // response.sendRedirect(request.getContextPath() + "/StudentFindAllServlet");
    }
}
