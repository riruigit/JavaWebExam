package cn.edu.lingnan.servlet.RelationServlet;

import cn.edu.lingnan.dao.RelationDao;
import cn.edu.lingnan.dao.TeamDao;
import cn.edu.lingnan.dto.RelationClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 18364
 */
@WebServlet("/UpdateRelationServlet")
public class UpdateRelationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid = request.getParameter("sid");
        System.out.println("��ϵ�޸Ļ�ȡ�Ĳ���" + sid);
        String tid = request.getParameter("tid");
        System.out.println("��ϵ�޸Ļ�ȡ�Ĳ���" + tid);
        String position = request.getParameter("position");
        System.out.println("��ϵ�޸Ļ�ȡ�Ĳ���" + position);

        RelationClass updateRelationClass = new RelationClass();
        RelationDao updateRelationDao = new RelationDao();
        updateRelationClass.setSid(sid);
        updateRelationClass.setTid(tid);
        updateRelationClass.setPosition(position);
        updateRelationClass.setState(1);
        System.out.println("" + updateRelationDao.updateRelation(updateRelationClass));

        TeamDao ut = new TeamDao();

        if (ut.findTeaByTid(tid)) {
            response.setCharacterEncoding("GBK");
            PrintWriter out = response.getWriter();
            out.print("<script>alert('�޸ĳɹ�'); window.location='/AllRelationServlet' </script>");
            out.flush();
            out.close();
        } else {
            response.setCharacterEncoding("GBK");
            PrintWriter out = response.getWriter();
            out.print("<script>alert('�ŶӲ����ڣ���ѡ��������'); window.location='/admin/UpdateRelation.jsp' </script>");
            out.flush();
            out.close();
        }

        // response.sendRedirect(request.getContextPath() + "/AllRelationServlet");
    }
}
