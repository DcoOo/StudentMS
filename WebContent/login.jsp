<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>登录</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/jquery/3.2.1/core.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>

<body>

<div class="container">
    <div>
        <img src="img/logo.jpg" alt="" class="img-circle">
    </div>

    <div class="row">

        <div class="col-md-6">
            <form action="servlet/logincontroller" method="POST">
                <div class="form-group">
                    <label for="username">账号：</label>
                    <input type="text" class="form-control" name="username" placeholder="请输入学号或工号">
                </div>
                <div class="form-group">
                    <label for="passwd">密码：</label>
                    <input type="password" class="form-control" name="passwd" placeholder="请输入密码">
                </div>
                <div class="form-group">
                    <label>身份：</label>
                    <label class="radio-inline">
                        <input type="radio" name="role" id="inlineRadio1" value="student"> 学生
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="role" id="inlineRadio2" value="teacher"> 教职工 
                    </label>
                </div>
                <div class="checkbox">
                    <label>
                        <input type="checkbox">记住我
                    </label>
                </div>
                <button type="submit" class="btn btn-default">登录</button>
            </form>
        </div>

        <div class="col-md-6">

        </div>
    </div>

</div>
</body>
</html>