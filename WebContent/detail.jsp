<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <meta charset="UTF-8">
    <title></title>
    <script>
        function show(time){
            var now = new Date();
            var year = now.getFullYear();
            var month = now.getMonth()+1;
            var date = now.getDate();
            var hh = now.getHours();
            var mm = now.getMinutes();
            for(var i = 0;i < 100;i ++){
                document.getElementsByClassName("day")[i].innerHTML = time;
            }
        }
        $(function () {
            $("#refresh").click(function () {
                history.go(0);
            })
        })
    </script>
</head>
<body onload="show('${requestScope.post.optime}');">

	<%@ include file="/topbar.jsp" %>
<div id="main" style="position: relative;top: 50px">
    <a href="#luntan"><button type="button" class="btn btn-default" style="position: fixed;bottom: 100px;right: 45px;height: 50px;width: 50px">
        <span class="glyphicon glyphicon-arrow-up"></span>
    </button></a>
    <button type="button" class="btn btn-default" id="refresh" style="position: fixed;bottom: 160px;right: 45px;height: 50px;width: 50px">
        <span class="glyphicon glyphicon-refresh"></span>
    </button>
    <a name="luntan" style="margin: auto"></a>
    <div class="kuai" style=" margin:10px auto;width: 1000px; height: 200px;border: 1px #CCCCCC solid;border-radius:5px;overflow: hidden;">
    	<!-- 删除帖子按钮，根据身份才能决定是否显示该按钮 -->
    	<c:if test="${sessionScope.part == 'teacher' || sessionScope.userId == requestScope.post.user_id }">
	<a href="/StudentMS/servlet/deletePostController?postId=${requestScope.post.id }">×</a>
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
    	</c:if>
        <div class="name" style="float:left;height: 200px;width: 100px;background-color: #c1e2b3;">${requestScope.postOwnerName }</div>
        <div  class="content" style="width: 980px;">
            <div>
            	<h3 style="text-align:center;color:#1a66b3">${requestScope.post.title }</h3>
            	<span>${requestScope.post.content }</span>
            </div>
            <div class="time" style="float:right;">
                <span class="lou" style="padding: 10px"></span><span class="day"></span><br>
                <button type="button" class="btn btn-default" onclick="onCollect('${post.id }')" style='margin: 5px'>
             <span class="glyphicon glyphicon-star"></span>
           </button>
           <button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#myModal" style="position:relative;left:50px">回复</button>
                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                <h4 class="modal-title" id="myModalLabel">回复：</h4>
                            </div>
                            <form action="/StudentMS/servlet/addReplyController" method="POST">
                            <textarea class="modal-body" name="replyContent" style="width:598px;height: 100px"></textarea>
                            <div class="modal-footer">
                            	<input type="hidden" name="postId" value="${requestScope.post.id }">
                                <button type="button" class="btn btn-default"  data-dismiss="modal">关闭</button>
                                <input type="submit" class="btn btn-primary" id="tijiao" ></input>
                            </div>
                            </form>
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal-dialog -->
                </div>
                <!-- /.modal -->
            </div>
        </div>
    </div>
    <c:if test="${! empty requestScope.replys }">
		  <c:forEach begin="0" end="${fn:length(requestScope.replys) - 1 }" varStatus="idx">
    <div class='huifu' style=" margin: auto;width: 1000px; height: 200px;border: 1px #CCCCCC solid;border-radius:5px;overflow: hidden;"><button type="button" class='close' data-dismiss="modal" aria-hidden="true"></button>
        <div class="name" style="float:left;height: 200px;width: 100px;background-color: #c1e2b3;">${requestScope.names[idx.index]}</div>
        <div  class="content" style="width: 980px;">
            <div><span>${requestScope.replys[idx.index].content }</span></div><div style='position: relative;top: 150px;left:600px;'><button type="button" onclick="onStar('${requestScope.replys[idx.index].id }')" class="btn btn-default" style='margin: 5px'>
            <span class="glyphicon glyphicon-thumbs-up" id="starNum${requestScope.replys[idx.index].id }">${requestScope.replys[idx.index].star_num }</span>
            </button><button type="button" class="btn btn-default" onclick="onOppose('${requestScope.replys[idx.index].id }')" style='margin: 5px'>
            <span class="glyphicon glyphicon-thumbs-down" id="opposeNum${requestScope.replys[idx.index].id }">${requestScope.replys[idx.index].oppose_num }</span>
            </button><span>${requestScope.replys[idx.index].optime }</span></div></div>
       </div>
   	</c:forEach> 
   	</c:if>
</div>
</body>
<script>
	function onStar(replyId){
		$.ajax({
			url:"/StudentMS/servlet/starReplyController?replyId="+replyId,
			async:true,
			type:'GET',
			success:function(result){
				if(result.substring(0, 1) == 0){
					var num = result.split('#')[1];	
					$('#starNum'+replyId).text(num);

				}
				if(result.substring(0, 1) == 1){
					alert("您已赞同过该回复");	
				}
				if(result.substring(0, 1) == 2){
					alert("您已经反对过该回复，无法赞同");
				}
			}
		});
	}
	
	function onOppose(replyId){
		$.ajax({
			url:"/StudentMS/servlet/opposeReplyController?replyId="+replyId,
			async:true,
			type:'GET',
			success:function(result){
				if(result.substring(0, 1) == 0){
					var num = result.split('#')[1];	
					$('#opposeNum'+replyId).text(num);

				}
				if(result.substring(0, 1) == 1){
					alert("您已经赞同过该回复，无法进行反对");	
				}
				if(result.substring(0, 1) == 2){
					alert("您已反对过该回复");
				}
			}
		});
		
	}
	
	function onCollect(postId){
		$.ajax({
			url:'/StudentMS/servlet/collectController?postId='+postId,
			async:true,
			type:'GET',
			success:function(result){
				if(result == 0){
					alert("您已经收藏过该帖子");
				}
				if(result == 1){
					alert("收藏成功");
					
				}
			}
		});
	}
	
</script>
</html>