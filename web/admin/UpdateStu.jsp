<%@ page import="javax.xml.validation.Validator" %>
<%@ page import="java.util.Vector" %>
<%@ page import="cn.edu.lingnan.dto.StudentCalss" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="cn.edu.lingnan.dao.StudentDao" %><%--
  Created by IntelliJ IDEA.
  User: 18364
  Date: 2020/5/28
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改学生页面</title>
    <script type="text/javascript" src="../runtime.js"></script>
</head>
<script type="text/javascript">
    function altRows(id) {
        if (document.getElementsByTagName) {

            var table = document.getElementById(id);
            var rows = table.getElementsByTagName("tr");

            for (i = 0; i < rows.length; i++) {
                if (i % 2 == 0) {
                    rows[i].className = "evenrowcolor";
                } else {
                    rows[i].className = "oddrowcolor";
                }
            }
        }
    }

    window.onload = function () {
        altRows('alternatecolor');
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/jquery/dist/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/font-awesome/css/font-awesome.min.css"/>
<script src="https://cdn.jsdelivr.net/gh/stevenjoezhang/live2d-widget/autoload.js"></script>
<style type="text/css">
    table.altrowstable {
        font-family: verdana, arial, sans-serif;
        font-size: 20px;
        color: #333333;
        border-width: 1px;
        border-color: #a9c6c9;
        border-collapse: collapse;
        text-align: center;
    }

    table.altrowstable th {
        text-align: center;
        width: 100px;
        height: 20px;
        border-width: 1px;
        padding: 15px;
        border-style: solid;
        border-color: #a9c6c9;
    }

    table.altrowstable td {
        width: 200px;
        text-align: center;
        border-width: 1px;
        padding: 15px;
        border-style: solid;
        border-color: #a9c6c9;


    }

    .oddrowcolor {
        background-color: #d4e3e5;
    }

    .evenrowcolor {
        background-color: #c3dde0;
    }

    input {
        width: 100%;
        height: 100%;
    }

    select {
        width: 100%;
        height: 100%;
    }

    p {
        position: fixed;
        top: 60px;
        right: 350px;
    }

    .layui-footer {
        text-align: center;
        position: fixed;
        left: 0;
        right: 0;
        bottom: 0;
        height: 44px;
        line-height: 44px;
        padding: 0 15px;
        background-color: #eee
    }


</style>
<body style="text-align: center">
<br><br>
<h1 align="center">学生信息修改显示页面</h1><br>
<p><a href="../StudentModule/StudentHomepage.jsp">回到首页 </a></p>
<form action="/StudentUpdateAllServlet">
    <table border="1" align="center" class="altrowstable" id="alternatecolor">
        <%
            Vector<StudentCalss> v = (Vector<StudentCalss>) session.getAttribute("allStuInfo");
            String sid = request.getParameter("sid");
            Iterator<StudentCalss> it = v.iterator();
            StudentCalss s = null;
            while (it.hasNext()) {
                s = it.next();
                if (sid.equals(s.getSid())) {
        %>

        <tr>
            <th>学生学号</th>
            <td><input type="hidden" name="sid" value=<%=s.getSid() %>><%=s.getSid()%>
            </td>
        </tr>

        <tr>
            <th>学生姓名</th>
            <td><input type="hidden" name="sname" value=<%=s.getSname() %>><%=s.getSname()%>
            </td>
        </tr>
        <tr>
            <th>学生年龄</th>
            <td><input autocomplete="off" placeholder="请输入年龄" required="required" type="text" name="age"
                       value=<%=s.getAge()%>>
            </td>
        </tr>
        <tr>
            <th>学生性别</th>
            <td>
                <select name="depart" required="required">
                    <option value="">请选择一个性别</option>
                    <option value="男">男</option>
                    <option value="女">女</option>

                </select>
            </td>
        </tr>

        <tr>
            <th>学生院系</th>
            <td>
                <select name="gender" required="required">
                    <option value="">请选择一个学院</option>
                    <option value="信息院">信息院</option>
                    <option value="文传院">文传院</option>
                    <option value="法政院">法政院</option>
                    <option value="教科院">教科院</option>
                    <option value="机电院">机电院</option>
                    <option value="生科院">生科院</option>
                    <option value="商学院">商学院</option>
                </select>
            </td>
        </tr>
        <tr>
            <th>学生权限</th>
            <td><label><input style="width:20px;height:20px" name="superuser" type="radio" value="1" checked/>
                管理员</label>
                <label><input style="width:20px;height:20px" name="superuser" type="radio" value="2"/> 学生</label>
            </td>
        </tr>
        <tr>
            <th>学生密码</th>
            <td><input autocomplete="off" placeholder="请输入密码" required="required" type="password" name="password"
                       value=<%=s.getPassword()%>>
            </td>
        </tr>
        <tr>
            <th>修改操作</th>
            <td><input type="submit" value="确定修改" onclick="return confirm('您要修改这个记录吗？')">
        </tr>

        <%
                }
            }
        %>
    </table>
</form>
</body>
<div class="layui-footer">
    <!-- 底部固定区域 -->
    Copyright © 没有昵称 版权所有 <span style="color:red" id="sitetime"></span> <a
        href="https://github.com/riruigit/JavaWebExam"> 开源地址</a>
</div>
</html>
