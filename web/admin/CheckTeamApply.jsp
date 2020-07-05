<%@ page import="javax.xml.validation.Validator" %>
<%@ page import="java.util.Vector" %>
<%@ page import="cn.edu.lingnan.dto.StudentCalss" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="cn.edu.lingnan.dao.StudentDao" %>
<%@ page import="cn.edu.lingnan.dto.TeamCalss" %>
<%@ page import="cn.edu.lingnan.dao.TeamDao" %>
<%@ page import="cn.edu.lingnan.dto.TeamNumber" %><%--
  Created by IntelliJ IDEA.
  User: 18364
  Date: 2020/5/28
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>全部学生</title>
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


    window.onload = function () {
        altRows('alternatecolor');
    }

    function allcheck() {
        var oCheck = document.getElementsByName('check');
        for (var i = 0; i < oCheck.length; i++) {
            oCheck[i].checked = true;
        }

    }

    function allnotcheck() {
        var oCheck = document.getElementsByName('check');
        for (var i = 0; i < oCheck.length; i++) {
            oCheck[i].checked = false;
        }
    }

    function addall() {
        var allsid = new Array();
        var flag = false;
        var oCheck = document.getElementsByName('check');
        for (var i = 0; i < oCheck.length; i++) {
            if (oCheck[i].checked) {
                allsid.push(oCheck[i].value);
                flag = true;
            }
        }
        if (flag) {
            if (confirm("您确定要增加这些记录？")) {
                location.href = "/CheckYesServlet?delApply=all&allsid=" + allsid;
            }
        } else {
            alert("请至少选择一个来进行批量增加操作！！！")
        }
    }

    function delall() {
        var allsid = new Array();
        var flag = false;
        var oCheck = document.getElementsByName('check');
        for (var i = 0; i < oCheck.length; i++) {
            if (oCheck[i].checked) {
                allsid.push(oCheck[i].value);
                flag = true;
            }
        }
        if (flag) {
            if (confirm("您确定要删除这些记录？")) {
                location.href = "/CheckNotServlet?delApply=all&allsid=" + allsid;
            }
        } else {
            alert("请至少选择一个来进行批量删除操作！！！")
        }
    }

</script>
<script src="https://cdn.jsdelivr.net/npm/jquery/dist/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/font-awesome/css/font-awesome.min.css"/>
<script src="https://cdn.jsdelivr.net/gh/stevenjoezhang/live2d-widget/autoload.js"></script>
<style type="text/css">
    table.altrowstable {
        font-family: verdana, arial, sans-serif;
        table-layout: fixed;
        font-size: 14px;
        color: #333333;
        border-width: 1px;
        border-color: #a9c6c9;
        border-collapse: collapse;
    }

    table.altrowstable th {
        border-width: 1px;
        padding: 14px;
        border-style: solid;
        border-color: #a9c6c9;
    }

    table.altrowstable td {
        text-align: center;
        border-width: 1px;
        padding: 14px;
        border-style: solid;
        border-color: #a9c6c9;
    }

    .oddrowcolor {
        background-color: #d4e3e5;
    }

    .evenrowcolor {
        background-color: #c3dde0;
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
<h1 align="center">团队申请审核界面</h1><br>
<p><a href="../StudentModule/StudentHomepage.jsp">回到首页 </a></p>
<table border="1" cellspacing="0" align="center" class="altrowstable" id="alternatecolor">
    <tr>
        <th><input id="btn1" type="button" value="全选" onclick="allcheck()"/>
            <input id="btn2" type="button" value="重置" onclick="allnotcheck()"/>
        </th>

        <th>学生学号</th>
        <th>学生姓名</th>
        <th>学生学院</th>
        <th>团队编号</th>
        <th>团队名称</th>
        <th>团队队长</th>
        <th>审核状态</th>

        <th><input id="btn3" type="button" value="批量增加" onclick="addall()">
            <input id="btn4" type="button" value="批量删除" onclick="delall()"></th>
    </tr>
    <%
        Vector<TeamNumber> v = (Vector<TeamNumber>) session.getAttribute("allCheckTeamInfo");
        Iterator<TeamNumber> it = v.iterator();
        TeamNumber s;
        while (it.hasNext()) {
            s = it.next();
    %>
    <tr>
        <td><input type="checkbox" name="check" value=<%=s.getStudentSid()%>></td>

        <td><%=s.getStudentSid()%>
        </td>
        <td><%=s.getStudentSname() %>
        </td>
        <td><%=s.getStudentGender() %>
        </td>
        <td><%=s.getTeamTid()%>
        </td>
        <td><%=s.getTeamTtname() %>
        </td>
        <td><%=s.getRelationPosition()%>
        </td>
        <td><span style="color: red"> <% if (s.getRelationState() == 2) out.print("等待审核");%></span>
        </td>
        <td><a href="/CheckYesServlet?applysid=<%=s.getStudentSid()%>"> <img src="/images/new.png"></a>
            <a href="/CheckNotServlet?applysid=<%=s.getStudentSid()%>" onclick="return confirm('您要删除这个记录吗？')"><img
                    src="/images/otherdelete.png"></a>
        </td>
    </tr>
    <%
        }
    %>
</table>
<div class="layui-footer">
    <!-- 底部固定区域 -->
    Copyright © 没有昵称 版权所有 <span style="color:red" id="sitetime"></span> <a
        href="https://github.com/riruigit/JavaWebExam"> 开源地址</a>
</div>
</body>
</html>
