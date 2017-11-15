<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>学生个人中心</title>
    <link href="/StudentMS/css/bootstrap.min.css" rel="stylesheet">
    <script src="/StudentMS/js/jquery-3.2.1.min.js"></script>
    <script src="/StudentMS/js/bootstrap.min.js"></script>

    <style>
        .col-md-2,.col-md-7,.row{
            border: 1px #bcbcbc solid;
        }

        .navbar{
            min-height: 30px;
        }
        .navbar-nav>li{
            padding: 5px 5px 5px;
        }

    </style>
</head>

<body>
<div class="container">
<div>
        <img src="/StudentMS/img/logo.jpg">
</div>

    <nav style="background:#1d85d7" class="navbar navbar-default">
    <ul class="nav navbar-nav">
        <li class="nav-header">
            主页
        </li>
    </ul>
</nav>

    <div class="row-fluid">
    <div class="col-md-2" style="margin: 0 3px">

        <div class="row">
        <ul class="nav navbar-nav">
            <li style="background: url(/StudentMS/img/top_comp02.gif) no-repeat; color: #fff; width: 97px;" class="nav-header">
                个人中心
            </li>
        </ul>
        </div>
        <ul class="nav nav-list">
            <li  class="nav-header">
                个人信息
            </li>
            <li>
                <a href="#" onclick="alter()">个人信息修改</a>
            </li>
            <li>
                <a href="#" onclick="passwordChange()">密码修改</a>
            </li>
            <li class="nav-header">
                信息查询
            </li>

            <li>
                <a href="#" onclick="examResult()">分班考试结果</a>
            </li>

            <li>
                <a href="#">学校规章制度</a>
            </li>
            <li>
                <a href="#">学校部门介绍</a>
            </li>
            <hr/>
            <li>
                <a href="#">新生论坛</a>
            </li>
        </ul>
    </div>


    <div class="col-md-7" style="margin: 0 3px" id="">

        <div class="row">
        <ul class="nav navbar-nav">
            <li style="background: url(/StudentMS/img/top_comp02.gif) no-repeat; color: #fff; width: 97px;" class="nav-header" id="centerTitle">
                近期通知
            </li>
        </ul>
        </div>

        <div id="centerContent">
        <ul class="">
        	<c:if test="${fn:length(requestScope.classAdviserMessages) > 0 }">
        		<c:forEach begin="0" end="${fn:length(requestScope.classAdviserMessages) - 1 }" varStatus="idx">
        			<li><a onclick="showNotice('${requestScope.classAdviserMessages[idx.index].id }', '${requestScope.classAdviserMessages[idx.index].title}', '${requestScope.classAdviserMessages[idx.index].content }', '${requestScope.classAdviserMessages[idx.index].optime }', '${requestScope.classAdviserMessages[idx.index].teacher }', '${requestScope.modelDocument[idx.index] }')">${requestScope.classAdviserMessages[idx.index].title }</a>
        		</c:forEach>
        	</c:if>
        	<!-- 
        	<c:forEach var="message" items='${requestScope.classAdviserMessages }'>
        		<li><a onclick="showNotice('${message.id }', '${message.title}', '${message.content }', '${message.optime }', '${message.teacher }')">${message.title }</a>
        	</c:forEach>
        	<c:forEach var="modelDocListJson" items="${requestScope.modelDocument }">
        		<span>${modelDocListJson }</span>
        	</c:forEach>
        	 -->
        </ul>
            
        </div>

    </div>



    <div class="col-md-2" style="margin: 0 3px">
        <div class="row">
        <ul class="nav navbar-nav">
            <li style="background: url(/StudentMS/img/top_comp02.gif) no-repeat; color: #fff; width: 97px;" class="nav-header">
                待办任务
            </li>
        </ul>
        </div>

        <div>
            <div class="row">
                <div class="col-md-12">必做任务</div>
            </div>
            <c:forEach var="memo_item" items="${requestScope.mustMemoList}" step="1">
	            <div class="row">
                	<div class="col-md-10">${memo_item.title}</div>
                	<div class="col-md-1"><a href="deletememocontroller?id=${memo_item.id}&type=0"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span></a></div>
            	</div>
            </c:forEach>
        </div>

        <hr>
        <div>
            <div class="row">
                <div class="col-md-9">选做任务</div>
            </div>
            <c:forEach var="memo_item" items="${requestScope.optionMemoList}" step="1">
	            <div class="row">
                	<div class="col-md-10">${memo_item.title}</div>
                	<div class="col-md-1"><a href="deletememocontroller?id=${memo_item.id }&type=0"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span></a></div>
            	</div>
            </c:forEach>
        </div>
        <hr>

        <div>
            <div class="row">
                <div class="col-md-9">自定义任务</div>

                <div style="border: none" class="col-md-3"><a onclick="customize()">编辑</a></div>
            </div>
            <c:forEach var="memo_item" items="${requestScope.customMemoList}" step="1">
	            <div class="row">
                	<div class="col-md-10">${memo_item.title}</div>
                	<div class="col-md-1"><a href = "deletememocontroller?id=${memo_item.id }&type=1"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span></a></div>
            	</div>
            </c:forEach>
            <form action="/StudentMS/servlet/addmemocontroller">
            	<div id="input" style="display: none">
                    <a href="#" id="addTask">添加新任务</a>
                    <div id="addTaskDiv" style="display: none">
                        <a href="#" id="AddMoreFileBox">再加一条</a>
                        <div id="InputsWrapper">
                            <div>
                            <input type="text" name="newTask" id="field_1" value=""/><a href="#" class="removeclass">×</a>
                            </div>
                        </div>
                    </div>
                </div>
                <button type="submit" class="btn btn-default" id="button" style="display: none">提交</button>
            	
            </form>
        </div>
    </div>
</div>
</div>
</body>
<script  type="text/javascript">
	function customize(){
        $('.id').show();
        $('.myTask').show();
        $('#button').show();
        $('.sign').hide();
        $('.task').hide();
        $('#input').show();
        $('#addTask').click(function (e) {
            $('#addTaskDiv').show();
        });
        var MaxInputs       = 8; //maximum input boxes allowed
        var InputsWrapper   = $("#InputsWrapper"); //Input boxes wrapper ID
        var AddButton       = $("#AddMoreFileBox"); //Add button ID
        var x = InputsWrapper.length; //initlal text box count
        var FieldCount=1; //to keep track of text box added
        $(AddButton).click(function (e)  //on add input button click
        {
            if(x <= MaxInputs) //max input box allowed
            {
                FieldCount++; //text box added increment
                //add input box
                $(InputsWrapper).append('<div>' +
                    '<input type="text" name="newTask" id="field_'+ FieldCount +'"/><a href="#" class="removeclass">×</a>' +
                    '</div>');
                x++; //text box increment
            }
            return false;
        });
        $("body").on("click",".removeclass", function(e){ //user click on remove text
            if( x > 1 ) {
                $(this).parent('div').remove(); //remove text box
                x--; //decrement textbox
            }
            return false;
        })
	}

    function alter() {
        $('#centerTitle').text("个人信息");
        $('#centerContent>*').hide();

        $.getJSON("11.php",function (data) {
            $('#centerContent').append('<div class="table-responsive">\n' +
                '        <table class="table table-bordered table-condensed">\n' +
                '            <tr>\n' +
                '                <th>邮箱</th>\n' +
                '                <th>年龄</th>\n' +
                '                <th>手机号</th>\n' +
                '            </tr>\n' +
                '            <tr>\n' +
                '                <form action="" method="post">' +
                '                <td><input type="text" name="email" value="'+ data.email + '"></td>\n' +
                '                <td><input type="text" name="age" value="'+ data.age + '"></td>\n' +
                '                <td><input type="text" name="phone" value="'+ data.phone + '"></td>\n' +
                '            </tr>\n' +
                '            <tr>\n' +
                '                <th>民族</th>\n' +
                '                <th>性别</th>\n' +
                '                <th>团员</th>\n' +
                '            </tr>\n' +
                '            <tr>\n' +
                '                <td><input type="text" name="nation" value="'+ data.nation + '"></td>\n' +
                '                <td><input type="text" name="sex" value="'+ data.sex + '"></td>\n' +
                '                <td><input type="text" name="idCYL" value="'+ data.isCYL + '"></td>\n' +
                '            </tr>\n' +
                '            <tr>\n' +
                '                <th>微信</th>\n' +
                '                <th>QQ</th>\n' +
                '                <th>家庭住址</th>\n' +
                '            </tr>\n' +
                '            <tr>\n' +
                '                <td><input type="text" name="weChart" value="'+ data.weChart + '"></td>\n' +
                '                <td><input type="text" name="QQ" value="'+ data.QQ + '"></td>\n' +
                '                <td><input type="text" name="address" value="'+ data.address + '"></td>\n' +
                '            </tr>\n' +
                '        </table>\n' +
                '<button type="submit">提交</button>' +
                '        </div>'+
                '       </form>'
            );
        });
    }

    function passwordChange() {
        $('#centerTitle').text("密码修改");
        $('#centerContent>*').hide();
        $('#centerContent').append('<form action="/servlet/updatepasswdcontroller" method="post">' +
            '<div class="form-group">' +
            '<label for="oldPassword">旧密码：</label> ' +
            '<input type="text" class="form-control" id="oldPassword" placeholder="请输入旧密码"> ' +
            '</div> ' +
            '<div class="form-group">' +
            ' <label for="newPassword">新密码：</label> ' +
            '<input type="password" name="newpasswd" class="form-control" id="newPassword" placeholder="请输入新密码"> '+
            '</div>'+
            '<div class="form-group">' +
            ' <label for="newPasswordRepeat">新密码：</label> ' +
            '<input type="password" class="form-control" id="newPasswordRepeat" placeholder="请再次输入新密码"> '+
            '</div>'+
            '<button type="submit" class="btn btn-default">确定修改</button> ' +
            '</form>'
        );
    }

    function examResult() {
        $('#centerTitle').text("考试结果");
        $('#centerContent>*').hide();
        $.getJSON("22.php",function (data) {
            $('#centerContent').append('<div class="table-responsive">\n' +
                '        <table class="table table-bordered table-condensed">\n' +
                '            <tr>\n' +
                '                <th>姓名</th>\n' +
                '                <th>数学成绩</th>\n' +
                '                <th>班级</th>\n' +
                '                <th>英语成绩</th>\n' +
                '                <th>班级</th>\n' +
                '                <th></th>\n' +
                '            </tr>\n' +
                '            <tr>\n' +
                '                <td>'+ data.name + '</td>\n' +
                '                <td>'+ data.mathScore + '</td>\n' +
                '                <td>'+ data.mathClass + '</td>\n' +
                '                <td>'+ data.englishScore + '</td>\n' +
                '                <td>'+ data.englishClass + '</td>\n' +
                '            </tr>\n' +
                '        </table>\n' +
                '        </div>'
            );
        });
    }
    
    function showNotice(id, title, content, optime, teacher, files_json) {
    	file_json = files_json.replace(/\@/g, '\"');
        $('#centerTitle').text("通知");
        $('#centerContent>*').hide();

        $('#centerContent').append('<h2>'+title+'</h2>' +
            '<p>'+content+'</p>');
        $.each($.parseJSON(file_json), function(n, value){
        	$('#centerContent').append('<a href='+value.address+' download="name">'+value.name+'</a>'+
        		'<input type="file">'	+
        		'<a href="">提交文件</a>'+'<hr>')
        });
    }
</script>
</html>