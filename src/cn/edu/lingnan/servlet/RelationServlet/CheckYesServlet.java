package cn.edu.lingnan.servlet.RelationServlet;

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
@WebServlet("/CheckYesServlet")
public class CheckYesServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RelationDao applyY = new RelationDao();
        String delApply = request.getParameter("delApply");
        System.out.println("在团队审核界面获取的参数" + delApply);
        //页面del参数是用来判断是不是批量删除，因为allStudent这个界面存在两个方式删除。批量删除就会把del参数传进来
        if (delApply == null)
        //删除一条
        {
            String sid = request.getParameter("applysid");
            System.out.println(sid);
            System.out.println("团队审核界面" + applyY.applyRelation(sid));

        }
        //删除多条
        else {
            String allsid = request.getParameter("allsid");
            System.out.println(allsid);
            String[] temp = allsid.split(",");
            for (String s : temp) {
                applyY.applyRelation(s);
            }
        }
        HttpSession s = request.getSession();
        StudentCalss stc = (StudentCalss) s.getAttribute("userInfo");
        RelationClass rc = applyY.findRelationBySid(stc.getSid());
        s.setAttribute("relationInfo", rc);
        TeamDao Tm = new TeamDao();
        TeamCalss TmC = Tm.findMyTeamBySid(stc.getSid());
        s.setAttribute("relationInfo", rc);
        s.setAttribute("teamInfo", TmC);
        response.setCharacterEncoding("GBK");
        PrintWriter out = response.getWriter();
        out.print("<script>alert('审核通过，已加入团队！！！'); window.location='/CheckTeamApplyServlet' </script>");
        out.flush();
        out.close();
        // response.sendRedirect(request.getContextPath() + "/CheckTeamApplyServlet");

    }
}
