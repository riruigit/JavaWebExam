package cn.edu.lingnan.servlet.RelationServlet;

import cn.edu.lingnan.dao.RelationDao;
import cn.edu.lingnan.dto.RelationClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;

/**
 * @author 18364
 */
@WebServlet("/AllRelationServlet")
public class AllRelationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RelationDao rd = new RelationDao();
        HttpSession s = request.getSession();
        //3根据处理结果,去对应的页面
        //2处理这些参数，调用方法
        String n = request.getParameter("relationPageNow");
        //页面获取参数
        int relationPageNow = 1;
        if (n != null) {
            relationPageNow = Integer.parseInt(n);
            //转成整数
        }
        Vector<RelationClass> RV = rd.findAllRelationFenYe(relationPageNow);
        s.setAttribute("allReaInfo", RV);
        //3根据处理结果,去对应的页面
        response.sendRedirect(request.getContextPath() + "/admin/AllRelation.jsp");


    }
}
