<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>发过的帖子</title>
    <link href="/StudentMS/css/bootstrap.min.css" rel="stylesheet">
    <script src="/StudentMS/js/jquery-3.2.1.min.js"></script>
    <script src="/StudentMS/js/bootstrap.min.js"></script>
    <style>
        .col-center-block {
            float: none;
            display: block;
            margin-left: auto;
            margin-right: auto;
        }
        body {
            padding-top: 70px;
        }
    </style>
</head>

<body>
<div class="container">

	<%@ include file="/topbar.jsp" %>

    <!--head-->

    <div class="col-md-10">

        <div class="row">
            <nav class="navbar navbar-default">
                <div class="navbar-header">
                    <a class="navbar-brand" href="/StudentMS/servlet/showReplyedController">回复我的</a>
                </div>
                <div class="navbar-header">
                    <a class="navbar-brand" href="/StudentMS/servlet/showCollectionController">收藏的帖子</a>
                </div>
                <div class="navbar-header">
                    <a class="navbar-brand" href="/StudentMS/servlet/showSendedPostController">我发的帖子</a>
                </div>
            </nav>
        </div>

        <div>

            <table class="table">
                <thead>
                <tr>
                    <th>标题</th>
                    <th>内容简介</th>
                    <th>发帖时间</th>
                    <th>总回复量</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${! empty requestScope.sendedPosts }">
                <c:forEach var="post" items="${requestScope.sendedPosts }">
                <tr>
                    <th><a href="/StudentMS/servlet/showDetailController?postId=${post.id }">${post.title }</a></th>
                    <th>${post.content }</th>
                    <th>${post.optime }</th>
                    <th>${post.reply_num }</th>
                </tr>
                </c:forEach>
                </c:if>
                </tbody>
            </table>

        </div>

    </div>


    <!--导航栏-->
    <div class="col-md-2">
        <header class="bs-docs-nav navbar navbar-static-top" id="top"></header>
        <a href="#pageTop" class="back-to-top"> Back to top </a>
    </div>

</div>

</body>
<ml>

