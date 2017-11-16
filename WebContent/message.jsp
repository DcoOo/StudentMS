<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>个人信息统计</title>
    <link href="/StudentMS/css/bootstrap.min.css" rel="stylesheet">
    <script src="/StudentMS/js/jquery-3.2.1.min.js"></script>
    <script src="/StudentMS/js/bootstrap.min.js"></script>
</head>

<body>

<div class="container">
    <div>
        <img src="/StudentMS/img/logo.jpg">
    </div>

    <div class="row">
        <p class="text-center text-info">
        <strong>
            由于您第一次登录我们的系统，所以需要您填写一些自己的基础信息，这些信息可以在登录系统后再次进行修改，填写并提交后便可以登录到个人中心。
        </strong></p>
    </div>
    <div class="row">
            <form method="post" action="/StudentMS/servlet/studentInfoGather">
                <div class="form-group">
                    <label for="email">邮箱：</label>
                    <input type="email" class="form-control" name="studentEmail" id="email" placeholder="请输入邮箱">
                </div>

                <div class="form-group">
                    <label for="age">年龄：</label>
                    <input type="text" class="form-control" name="studentAge" id="age" placeholder="请输入年龄">
                </div>

                <div class="form-group">
                    <label for="phone">手机号：</label>
                    <input type="text" class="form-control" name="studentPhone" id="phone" placeholder="请输入手机号">
                </div>

                <div class="form-group">
                    <label for="nation">民族：</label>
                    <input type="text" class="form-control" name="studentNation" id="nation" placeholder="请输入民族">
                </div>

                <div class="form-group">
                    <label>性别：</label>
                    <label class="radio-inline">
                        <input type="radio" name="studentSex" id="male" value="male"> 男
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="studentSex" id="female" value="female"> 女
                    </label>
                </div>

                <div class="form-group">
                    <label>是否为团员：</label>
                    <label class="radio-inline">
                        <input type="radio" name="studentCYL" id="yes" value="yes"> 是
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="studentCYL" id="no" value="no"> 否
                    </label>
                </div>

                <div class="form-group">
                    <label for="weChat">微信：</label>
                    <input type="text" class="form-control" name="studentWechat" id="weChat" placeholder="请输入微信号">
                </div>

                <div class="form-group">
                    <label for="QQ">QQ:</label>
                    <input type="text" class="form-control" name="studentQQ" id="QQ" placeholder="请输入QQ号">
                </div>

                <div class="form-group">
                    <label for="address">家庭住址：</label>
                    <input type="text" class="form-control" name="studentAddress" id="address" placeholder="请输入家庭住址">
                </div>

                <div class="form-group">
                    <label>选做任务：</label>
                <div class="checkbox">
                    <c:forEach var="memo" items="${optionMemos}">
                    	<label>
                    		<input type="checkbox" value="${memo.getId()}" name="optionMemo">
                    		${memo.getTitle()}
                    	</label>
					</c:forEach>
                </div>
                </div>

                <button type="submit" class="btn btn-default">提交</button>
            </form>
    </div>
</div>
</body>
</html>