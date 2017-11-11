<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>学生登录</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/jquery/3.2.1/core.js"></script>
    <script src="js/bootstrap.min.js"></script>

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
        <img src="img/logo.jpg" alt="" class="img-circle">
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
            <li style="background: url(img/top_comp02.gif) no-repeat; color: #fff; width: 97px;" class="nav-header">
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
            <li style="background: url(img/top_comp02.gif) no-repeat; color: #fff; width: 97px;" class="nav-header">
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
            <li style="background: url(img/top_comp02.gif) no-repeat; color: #fff; width: 97px;" class="nav-header">
                待办任务
            </li>
        </ul>
        </div>

        <div>

            <div class="row">
                <div class="col-md-12">必做任务</div>
            </div>
            <div class="row">
                <div class="col-md-10">${requestScope.mustMemoList.title}</div>
                <div class="col-md-1"><a><span class="glyphicon glyphicon-ok" aria-hidden="true"></span></a></div>
            </div>

        </div>

        <hr>
        <div>
            <div class="row">
                <div class="col-md-9">选做任务</div>
                <div style="border: none" class="col-md-3"><a>编辑</a></div>
            </div>
            <div class="row">
                <div class="col-md-10">${requestScope.optionMemoList.title }</div>
                <div class="col-md-1"><a><span class="glyphicon glyphicon-ok" aria-hidden="true"></span></a></div>
            </div>
            <div class="row">
                <div class="col-md-10">任务2</div>
                <div class="col-md-1"><a><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a></div>
            </div>
        </div>
        <hr>

        <div>
            <div class="row">
                <div class="col-md-9">自定义任务</div>

                <div style="border: none" class="col-md-3"><a>编辑</a></div>
            </div>
            <div class="row">
                <div class="col-md-10">${requestScope.customMemoList.title }</div>
                <div class="col-md-1"><a><span class="glyphicon glyphicon-ok" aria-hidden="true"></span></a></div>
            </div>
        </div>

    </div>
</div>

</div>
</body>
</html>