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
        System.out.println("删除全部页面获取" + del);
        //页面del参数是用来判断是不是批量删除，因为allStudent这个界面存在两个方式删除。批量删除就会把del参数传进来
        if (del == null)
        //删除一条
        {
            String sid = request.getParameter("sid");
            System.out.println(sid);
            System.out.println("所有学生页面删除操作" + delRea.deleteRelationBySid(sid));
        }
        //删除多条
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
        out.print("<script>alert('删除成功'); window.location='index.html' </script>");
        out.flush();
        out.close();

        response.sendRedirect(request.getContextPath() + "/AllRelationServlet");

    }
}
