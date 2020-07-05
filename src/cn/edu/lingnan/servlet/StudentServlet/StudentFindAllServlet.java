package cn.edu.lingnan.servlet.StudentServlet;

import cn.edu.lingnan.dao.StudentDao;
import cn.edu.lingnan.dto.StudentCalss;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;

@WebServlet("/StudentFindAllServlet")
public class StudentFindAllServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1�ӿͻ��˻�ȡ������Ҫ�Ĳ������������е�ѧ����Ϣ���ڸð취�в���Ҫ��ȡ������
        //2������Щ���������÷���
        StudentDao sd = new StudentDao();
        String n = request.getParameter("pageNow");
        //ҳ���ȡ����
        int pageNow = 1;
        if (n != null) {
            pageNow = Integer.parseInt(n);
            System.out.println("��ѯȫ��ѧ����ȡ�ķ�ҳ��" + pageNow);
            //ת������
        }
        Vector<StudentCalss> v = sd.findAllStuFenYe(pageNow);
        HttpSession s = request.getSession();

        s.setAttribute("allStuInfo", v);
        //3���ݴ�����,ȥ��Ӧ��ҳ��
        response.sendRedirect(request.getContextPath() + "/admin/AllStudent.jsp");

    }
}
