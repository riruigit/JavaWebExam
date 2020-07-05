package cn.edu.lingnan.servlet.Login;

import cn.edu.lingnan.dao.RelationDao;
import cn.edu.lingnan.dao.StudentDao;
import cn.edu.lingnan.dao.TeamDao;
import cn.edu.lingnan.dto.RelationClass;
import cn.edu.lingnan.dto.StudentCalss;
import cn.edu.lingnan.dto.TeamCalss;

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
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /*
     *(1）系统登录界面的servlet。页面获取账号和密码，然后调用studentDao里面的方法查询是不是存在。
     * 如果存在，就去homepage，如果没有的话，那就是一个错误页面，弹窗提示登录信息出现错误，请重新登录
     *（2） 登录完成之后，获取这个学生的个人信息，也就是在通过这个学生的个人信息。
     * 一个个人session 一个团队的session  还有一个权限的session（老师的过滤器）
     *
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("登录获取的账号和密码" + username + " " + password);

        StudentDao sdq = new StudentDao();
        TeamDao td = new TeamDao();
        RelationDao re = new RelationDao();

        StudentCalss st = sdq.findStudentByNamePassword(username, password);

        System.out.println("登陆后获取到的个人信息里面的学号是" + st.getSid());
        TeamCalss tc = td.findMyTeamBySid(st.getSid());
        RelationClass rc = re.findRelationBySid(st.getSid());


        HttpSession s = request.getSession();

        //分别放到session里面，方便后面的获取操作
        //这里有三个session一个是个人信息的，一个是团队信息的，一个是用来搞过滤器的。
        s.setAttribute("superuser", st.getSuperuser());
        s.setAttribute("userInfo", st);
        s.setAttribute("teamInfo", tc);
        s.setAttribute("relationInfo", rc);

        //如果存在这个学生，那就直接进去主页，如果没有，就去登录错误的界面
        if (st.getSid() != null) {
            response.sendRedirect(request.getContextPath() + "/StudentModule/StudentHomepage.jsp");
        } else {
            response.setCharacterEncoding("GBK");
            PrintWriter out = response.getWriter();
            out.print("<script>alert('你的登录信息有误！！！请选择其他操作'); window.location='index.html' </script>");
            out.flush();
            out.close();
        }

    }
}
