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
         *ɾ���ŶӰ£��������ȥ����ϵ���м�¼��ֱ��ɾ���ˣ��Ŷӱ�������ɾ������
         */
        String tid = request.getParameter("tid");
        System.out.println("�Ŷ�ɾ����ȡ�Ĳ���" + tid);
        TeamDao delTeam = new TeamDao();
        String del = request.getParameter("del");
        System.out.println("ȫ��ѧ��ҳ���ȡ" + del);
        //ҳ��del�����������ж��ǲ�������ɾ������ΪallStudent����������������ʽɾ��������ɾ���ͻ��del����������
        if (del == null)
        //ɾ��һ��
        {
            System.out.println("ɾ���Ŷӵķ���ֵ" + delTeam.deleteTeaByTid(tid));
        }
        //ɾ������
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
