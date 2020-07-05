package cn.edu.lingnan.servlet.StudentServlet;

import cn.edu.lingnan.dao.StudentDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 18364
 */
@WebServlet("/StudentDeleteServlet")
public class StudentDeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StudentDao sd = new StudentDao();
        String del = request.getParameter("del");
        System.out.println("ȫ��ѧ��ҳ���ȡ" + del);
        //ҳ��del�����������ж��ǲ�������ɾ������ΪallStudent����������������ʽɾ��������ɾ���ͻ��del����������
        if (del == null)
        //ɾ��һ��
        {
            String sid = request.getParameter("sid");
            System.out.println(sid);
            System.out.println("����ѧ��ҳ��ɾ������" + sd.deleteStuBySid(sid));
        }
        //ɾ������
        else {
            String allsid = request.getParameter("allsid");
            System.out.println(allsid);
            String[] temp = allsid.split(",");
            for (String s : temp) {
                sd.deleteStuBySid(s);
            }
        }


        response.sendRedirect(request.getContextPath() + "/StudentFindAllServlet");

    }
}
