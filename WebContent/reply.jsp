<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>回复我的</title>
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
            <ul class="list-unstyled">
    <c:if test="${fn:length(requestScope.replyList) > 0 }">
		  <c:forEach begin="0" end="${fn:length(requestScope.replyList) - 1 }" varStatus="idx">
                <li>
                    <div class="row">
                        <div class="text-left">
                            ${requestScope.nameList[idx.index] }：${requestScope.replyList[idx.index].content }
                            <div class="pull-right">${requestScope.replyList[idx.index].optime }</div>
                        </div>
                    </div>
                    <div class="row">回复我的主题：
                    <a href="/StudentMS/servlet/showDetailController?postId=${requestScope.postList[idx.index].id }">
                        ${requestScope.postList[idx.index].title }</a>
                    </div>
                    <hr>
                </li>
		  </c:forEach>
	</c:if>
            </ul>
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
