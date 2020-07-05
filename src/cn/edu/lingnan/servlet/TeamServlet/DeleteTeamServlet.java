package cn.edu.lingnan.servlet.TeamServlet;

import cn.edu.lingnan.dao.TeamDao;
import cn.edu.lingnan.dto.StudentCalss;
import cn.edu.lingnan.dto.TeamCalss;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;

/**
 * @author 18364
 */
@WebServlet("/DeleteTeamServlet")
public class DeleteTeamServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
         *删除团队奥，这个就先去检查关系表，有记录就直接删除了，团队表先做软删除好了
         */
        String tid = request.getParameter("tid");
        System.out.println("团队删除获取的参数" + tid);
        TeamDao delTeam = new TeamDao();
        String del = request.getParameter("del");
        System.out.println("全部学生页面获取" + del);
        //页面del参数是用来判断是不是批量删除，因为allStudent这个界面存在两个方式删除。批量删除就会把del参数传进来
        if (del == null)
        //删除一条
        {
            System.out.println("删除团队的返回值" + delTeam.deleteTeaByTid(tid));
        }
        //删除多条
        else {
            String alltid = request.getParameter("alltid");
            System.out.println(alltid);
            String[] temp = alltid.split(",");
            for (String s : temp) {
                delTeam.deleteTeaByTid(s);
            }
        }
        Vector<TeamCalss> delT = delTeam.findAllTeam();
        HttpSession s = request.getSession();
        StudentCalss stcC = (StudentCalss) s.getAttribute("userInfo");
        TeamCalss tc = delTeam.findMyTeamBySid(stcC.getSid());
        s.setAttribute("teamInfo", tc);
        s.setAttribute("allTeaInfo", delT);
        response.sendRedirect(request.getContextPath() + "/admin/AllTeam.jsp");
    }
}
