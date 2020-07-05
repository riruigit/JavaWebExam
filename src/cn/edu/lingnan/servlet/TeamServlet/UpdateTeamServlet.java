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
         *�����Ŷ���Ϣ��Ӧ���ǹ���Ա����
         *Ӧ�����Ƚ���servletȻ����ж��ǲ��ǹ���Ա��
         * ����ǹ���Ա�Ļ�����ֱ��ȥ�޸ĵ�ҳ�棬��������Ǿͻص�ѧ����ҳ���е������Ǹ�
         */


        String tid = request.getParameter("tid");
        System.out.println("�Ŷ��޸Ļ�ȡ�Ĳ���" + tid);
        String tname = request.getParameter("tname");
        System.out.println("�Ŷ��޸Ļ�ȡ�Ĳ���" + tname);
        String tteacher = request.getParameter("tteacher");
        System.out.println("�Ŷ��޸Ļ�ȡ�Ĳ���" + tteacher);
        String titem = request.getParameter("titem");
        System.out.println("�Ŷ��޸Ļ�ȡ�Ĳ���" + titem);

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
            System.out.println("�Ŷӱ���޸ĳɹ�");
            response.setCharacterEncoding("GBK");
            PrintWriter out = response.getWriter();
            out.print("<script>alert('�޸ĳɹ�'); window.location='/admin/AllTeam.jsp' </script>");
            out.flush();
            out.close();
        }
        //  response.sendRedirect(request.getContextPath() + "/admin/AllTeam.jsp");

    }
}
