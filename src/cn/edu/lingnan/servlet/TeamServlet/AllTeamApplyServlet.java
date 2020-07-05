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
@WebServlet("/AllTeamApplyServlet")
public class AllTeamApplyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TeamDao tmd = new TeamDao();
        Vector<TeamCalss> v = tmd.findAllTeam();
        HttpSession s = request.getSession();
        s.setAttribute("applyAllTeaInfo", v);
        response.sendRedirect(request.getContextPath() + "/TeamModule/ApplyTeam.jsp");


    }
}
