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
        //1从客户端获取我们想要的参数（查找所有的学生信息，在该办法中不需要获取参数）
        //2处理这些参数，调用方法
        StudentDao sd = new StudentDao();
        String n = request.getParameter("pageNow");
        //页面获取参数
        int pageNow = 1;
        if (n != null) {
            pageNow = Integer.parseInt(n);
            System.out.println("查询全部学生获取的分页数" + pageNow);
            //转成整数
        }
        Vector<StudentCalss> v = sd.findAllStuFenYe(pageNow);
        HttpSession s = request.getSession();

        s.setAttribute("allStuInfo", v);
        //3根据处理结果,去对应的页面
        response.sendRedirect(request.getContextPath() + "/admin/AllStudent.jsp");

    }
}
