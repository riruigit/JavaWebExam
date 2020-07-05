package cn.edu.lingnan.servlet.TeamServlet;

import cn.edu.lingnan.dao.TeamDao;
import cn.edu.lingnan.dto.TeamCalss;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

/**
 * @author 18364
 */
@WebServlet("/UpdateTeamServlet")
public class UpdateTeamServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
         *更新团队信息，应该是管理员做的
         *应该是先进到servlet然后就判断是不是管理员，
         * 如果是管理员的话，就直接去修改的页面，如果不是那就回到学生主页，有弹窗的那个
         */


        String tid = request.getParameter("tid");
        System.out.println("团队修改获取的参数" + tid);
        String tname = request.getParameter("tname");
        System.out.println("团队修改获取的参数" + tname);
        String tteacher = request.getParameter("tteacher");
        System.out.println("团队修改获取的参数" + tteacher);
        String titem = request.getParameter("titem");
        System.out.println("团队修改获取的参数" + titem);

        TeamDao teaD = new TeamDao();
        TeamCalss teaC = new TeamCalss();
        teaC.setTid(tid);
        teaC.setTname(tname);
        teaC.setTteacher(tteacher);
        teaC.setTitem(titem);
        teaC.setState(1);
        System.out.println(teaD.updateTeam(teaC));

        Vector<TeamCalss> v = teaD.findAllTeam();
        HttpSession s = request.getSession();
        s.setAttribute("allTeaInfo", v);

        if (teaD.updateTeam(teaC)) {
            System.out.println("团队表的修改成功");
            response.setCharacterEncoding("GBK");
            PrintWriter out = response.getWriter();
            out.print("<script>alert('修改成功'); window.location='/admin/AllTeam.jsp' </script>");
            out.flush();
            out.close();
        }
        //  response.sendRedirect(request.getContextPath() + "/admin/AllTeam.jsp");

    }
}
