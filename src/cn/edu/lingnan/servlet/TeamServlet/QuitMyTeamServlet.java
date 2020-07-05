package cn.edu.lingnan.servlet.TeamServlet;

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

@WebServlet("/QuitMyTeamServlet")
public class QuitMyTeamServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession();


        TeamCalss quitTc = (TeamCalss) s.getAttribute("teamInfo");
        if (quitTc.getTid() != null) {
            response.sendRedirect(request.getContextPath() + "/TeamModule/QuitTeam.jsp");
        } else {
            System.out.println("���˳��Ŷӵ�ʱ���ж����ѧ����û�м�¼��û�вμ��Ŷ�");

            response.setCharacterEncoding("GBK");
            PrintWriter out = response.getWriter();
            out.print("<script>alert('����û�вμ��Ŷӣ�������ѡ����������'); window.location='/TeamModule/MyTeamHomepage.jsp' </script>");
            out.flush();
            out.close();
        }
    }
}
