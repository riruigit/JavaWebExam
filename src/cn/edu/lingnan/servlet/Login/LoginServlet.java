package cn.edu.lingnan.servlet.Login;

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
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /*
     *(1��ϵͳ��¼�����servlet��ҳ���ȡ�˺ź����룬Ȼ�����studentDao����ķ�����ѯ�ǲ��Ǵ��ڡ�
     * ������ڣ���ȥhomepage�����û�еĻ����Ǿ���һ������ҳ�棬������ʾ��¼��Ϣ���ִ��������µ�¼
     *��2�� ��¼���֮�󣬻�ȡ���ѧ���ĸ�����Ϣ��Ҳ������ͨ�����ѧ���ĸ�����Ϣ��
     * һ������session һ���Ŷӵ�session  ����һ��Ȩ�޵�session����ʦ�Ĺ�������
     *
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("��¼��ȡ���˺ź�����" + username + " " + password);

        StudentDao sdq = new StudentDao();
        TeamDao td = new TeamDao();
        RelationDao re = new RelationDao();

        StudentCalss st = sdq.findStudentByNamePassword(username, password);

        System.out.println("��½���ȡ���ĸ�����Ϣ�����ѧ����" + st.getSid());
        TeamCalss tc = td.findMyTeamBySid(st.getSid());
        RelationClass rc = re.findRelationBySid(st.getSid());


        HttpSession s = request.getSession();

        //�ֱ�ŵ�session���棬�������Ļ�ȡ����
        //����������sessionһ���Ǹ�����Ϣ�ģ�һ�����Ŷ���Ϣ�ģ�һ����������������ġ�
        s.setAttribute("superuser", st.getSuperuser());
        s.setAttribute("userInfo", st);
        s.setAttribute("teamInfo", tc);
        s.setAttribute("relationInfo", rc);

        //����������ѧ�����Ǿ�ֱ�ӽ�ȥ��ҳ�����û�У���ȥ��¼����Ľ���
        if (st.getSid() != null) {
            response.sendRedirect(request.getContextPath() + "/StudentModule/StudentHomepage.jsp");
        } else {
            response.setCharacterEncoding("GBK");
            PrintWriter out = response.getWriter();
            out.print("<script>alert('��ĵ�¼��Ϣ���󣡣�����ѡ����������'); window.location='index.html' </script>");
            out.flush();
            out.close();
        }

    }
}
