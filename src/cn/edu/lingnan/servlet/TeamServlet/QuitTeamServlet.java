package cn.edu.lingnan.servlet.TeamServlet;

import cn.edu.lingnan.dao.RelationDao;
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

/**
 * @author 18364
 */
@WebServlet("/QuitTeamServlet")
public class QuitTeamServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession();
        StudentCalss quits = (StudentCalss) s.getAttribute("userInfo");
        RelationDao rd = new RelationDao();


        System.out.println("在删除我的团队的时候获取的学号" + quits.getSid());
        RelationDao quitR = new RelationDao();
        System.out.println("删除我的团队的返回值" + quitR.deleteRelationBySid(quits.getSid()));
        //删除完成之后应该去到我的团队主页。
        //再回到主页之前应该更新一波session
        TeamDao quitT = new TeamDao();
        TeamCalss tc = quitT.findMyTeamBySid(quits.getSid());
        RelationClass rcs = quitR.findRelationBySid(quits.getSid());
        s.setAttribute("teamInfo", tc);
        s.setAttribute("relationInfo", rcs);
        System.out.println("在退出团队的时候判断这个学生有没有记录：参加了团队");
        response.sendRedirect(request.getContextPath() + "/TeamModule/MyTeamHomepage.jsp");


    }
}
