<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>学生登录</title>
    <link href="/StudentMS/css/bootstrap.min.css" rel="stylesheet">
    <script src="/StudentMS/js/jquery-3.2.1.min.js"></script>
    <script src="/StudentMS/js/bootstrap.min.js"></script>

    <style>
        .col-md-3,.col-md-5,.row{
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
        <img src="/StudentMS/img/logo.jpg" alt="" class="img-circle">
</div>

<nav style="background:#1d85d7" class="navbar navbar-default">
    <ul class="nav navbar-nav">
        <li class="nav-header">
            主页
        </li>
    </ul>
</nav>

<div class="row-fluid">
    <div class="col-md-3" style="margin: 0 3px">

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
                <a href="#">个人信息修改</a>
            </li>
            <li>
                <a href="#">密码修改</a>
            </li>
            <li class="nav-header">
                信息查询
            </li>

            <li>
                <a href="#" >分班考试结果</a>
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


    <div class="col-md-5" style="margin: 0 3px">
        <div class="row">
        <ul class="nav navbar-nav">
            <li style="background: url(/StudentMS/img/top_comp02.gif) no-repeat; color: #fff; width: 97px;" class="nav-header">
                近期通知
            </li>
        </ul>
        </div>

        <ul class="list-unstyled">
            <li>1</li>
            <li>1</li>
            <li>1</li>
            <li>1</li>
        </ul>

        <nav aria-label="Page navigation">
            <ul class="pagination">
                <li>
                    <a href="#" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <li><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li>
                    <a href="#" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>
        </nav>
    </div>



    <div class="col-md-3" style="margin: 0 3px">
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
<script>
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
</script>
</body>
</html>