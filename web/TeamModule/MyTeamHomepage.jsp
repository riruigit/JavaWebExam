<%@ page import="cn.edu.lingnan.dto.StudentCalss" %>
<%@ page import="java.util.Vector" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="cn.edu.lingnan.dto.TeamCalss" %>
<%@ page import="cn.edu.lingnan.dto.RelationClass" %><%--
  Created by IntelliJ IDEA.
  User: 18364
  Date: 2020/5/9
  Time: 22:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>岭师团队竞赛管理系统</title>
    <script type="text/javascript" src="../runtime.js"></script>
    <link rel="stylesheet" href="../css/layui.css">
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

    </style>


    <style>
        .o_div {
            background: url("/images/01.jpg") no-repeat;
        }

        .o_span {
            display: block;
            text-align: center;
            font-size: 20px;
            letter-spacing: 8px
        }
    </style>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">岭师团队竞赛管理系统</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->

        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    ${sessionScope.userInfo.sname}
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="/StudentModule/StudentUpdate.jsp">修改资料</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="/LoginoutServlet">退出系统</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">参赛学生管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/StudentModule/StudentHomepage.jsp">我的信息</a></dd>
                        <dd><a href="/StudentFindAllServlet">全部学生</a></dd>
                    </dl>
                </li>

                <li class="layui-nav-item layui-nav-itemed">
                    <a href="javascript:;">参赛团队管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/TeamModule/MyTeamHomepage.jsp">我的团队</a></dd>
                        <dd><a href="/AllTeamServlet">全部团队</a></dd>
                        <dd><a href="/MyTeamNumberServlet">团队成员</a></dd>
                        <dd><a href="/AllTeamApplyServlet">申请加入</a></dd>
                        <dd><a href="/QuitMyTeamServlet">退出团队</a></dd>
                    </dl>
                </li>

                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">参赛选手管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/AllRelationServlet">查看全部情况</a></dd>
                        <dd><a href="/CheckTeamApplyServlet">团队申请审核</a></dd>
                        <dd><a href="/AfterUpdateTeamServlet">修改团队资料</a></dd>
                        <dd><a href="/admin/AddTeam.jsp">增加一个团队</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="page-content">
        <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">


            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show o_div">
                    <span class="o_span">${sessionScope.userInfo.sname}，欢迎来到管理系统！</span>
                    <div class="layui-col-md6" style="padding: 30px;left:400px;top:100px;background-color: #F2F2F2;">
                        <div class="layui-card">
                            <div class="layui-card-header">您的团队详情如下
                            </div>
                            <div class="layui-card-body">
                                <%--<div class="layui-form-label" style="text-align: left">你好</div>--%>
                                您的学号： ${sessionScope.userInfo.sid}
                            </div>
                            <div class="layui-card-body">
                                您的姓名： ${sessionScope.userInfo.sname}
                            </div>

                            <%RelationClass rec = (RelationClass) session.getAttribute("relationInfo");%>

                            <div class="layui-card-body">
                                团队审核： <span style="color:red"> <%
                                if (rec.getState() == 2) {
                                    out.print("申请审核中");
                                } else {
                                    if (rec.getState() == 1) out.print("审核通过");
                                    else out.print("没记录提交或者不通过");
                                }
                            %></span>
                            </div>

                            <div class="layui-card-body">
                                您的团队号： ${sessionScope.teamInfo.tid}
                            </div>
                            <div class="layui-card-body">
                                团队名称： ${sessionScope.teamInfo.tname}
                            </div>
                            <div class="layui-card-body">
                                带队老师： ${sessionScope.teamInfo.tteacher}
                            </div>
                            <div class="layui-card-body">
                                项目方向： ${sessionScope.teamInfo.titem}
                            </div>

                        </div>
                    </div>
                </div>
            </div>


        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        Copyright © 没有昵称 版权所有 <span style="color:red" id="sitetime"></span> <a
            href="https://github.com/riruigit/JavaWebExam">开源地址</a>
    </div>
</div>
<script src="../layui.js"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function () {
        var element = layui.element;

    });
</script>

</body>

</html>