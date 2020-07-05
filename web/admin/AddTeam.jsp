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
    <title>增加团队</title>
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
        width: 100px;
        height: 20px;
        border-width: 1px;
        padding: 15px;
        border-style: solid;
        border-color: #a9c6c9;
        text-align: center;
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
<h1 align="center">增加一个团队显示页面</h1><br>
<p><a href="../StudentModule/StudentHomepage.jsp">回到首页 </a></p>
<form action="/AddTeamServlet">
    <table border="1" align="center" class="altrowstable" id="alternatecolor">

        <tr>
            <th>团队编号</th>
            <td><input autocomplete="off" required="required" type="text" name="tid" value="">
            </td>
        </tr>

        <tr>
            <th>团队名称</th>
            <td><input autocomplete="off" required="required" type="text" name="tname" value="">
            </td>
        </tr>
        <tr>
            <th>带队老师</th>
            <td><input autocomplete="off" required="required" type="text" name="tteacher" value="">
            </td>
        </tr>
        <tr>
            <th>团队项目</th>
            <td><textarea required="required" rows="4" cols="50" name="titem"></textarea>
            </td>
        </tr>

        <tr>
            <th>注册操作</th>
            <td><input type="submit" value="提交">
        </tr>
    </table>
</form>
<div class="layui-footer">
    <!-- 底部固定区域 -->
    Copyright © 没有昵称 版权所有 <span style="color:red" id="sitetime"></span> <a
        href="https://github.com/riruigit/JavaWebExam"> 开源地址</a>
</div>
</body>
</html>
