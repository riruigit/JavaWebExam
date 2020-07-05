package cn.edu.lingnan.servlet.TeamServlet;

import cn.edu.lingnan.dao.TeamDao;
import cn.edu.lingnan.dto.TeamCalss;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

/**
 * @author 18364
 */
@WebServlet("/AddTeamServlet")
public class AddTeamServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String addTid = request.getParameter("tid");
        String addName = request.getParameter("tname");
        String addTeacher = request.getParameter("tteacher");
        String addItem = request.getParameter("titem");
        System.out.println("������һ���Ŷӵ�ʱ���ȡ�Ĳ���" + addTid + "#" + addName + "#" + addTeacher + "#" + addItem);
        TeamDao addTeam = new TeamDao();
        TeamCalss addTeamClass = new TeamCalss();
        addTeamClass.setTid(addTid);
        addTeamClass.setTname(addName);
        addTeamClass.setTteacher(addTeacher);
        addTeamClass.setTitem(addItem);
        addTeamClass.setState(1);


        if (addTeam.findTeaByTid(addTid)) {
            System.out.println("�Ѿ���������Ŷ�");
            response.setCharacterEncoding("GBK");
            PrintWriter out = response.getWriter();
            out.print("<script>alert('����Ŷ��Ѿ���ע�ᣡ������ѡ����������'); window.location='/admin/AddTeam.jsp' </script>");
            out.flush();
            out.close();
        } else {

            System.out.println(addTeam.insertTeam(addTeamClass));
            Vector<TeamCalss> v = addTeam.findAllTeam();
            HttpSession s = request.getSession();
            s.setAttribute("allTeaInfo", v);
            response.setCharacterEncoding("GBK");
            PrintWriter out = response.getWriter();
            out.print("<script>alert('�Ŷ�ע��ɹ�'); window.location='/admin/AllTeam.jsp' </script>");
            out.flush();
            out.close();
            // response.sendRedirect(request.getContextPath() + "/admin/AllTeam.jsp");
        }


    }
}
