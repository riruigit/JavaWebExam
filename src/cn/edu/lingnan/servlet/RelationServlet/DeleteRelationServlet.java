package cn.edu.lingnan.servlet.RelationServlet;

import cn.edu.lingnan.dao.RelationDao;
import cn.edu.lingnan.dao.StudentDao;

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
@WebServlet("/DeleteRelationServlet")
public class DeleteRelationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RelationDao delRea = new RelationDao();
        String del = request.getParameter("del");
        System.out.println("ɾ��ȫ��ҳ���ȡ" + del);
        //ҳ��del�����������ж��ǲ�������ɾ������ΪallStudent����������������ʽɾ��������ɾ���ͻ��del����������
        if (del == null)
        //ɾ��һ��
        {
            String sid = request.getParameter("sid");
            System.out.println(sid);
            System.out.println("����ѧ��ҳ��ɾ������" + delRea.deleteRelationBySid(sid));
        }
        //ɾ������
        else {
            String allsid = request.getParameter("allsid");
            System.out.println(allsid);
            String[] temp = allsid.split(",");
            for (String s : temp) {
                delRea.deleteRelationBySid(s);
            }
        }
        response.setCharacterEncoding("GBK");
        PrintWriter out = response.getWriter();
        out.print("<script>alert('ɾ���ɹ�'); window.location='index.html' </script>");
        out.flush();
        out.close();

        response.sendRedirect(request.getContextPath() + "/AllRelationServlet");

    }
}
