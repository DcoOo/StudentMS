<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>新生论坛</title>
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
    <div class="col-md-10" id="pageTop">
        <!--搜索栏-->
        <div class="row">
            <div class="col-xs-6 col-md-4 col-center-block">
                <form class="form-inline" action="/StudentMS/servlet/searchPostController">
                    <div class="form-group">
                        <input type="text" class="form-control" name="searchText" id="exampleInputName2" placeholder="请输入搜索内容">
                    </div>
                    <button type="submit" class="btn btn-default">搜索</button>
                </form>
            </div>
        </div>

        <!--帖子浏览-->
        	<c:if test="${! empty requestScope.posts || fn:length(requestScope.classAdviserAndAssistantMessages) > 0}">
			  <c:forEach begin="0" end="${fn:length(requestScope.posts) - 1 }" varStatus="idx">
	        	<div class="row">
            <div class="col-md-2">
                ${requestScope.posts[idx.index].reply_num }
            </div>
            <div class="col-md-10">
                <a></a>
                <div class="row">
                    <div class="col-md-9">
                        <a href='/StudentMS/servlet/showDetailController?postId=${requestScope.posts[idx.index].id }'>${requestScope.posts[idx.index].title }</a>
                    </div>
                    <div class="col-md-3">
                        ${requestScope.names[idx.index] }
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-9">
                        ${requestScope.posts[idx.index].content }
                    </div>
                    <div class="col-md-3">
                       ${requestScope.posts[idx.index].optime } 
                    </div>
                </div>
            </div>
        </div>
        		</c:forEach>
        	</c:if>	
         <!--发帖-->
        <div class="row">
            <form id="addPost" action="/StudentMS/servlet/addPostController">
                <div class="form-group form-inline">
                    <label for="exampleInputEmail1">帖子名称</label>
                    <input type="text" name="postTitle" class="form-control" id="exampleInputEmail1" placeholder="帖子">
                </div>
                <div id="editor">
                    <p></p>
                </div>
                <!-- 注意， 只需要引用 JS，无需引用任何 CSS ！！！-->
                <script type="text/javascript" src="/StudentMS/js/wangEditor.min.js"></script>
                <script type="text/javascript">
                    var E = window.wangEditor
                    var editor = new E('#editor')
                    // 或者 var editor = new E( document.getElementById('#editor') )
                    editor.create()
                    content = editor.txt.text();
                    $('#content').val(content);
                </script>
				<input id="content" name="postContent" type="hidden" value="">
                <button type="button" onclick="onClickAddPost()" class="btn btn-default">发帖</button>
            </form>
        </div>
    </div>

    <!--导航栏-->
    <div class="col-md-2">
        <header class="bs-docs-nav navbar navbar-static-top" id="top"></header>
        <a href="#pageTop" class="back-to-top"> Back to top </a>
    </div>

</div>
</body>
<script>
	function onClickAddPost(){
		content = editor.txt.text();
		$('#content').val(content);
		$('#addPost').submit();
		
	}
</script>
</html>