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
@WebServlet("/AllTeamServlet")
public class AllTeamServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TeamDao td = new TeamDao();
        Vector<TeamCalss> v = td.findAllTeam();
        HttpSession s = request.getSession();
        s.setAttribute("allTeaInfo", v);

        //����ǹ���Ա��ֱ��ȥ�޸�ҳ�棬������ǵĻ����Ǿ�ֱ��ȥ��ʾȫ���������޸�ҳ��
        //��ȡѧ��,�ӵ�¼ѧ���ĸ�����Ϣ��ȡ
        StudentCalss stc = (StudentCalss) s.getAttribute("userInfo");

        if (stc.getSuperuser() == 1) {
            response.sendRedirect(request.getContextPath() + "/admin/AllTeam.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/TeamModule/AllTeamNotAdmin.jsp");
        }


    }
}
