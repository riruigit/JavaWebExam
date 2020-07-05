package cn.edu.lingnan.servlet.RelationServlet;

import cn.edu.lingnan.dao.RelationDao;
import cn.edu.lingnan.dto.RelationClass;
import cn.edu.lingnan.dto.TeamNumber;

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
@WebServlet("/CheckTeamApplyServlet")
public class CheckTeamApplyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
         *�������裬Ӧ�����ȵ��һ��servlet���÷������ѹ�ϵ���state״̬��2��ȫ�����ҳ�����
         * �ҳ�����֮��Ϳ���ȥjspҳ���ˣ����jspҳ��Ӧ�ð���ѧ��ѧ���Լ������������Ŷӡ���
         * Ȼ��ҳ���������ѡ�һ�������ͨ����һ����ͨ����
         * Ȼ����������Ҫjspҳ�洫�ݲ����ġ���������һ��servlet��Ȼ��Ϳ��Ը���һ��session
         * Ȼ��ͼ����ص���һ��servlet
         */
        RelationDao checkR = new RelationDao();
        Vector<TeamNumber> checkV = checkR.findAllCheck();
        HttpSession s = request.getSession();
        s.setAttribute("allCheckTeamInfo", checkV);
        response.sendRedirect(request.getContextPath() + "/admin/CheckTeamApply.jsp");


    }
}
