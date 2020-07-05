package cn.edu.lingnan.servlet.RelationServlet;

import cn.edu.lingnan.dao.RelationDao;
import cn.edu.lingnan.dto.RelationClass;

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
@WebServlet("/AllRelationServlet")
public class AllRelationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RelationDao rd = new RelationDao();
        HttpSession s = request.getSession();
        //3���ݴ�����,ȥ��Ӧ��ҳ��
        //2������Щ���������÷���
        String n = request.getParameter("relationPageNow");
        //ҳ���ȡ����
        int relationPageNow = 1;
        if (n != null) {
            relationPageNow = Integer.parseInt(n);
            //ת������
        }
        Vector<RelationClass> RV = rd.findAllRelationFenYe(relationPageNow);
        s.setAttribute("allReaInfo", RV);
        //3���ݴ�����,ȥ��Ӧ��ҳ��
        response.sendRedirect(request.getContextPath() + "/admin/AllRelation.jsp");


    }
}
