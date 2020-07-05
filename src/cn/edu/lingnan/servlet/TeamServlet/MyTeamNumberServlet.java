package cn.edu.lingnan.servlet.TeamServlet;

import cn.edu.lingnan.dao.RelationDao;
import cn.edu.lingnan.dto.RelationClass;
import cn.edu.lingnan.dto.StudentCalss;
import cn.edu.lingnan.dto.TeamCalss;
import cn.edu.lingnan.dto.TeamNumber;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

@WebServlet("/MyTeamNumberServlet")
public class MyTeamNumberServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
         *功能就是查找我的团队成员
         * 先判断，假如登录这个学生有一条学生记录，那么就可以去到这个界面
         * 假如这个学生没有参加任何团队，那么就可以回到团队主页，并且可以加一个弹窗提示没有参加任何团队。
         */
        HttpSession s = request.getSession();
        StudentCalss stcC = (StudentCalss) s.getAttribute("userInfo");

        RelationDao rd = new RelationDao();
        RelationClass rc = rd.findRelationBySid(stcC.getSid());
        if (rc.getState() == 2) {
            System.out.println("审核中");
            response.setCharacterEncoding("GBK");
            PrintWriter out = response.getWriter();
            out.print("<script>alert('您已经提交了一个申请，正在等待审核中！！！请选择其他操作'); window.location='/TeamModule/MyTeamHomepage.jsp' </script>");
            out.flush();
            out.close();
        } else {
            String n = request.getParameter("pageNow");
            System.out.println("查询我的团队成员获取的页面" + n);
            System.out.println("查询我的团队成员通过个人信息的session获取的学号" + stcC.getSid());
            int pageNow = 1;
            if (n != null) {
                pageNow = Integer.parseInt(n);
            }
            Vector<TeamNumber> myTeam = rd.findAllTeaNumberFenYe(stcC.getSid(), pageNow);
            s.setAttribute("teamNumber", myTeam);
            TeamCalss stc = (TeamCalss) s.getAttribute("teamInfo");
            if (stc.getTid() == null) {
                System.out.println("在查询我的团队成员的时候判断这个学生有没有记录：没有记录");

                response.setCharacterEncoding("GBK");
                PrintWriter out = response.getWriter();
                out.print("<script>alert('您还没有参加团队！！！请选择其他操作'); window.location='/TeamModule/MyTeamHomepage.jsp' </script>");
                out.flush();
                out.close();
            } else {
                System.out.println("在查询我的团队成员的时候判断这个学生有没有记录：存在记录");
                response.sendRedirect(request.getContextPath() + "/TeamModule/MyTeamNumber.jsp");
            }

        }


    }
}
