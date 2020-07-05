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
import java.io.PrintWriter;
import java.util.Vector;

/**
 * @author 18364
 */
@WebServlet("/ApplyTeamServlet")
public class ApplyTeamServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
         *主页点击，然后去到一个界面，显示全部的团队，然后点击加号就可以加入团队了。
         * 加入团队的操作是写一条关系到关系表，但是团队已经写死了，就不会触及外键的问题
         * 先点击加号，然后验证，这个学生是不是存在了一个团队，
         * 如果存在的话，那么就跳转到退出团队的界面，问他是不是要退出。如果没有，那就直接执行插入的操作
         *1.进到一个显示全部团队界面的
         *2.判断这个学生有没有参加团队
         *3.插入的是关系表
         */
        HttpSession s = request.getSession();
        TeamCalss teC = (TeamCalss) s.getAttribute("teamInfo");
        StudentCalss stud = (StudentCalss) s.getAttribute("userInfo");
        RelationDao ral = new RelationDao();
        TeamDao td = new TeamDao();
        RelationClass relaC = new RelationClass();
        String tid = request.getParameter("Teamtid");
        relaC.setTid(tid);
        relaC.setSid(stud.getSid());
        relaC.setPosition("队员");
        relaC.setState(1);
        System.out.println("111" + teC.getTid());
        /*
         *这里是添加relation（申请一个团队）的判断，可以从一个session获取
         * 假如session的值是1，那个就可以直接去到删除界面  //这个已经做好了
         * 假如这个session的值是二，那个就可以提示已经提交申请，等待审核了，请不要重复提交   //这个要做一个页面。弹窗提示
         * 假如这个session的值是空，那就应该直接提示（你确定要提交申请）     //就插入函数
         */
//
        if (ral.findRelationBySid(stud.getSid()).getState() == 1) {
            System.out.println("在申请加入团队的时候，这个学生参加了一个团队，准备跳转到删除界面");
            response.sendRedirect(request.getContextPath() + "/TeamModule/QuitTeam.jsp");
        } else {
            if (ral.findRelationBySid(stud.getSid()).getState() == 2) {
                System.out.println("重复提交");


                response.setCharacterEncoding("GBK");
                PrintWriter out = response.getWriter();
                out.print("<script>alert('您已经提交了一个申请，正在等待审核中！！！请选择其他操作'); window.location='/TeamModule/MyTeamHomepage.jsp' </script>");
                out.flush();
                out.close();


            } else {
                System.out.println("在申请加入团队的时候，这个学生没有参加任何团队，可以直接添加了");
                System.out.println("申请加入成功" + ral.insertRelation(relaC));
                TeamCalss tc = td.findMyTeamBySid(stud.getSid());
                s.setAttribute("teamInfo", tc);
                RelationDao quitR = new RelationDao();
                RelationClass rc = quitR.findRelationBySid(stud.getSid());
                s.setAttribute("relationInfo", rc);
                response.setCharacterEncoding("GBK");
                PrintWriter out = response.getWriter();
                out.print("<script>alert('提交申请成功，正在等待管理员审核！！！请选择其他操作'); window.location='/TeamModule/MyTeamHomepage.jsp' </script>");
                out.flush();
                out.close();
                //response.sendRedirect(request.getContextPath() + "/TeamModule/MyTeamHomepage.jsp");
            }


        }


    }
}
