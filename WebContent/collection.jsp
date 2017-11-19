<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>我的收藏</title>
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
                <th>作者</th>
                <th>帖子简介</th>
                <th>发帖时间</th>
            </tr>
            </thead>
            <tbody>
        		<c:if test="${fn:length(requestScope.classAdviserMessages) > 0 }">
		        	<c:forEach begin="0" end="${fn:length(requestScope.classAdviserMessages) - 1 }" varStatus="idx">
		        		<li><a onclick="showNotice('${requestScope.classAdviserMessages[idx.index].id }', '${requestScope.classAdviserMessages[idx.index].title}', '${requestScope.classAdviserMessages[idx.index].content }', '${requestScope.classAdviserMessages[idx.index].optime }', '${requestScope.classAdviserMessages[idx.index].teacher }', '${requestScope.modelDocument[idx.index] }')">${requestScope.classAdviserMessages[idx.index].title }</a>
		        	</c:forEach>
        	 	</c:if>
            <c:if test="${! empty requestScope.collectedPosts}">
			<c:forEach begin="0" items="${requestScope.collectedPosts }" end="${fn:length(requestScope.collectedPosts) - 1 }" varStatus="idx">
	            <tr>
                	<td><a href="/StudentMS/servlet/showDetailController?postId=${requestScope.collectedPosts[idx.index].id }">${requestScope.collectedPosts[idx.index].title }</a></td>
                	<td>${requestScope.authorList[idx.index] }</td>
                	<td>${requestScope.collectedPosts[idx.index].content }</td>
                	<td>${requestScope.collectedPosts[idx.index].optime }</td>
            	</tr>
			</c:forEach>
            </c:if>
            </tbody>
        </table>

    </div>

    </div>

    <!--收藏信息-->





    <!--导航栏-->
    <div class="col-md-2">
        <header class="bs-docs-nav navbar navbar-static-top" id="top"></header>
        <a href="#pageTop" class="back-to-top"> Back to top </a>
    </div>

</div>

</body>
</html>