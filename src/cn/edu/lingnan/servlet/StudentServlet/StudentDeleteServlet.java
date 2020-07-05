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
        System.out.println("全部学生页面获取" + del);
        //页面del参数是用来判断是不是批量删除，因为allStudent这个界面存在两个方式删除。批量删除就会把del参数传进来
        if (del == null)
        //删除一条
        {
            String sid = request.getParameter("sid");
            System.out.println(sid);
            System.out.println("所有学生页面删除操作" + sd.deleteStuBySid(sid));
        }
        //删除多条
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
