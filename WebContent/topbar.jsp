<%@ page language="java" pageEncoding="UTF-8"%>
<html>
	<head>
	</head>
	<body>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="navbar-header">
        <a class="navbar-brand" href="/StudentMS/servlet/showPostController">新生论坛</a>
    </div>
    <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
               aria-haspopup="true" aria-expanded="false">信息<span class="caret"></span></a>
            <ul class="dropdown-menu">
                <li><a href="/StudentMS/servlet/showReplyedController">收到的回复<span class="badge"></span></a></li>
                <li><a href="/StudentMS/servlet/showSendedPostController">发布的帖子<span class="badge"></span></a></li>
                <li><a href="/StudentMS/servlet/showCollectionController">收藏的帖子<span class="badge"></span></a></li>
            </ul>
        <li><a href="/StudentMS/servlet/showmemocontroller">${sessionScope.name }</a></li>
    </ul>
</nav>
	</body>
</html>