package cn.edu.lingnan.servlet.TeamServlet;

import cn.edu.lingnan.dao.RelationDao;
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
import java.util.Vector;

/**
 * @author 18364
 */
@WebServlet("/ApplyTeamServlet")
public class ApplyTeamServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
         *��ҳ�����Ȼ��ȥ��һ�����棬��ʾȫ�����Ŷӣ�Ȼ�����ӺžͿ��Լ����Ŷ��ˡ�
         * �����ŶӵĲ�����дһ����ϵ����ϵ�������Ŷ��Ѿ�д���ˣ��Ͳ��ᴥ�����������
         * �ȵ���Ӻţ�Ȼ����֤�����ѧ���ǲ��Ǵ�����һ���Ŷӣ�
         * ������ڵĻ�����ô����ת���˳��ŶӵĽ��棬�����ǲ���Ҫ�˳������û�У��Ǿ�ֱ��ִ�в���Ĳ���
         *1.����һ����ʾȫ���Ŷӽ����
         *2.�ж����ѧ����û�вμ��Ŷ�
         *3.������ǹ�ϵ��
         */
        HttpSession s = request.getSession();
        TeamCalss teC = (TeamCalss) s.getAttribute("teamInfo");
        StudentCalss stud = (StudentCalss) s.getAttribute("userInfo");
        RelationDao ral = new RelationDao();
        TeamDao td = new TeamDao();
        RelationClass relaC = new RelationClass();
        String tid = request.getParameter("Teamtid");
        relaC.setTid(tid);
        relaC.setSid(stud.getSid());
        relaC.setPosition("��Ա");
        relaC.setState(1);
        System.out.println("111" + teC.getTid());
        /*
         *���������relation������һ���Ŷӣ����жϣ����Դ�һ��session��ȡ
         * ����session��ֵ��1���Ǹ��Ϳ���ֱ��ȥ��ɾ������  //����Ѿ�������
         * �������session��ֵ�Ƕ����Ǹ��Ϳ�����ʾ�Ѿ��ύ���룬�ȴ�����ˣ��벻Ҫ�ظ��ύ   //���Ҫ��һ��ҳ�档������ʾ
         * �������session��ֵ�ǿգ��Ǿ�Ӧ��ֱ����ʾ����ȷ��Ҫ�ύ���룩     //�Ͳ��뺯��
         */
//
        if (ral.findRelationBySid(stud.getSid()).getState() == 1) {
            System.out.println("����������Ŷӵ�ʱ�����ѧ���μ���һ���Ŷӣ�׼����ת��ɾ������");
            response.sendRedirect(request.getContextPath() + "/TeamModule/QuitTeam.jsp");
        } else {
            if (ral.findRelationBySid(stud.getSid()).getState() == 2) {
                System.out.println("�ظ��ύ");


                response.setCharacterEncoding("GBK");
                PrintWriter out = response.getWriter();
                out.print("<script>alert('���Ѿ��ύ��һ�����룬���ڵȴ�����У�������ѡ����������'); window.location='/TeamModule/MyTeamHomepage.jsp' </script>");
                out.flush();
                out.close();


            } else {
                System.out.println("����������Ŷӵ�ʱ�����ѧ��û�вμ��κ��Ŷӣ�����ֱ�������");
                System.out.println("�������ɹ�" + ral.insertRelation(relaC));
                TeamCalss tc = td.findMyTeamBySid(stud.getSid());
                s.setAttribute("teamInfo", tc);
                RelationDao quitR = new RelationDao();
                RelationClass rc = quitR.findRelationBySid(stud.getSid());
                s.setAttribute("relationInfo", rc);
                response.setCharacterEncoding("GBK");
                PrintWriter out = response.getWriter();
                out.print("<script>alert('�ύ����ɹ������ڵȴ�����Ա��ˣ�������ѡ����������'); window.location='/TeamModule/MyTeamHomepage.jsp' </script>");
                out.flush();
                out.close();
                //response.sendRedirect(request.getContextPath() + "/TeamModule/MyTeamHomepage.jsp");
            }


        }


    }
}
