<%@ page import="javax.xml.validation.Validator" %>
<%@ page import="java.util.Vector" %>
<%@ page import="cn.edu.lingnan.dto.StudentCalss" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="cn.edu.lingnan.dao.StudentDao" %>
<%@ page import="cn.edu.lingnan.dto.TeamCalss" %>
<%@ page import="cn.edu.lingnan.dao.TeamDao" %><%--
  Created by IntelliJ IDEA.
  User: 18364
  Date: 2020/5/28
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>全部团队</title>
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

    function delall() {
        var alltid = new Array();
        var flag = false;
        var oCheck = document.getElementsByName('check');
        for (var i = 0; i < oCheck.length; i++) {
            if (oCheck[i].checked) {
                alltid.push(oCheck[i].value);
                flag = true;
            }
        }
        if (flag) {
            if (confirm("您确定要删除这些记录？")) {
                location.href = "/DeleteTeamServlet?del=all&alltid=" + alltid;
            }
        } else {
            alert("请至少选择一个来进行批量删除操作！！！")
        }
    }

</script>

<style type="text/css">
    table.altrowstable {
        font-family: verdana, arial, sans-serif;
        table-layout: fixed;
        font-size: 14px;
        color: #333333;
        border-width: 1px;
        border-color: #a9c6c9;
        border-collapse: collapse;
        word-break: break-all;
        word-wrap: break-word;
    }

    table.altrowstable th {
        border-width: 1px;
        padding: 14px;
        border-style: solid;
        border-color: #a9c6c9;
    }

    table.altrowstable td {
        word-break: break-all;
        word-wrap: break-word;
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
        top: 45px;
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
<br>
<h1 align="center">所有团队的信息显示页面</h1>
<p><a href="../StudentModule/StudentHomepage.jsp">回到首页 </a></p>
<table border="1" align="center" class="altrowstable" id="alternatecolor" width="1200px">
    <tr>
        <th><input id="btn1" type="button" value="全选" onclick="allcheck()"/>
            <input id="btn2" type="button" value="重置" onclick="allnotcheck()"/>
        </th>

        <th>团队编号</th>
        <th>团队名称</th>
        <th>带队老师</th>
        <th>团队项目</th>
        <th>团队状态</th>
        <th><input id="btn3" type="button" value="批量删除" onclick="delall()"></th>
    </tr>
    <%
        Vector<TeamCalss> v = (Vector<TeamCalss>) session.getAttribute("allTeaInfo");
        Iterator<TeamCalss> it = v.iterator();
        TeamCalss s;
        while (it.hasNext()) {
            s = it.next();
    %>
    <tr>
        <td><input type="checkbox" name="check" value=<%=s.getTid()%>></td>

        <td><%=s.getTid() %>
        </td>
        <td><%=s.getTname() %>
        </td>
        <td><%=s.getTteacher() %>
        </td>
        <td><%=s.getTitem() %>
        </td>
        <td><%=s.getState() %>
        </td>
        <td><a href="UpdateTeam.jsp?tid=<%=s.getTid()%>"> <img src="/images/edit.png"></a>
            <a href="/DeleteTeamServlet?tid=<%=s.getTid()%>" onclick="return confirm('您要删除这个记录吗？同时队员将解散！！！请慎重！！！')"><img
                    src="/images/delete.jpg"></a>
        </td>
    </tr>
    <%
        }
    %>
</table>
<div class="layui-footer">
    <!-- 底部固定区域 -->
    Copyright © 没有昵称 版权所有 <span style="color:red" id="sitetime"></span> <a
        href="https://github.com/Snailclimb/JavaGuide"> 开源地址</a>
</div>
</body>
</html>
