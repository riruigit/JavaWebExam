package cn.edu.lingnan.servlet.RelationServlet;

import cn.edu.lingnan.dao.RelationDao;
import cn.edu.lingnan.dto.RelationClass;
import cn.edu.lingnan.dto.TeamNumber;

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
@WebServlet("/CheckTeamApplyServlet")
public class CheckTeamApplyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
         *操作步骤，应该是先点击一个servlet调用方法，把关系表的state状态是2的全部查找出来。
         * 找出来了之后就可以去jsp页面了（这个jsp页面应该包括学生学号以及姓名，申请团队。）
         * 然后页面就有两个选项，一个是审核通过，一个不通过。
         * 然后这里是需要jsp页面传递参数的。传进另外一个servlet，然后就可以更新一波session
         * 然后就继续回到第一个servlet
         */
        RelationDao checkR = new RelationDao();
        Vector<TeamNumber> checkV = checkR.findAllCheck();
        HttpSession s = request.getSession();
        s.setAttribute("allCheckTeamInfo", checkV);
        response.sendRedirect(request.getContextPath() + "/admin/CheckTeamApply.jsp");


    }
}
