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
        System.out.println("���Ŷ���˽����ȡ�Ĳ���" + delApply);
        //ҳ��del�����������ж��ǲ�������ɾ������ΪallStudent����������������ʽɾ��������ɾ���ͻ��del����������
        if (delApply == null)
        //ɾ��һ��
        {
            String sid = request.getParameter("applysid");
            System.out.println(sid);
            System.out.println("�Ŷ���˽���" + applyY.applyRelation(sid));

        }
        //ɾ������
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
        out.print("<script>alert('���ͨ�����Ѽ����Ŷӣ�����'); window.location='/CheckTeamApplyServlet' </script>");
        out.flush();
        out.close();
        // response.sendRedirect(request.getContextPath() + "/CheckTeamApplyServlet");

    }
}
