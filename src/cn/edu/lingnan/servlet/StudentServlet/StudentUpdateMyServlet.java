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
@WebServlet("/StudentUpdateMyServlet")
public class StudentUpdateMyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }

    /*
     *这个功能比较简单，仅仅是修改自己的信息
     * 修改完了直接回到学生个人主页呗，小小特色就是一个弹窗把，您确定要修改自己的信息这样
     * 修改了就得更新一下session，不然的话可能不会实时更新
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid = request.getParameter("sid");
        String name = request.getParameter("sname");
        String age = request.getParameter("age");
        String depart = request.getParameter("depart");
        String gender = request.getParameter("gender");
        String superuser = request.getParameter("superuser");
        String password = request.getParameter("password");
        System.out.println("修改页面获取的参数" + sid + name + age + depart + gender + superuser + password);
        StudentDao sd = new StudentDao();
        StudentCalss ust = new StudentCalss();
        ust.setSid(sid);
        ust.setSname(name);
        ust.setAge(Integer.parseInt(age));
        ust.setDepart(depart);
        ust.setGender(gender);
        ust.setSuperuser(Integer.parseInt(superuser));
        ust.setPassword(password);

        //学生表的修改的参数一个类，所以这里得设置一个类加进去
        //修改完得更新了一波session，然后去到我的学生主页

        HttpSession s = request.getSession();
        s.setAttribute("superuser", ust.getSuperuser());
        s.setAttribute("userInfo", ust);


        System.out.println("学生修改返回值" + sd.updateStu(ust));
        response.setCharacterEncoding("GBK");
        PrintWriter out = response.getWriter();
        out.print("<script>alert('修改成功'); window.location='/StudentModule/StudentHomepage.jsp' </script>");
        out.flush();
        out.close();
        // response.sendRedirect(request.getContextPath() + "/StudentModule/StudentHomepage.jsp");


    }
}
