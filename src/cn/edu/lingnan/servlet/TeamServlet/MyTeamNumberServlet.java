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
         *���ܾ��ǲ����ҵ��Ŷӳ�Ա
         * ���жϣ������¼���ѧ����һ��ѧ����¼����ô�Ϳ���ȥ���������
         * �������ѧ��û�вμ��κ��Ŷӣ���ô�Ϳ��Իص��Ŷ���ҳ�����ҿ��Լ�һ��������ʾû�вμ��κ��Ŷӡ�
         */
        HttpSession s = request.getSession();
        StudentCalss stcC = (StudentCalss) s.getAttribute("userInfo");

        RelationDao rd = new RelationDao();
        RelationClass rc = rd.findRelationBySid(stcC.getSid());
        if (rc.getState() == 2) {
            System.out.println("�����");
            response.setCharacterEncoding("GBK");
            PrintWriter out = response.getWriter();
            out.print("<script>alert('���Ѿ��ύ��һ�����룬���ڵȴ�����У�������ѡ����������'); window.location='/TeamModule/MyTeamHomepage.jsp' </script>");
            out.flush();
            out.close();
        } else {
            String n = request.getParameter("pageNow");
            System.out.println("��ѯ�ҵ��Ŷӳ�Ա��ȡ��ҳ��" + n);
            System.out.println("��ѯ�ҵ��Ŷӳ�Աͨ��������Ϣ��session��ȡ��ѧ��" + stcC.getSid());
            int pageNow = 1;
            if (n != null) {
                pageNow = Integer.parseInt(n);
            }
            Vector<TeamNumber> myTeam = rd.findAllTeaNumberFenYe(stcC.getSid(), pageNow);
            s.setAttribute("teamNumber", myTeam);
            TeamCalss stc = (TeamCalss) s.getAttribute("teamInfo");
            if (stc.getTid() == null) {
                System.out.println("�ڲ�ѯ�ҵ��Ŷӳ�Ա��ʱ���ж����ѧ����û�м�¼��û�м�¼");

                response.setCharacterEncoding("GBK");
                PrintWriter out = response.getWriter();
                out.print("<script>alert('����û�вμ��Ŷӣ�������ѡ����������'); window.location='/TeamModule/MyTeamHomepage.jsp' </script>");
                out.flush();
                out.close();
            } else {
                System.out.println("�ڲ�ѯ�ҵ��Ŷӳ�Ա��ʱ���ж����ѧ����û�м�¼�����ڼ�¼");
                response.sendRedirect(request.getContextPath() + "/TeamModule/MyTeamNumber.jsp");
            }

        }


    }
}
