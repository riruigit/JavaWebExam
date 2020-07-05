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


        System.out.println("��ɾ���ҵ��Ŷӵ�ʱ���ȡ��ѧ��" + quits.getSid());
        RelationDao quitR = new RelationDao();
        System.out.println("ɾ���ҵ��Ŷӵķ���ֵ" + quitR.deleteRelationBySid(quits.getSid()));
        //ɾ�����֮��Ӧ��ȥ���ҵ��Ŷ���ҳ��
        //�ٻص���ҳ֮ǰӦ�ø���һ��session
        TeamDao quitT = new TeamDao();
        TeamCalss tc = quitT.findMyTeamBySid(quits.getSid());
        RelationClass rcs = quitR.findRelationBySid(quits.getSid());
        s.setAttribute("teamInfo", tc);
        s.setAttribute("relationInfo", rcs);
        System.out.println("���˳��Ŷӵ�ʱ���ж����ѧ����û�м�¼���μ����Ŷ�");
        response.sendRedirect(request.getContextPath() + "/TeamModule/MyTeamHomepage.jsp");


    }
}
