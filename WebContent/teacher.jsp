<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title id="title"></title>
    
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
            <a href="/StudentMS/servlet/TeacherShowMemoController">主页</a>
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
                    新生信息
                </li>
                <li>
                    <a href="#" onclick="getCheck()">新生报到情况</a>
                </li>
                <li>
                    <a href="#" onclick="allInfo()">新生信息统计</a>
                </li>
                <c:if test="${sessionScope.role == 0}">
					<li>
						<a href="#" onclick="getCYL()">团成员统计</a>
					</li>
				</c:if>

                <li class="nav-header">
                    通知管理
                </li>
                <li>
                    <a href="#" onclick="setNotice()">发布通知</a>
                </li>
                <li>
                    <a href="#" onclick="noticeManage()">通知回收</a>
                </li>
                <hr/>
                <li>
                    <a href="/StudentMS/servlet/showPostController">新生论坛</a>
                </li>
            </ul>
        </div>


        <div class="col-md-7" style="margin: 0 3px">
            <div class="row">
                <ul class="nav navbar-nav">
                    <li style="background: url(/StudentMS/img/top_comp02.gif) no-repeat; color: #fff; width: 97px; display: none" class="nav-header" id="centerTitle">
                    </li>
                </ul>
            </div>

            <div id="centerContent" >

            </div>

        </div>



        <div class="col-md-2" style="margin: 0 3px">
            <div class="row">
                <div class="col-lg-9">
                    <ul class="nav navbar-nav">
                        <li style="background: url(/StudentMS/img/top_comp02.gif) no-repeat; color: #fff; width: 97px;" class="nav-header">
                            工作日志
                        </li>
                    </ul>
                </div>
                <div class="col-md-3">
                    <div style="border: none" class="col-md-3"><a onclick="customize()">编辑</a></div>
                </div>
            </div>

            <c:forEach var="memo_item" items="${requestScope.memoList}" step="1">
	            <div class="row">
                	<div class="col-md-10"><a onclick = "')"></a>
					<a onclick="memoContent('${memo_item.id}')">${memo_item.title}</a>
					</div>
                	<div class="col-md-1"><a href = "TeacherDeleteMemoController?id=${memo_item.id }&type=1"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span></a></div>
            	</div>
            </c:forEach>
            
    </div>

        <script>
        var infos = [];
        var checkArr = [];
        var checkVal = [];
        var classes = [];
        var classCheckVal = [];
        
       // $('#title').text("老师个人中心");
       var roleForTitle = ${sessionScope.role};
        if(roleForTitle){
        	$('#title').text("班主任个人中心");
        }
        else{
        	
        	$('#title').text("辅导员个人中心");
        }
                /**
                 * 查询报到情况
                 **/
                function checkSetTitle() {
                    $('#centerTitle').show();
                    $('#centerTitle').text("报到情况");
                    
                }
                function check() {
                    $.ajaxSettings.async = false;
                    $.getJSON("/StudentMS/servlet/TeacherSearchAllStudentsRegisterController",function(data){
                        checkArr = data;
                    });
                }
                function getCheck() {
                    checkSetTitle();
                    check();
                    if ($('#centerContent').length > 0){
                        $('#centerContent').empty();
                    }
                    if ($('#rowCheck').length <= 0){
                        var info = '';
                         for (var i =0;i<checkArr.length;i++){
                            info +=
                                '<tr>' +
                                '<td>'+ checkArr[i].name +'</td>' +
                                '<td>'+ checkArr[i].isCheck +'</td>' +
                                '</tr>'
                                
                        }
                        var content = $('#centerContent');
                        content.append('<div class="row" id="rowCheck">' +
                            '<a class="btn btn-default" onclick="getAll()">所有新生</a>' +
                            '<a class="btn btn-default" onclick="isCheck()">已报到新生</a> ' +
                            '<a class="btn btn-default" onclick="isNotCheck()">未报到新生</a> '+
                            '<form method=post action="/StudentMS/servlet/TeacherOutputRegisterController">'+
                            '<input value="all" name="check" id="downloadContent" type="hidden"/> ' +
                            '<input class="btn btn-default" type="submit" value="下载导出" >'+
                            '</form>'+
                            '</div>' +
                            '<table class="table table-bordered table-condensed">' +
                            '<thead>' +
                            '<tr>' +
                            '<th>姓名</th>' +
                            '<th>是否报到</th>' +
                            '</tr>' +
                            '</thead>' +
                            '<tbody>' + info + '</tbody></table>'
                        );
                    }
                }
                function getAll() {
                    $('#downloadContent').val('all');
                    $("table tbody tr")
                        .hide()
                        .show();
                }
                function isNotCheck() {
                    $('#downloadContent').val('isNotCheck');
                    $("table tbody tr")
                        .hide()
                        .filter(":contains('"+ "否" +"')")
                        .show();
                }
                function isCheck() {
                    $('#downloadContent').val('isCheck');
                    $("table tbody tr")
                        .hide()
                        .filter(":contains('"+ "是" +"')")
                        .show();
                }
                /**
                 * 学生信息查询
                 **/
                function getInfo() {
                    $.ajaxSettings.async = false;
                    $.getJSON("/StudentMS/servlet/TeacherSearchAllStudentsController",function(data){
                        infos = data;
                    });
                    
                    $.getJSON("/StudentMS/servlet/ShowAllClassesController",function (data) {
                        classes = data;
                    })
                }
                function infoSetTitle() {
                    $('#centerTitle').show();
                    $('#centerTitle').text("新生信息");
                }
                function allInfo() {
                    infoSetTitle();
                    getInfo();
                    if ($('#centerContent').length > 0){
                        $('#centerContent').empty();
                        $('#rowInfo').empty();
                    }
                    var tableBody = '';
                    for (var i = 0;i<infos.length;i++){
                            tableBody += '<tr>' +
                                '<td class="col0">'+ infos[i].name +'</td>' +
                                '<td class="col1">'+ infos[i].number +'</td>' +
                                '<td class="col2">'+ infos[i].class_id +'</td>' +
                                '<td class="col3">'+ infos[i].id_num +'</td>' +
                                '<td class="col4">'+ infos[i].sex +'</td>' +
                                '<td class="col5">'+ infos[i].age +'</td>' +
                                '<td class="col6">'+ infos[i].email +'</td>' +
                                '<td class="col7">'+ infos[i].wechat +'</td>' +
                                '<td class="col8">'+ infos[i].qq +'</td>' +
                                '<td class="col9">'+ infos[i].phone +'</td>' +
                                '<td class="col10">'+ infos[i].address +'</td>' +
                                '<td class="col11">'+ infos[i].nation +'</td>' +
                                '</tr>';
                    }
                    
                    var premission = ${sessionScope.role};
                    var classCheck = '';
                    if (premission == 0){
                        classCheck += '<label>班级：</label>';
                        for (var i = 0;i<classes.length;i++){
                            classCheck += '<label class="checkbox-inline">' +
                                '<input type="checkbox" class="classesCheck" id="" name='+classes[i].class1+' value="'+ classes[i].class1 +'">'+ classes[i].class1 +
                                '</label>';
                        }
                        
                    }
                    
                    var content = $('#centerContent');
                    content.append('<div id="rowInfo">' +
                        '<form action="/StudentMS/servlet/TeacherOutputStudentInfoController" method="post" id="InfoForm">' +
                        '<label class="checkbox-inline">' +
                        '<input type="checkbox" class="infoHead" name="name" value="0">姓名' +
                        '</label>'+
                        '<label class="checkbox-inline">' +
                        '<input type="checkbox" class="infoHead" name="number" value="1">学号' +
                        '</label>'+
                        '<label class="checkbox-inline">' +
                        '<input type="checkbox" class="infoHead" name="class_id" value="2">班级' +
                        '</label>'+
                        '<label class="checkbox-inline">' +
                        '<input type="checkbox" class="infoHead" name="id_num" value="3">身份证号' +
                        '</label>'+
                        '<label class="checkbox-inline">' +
                        '<input type="checkbox" class="infoHead" name="sex" value="4">性别' +
                        '</label>'+
                        '<label class="checkbox-inline">' +
                        '<input type="checkbox" class="infoHead" name="age" value="5">年龄' +
                        '</label>'+
                        '<label class="checkbox-inline">' +
                        '<input type="checkbox" class="infoHead" name="email" value="6">邮箱' +
                        '</label>'+
                        '<label class="checkbox-inline">' +
                        '<input type="checkbox" class="infoHead" name="wechat" value="7">微信' +
                        '</label>'+
                        '<label class="checkbox-inline">' +
                        '<input type="checkbox" class="infoHead" name="qq" value="8">QQ' +
                        '</label>'+
                        '<label class="checkbox-inline">' +
                        '<input type="checkbox" class="infoHead" name="phone" value="9">手机号' +
                        '</label>'+
                        '<label class="checkbox-inline">' +
                        '<input type="checkbox" class="infoHead" name="address" value="10">家庭住址' +
                        '</label>'+
                        '<label class="checkbox-inline">' +
                        '<input type="checkbox" class="infoHead" name="nation" value="11">民族' +
                        '</label><br>'+
                        classCheck +
                        '<br><div class="pull-right"><a class="btn btn-default" onclick="infoSubmit()">提交</a>' +
                        '<input class="btn btn-default" type="submit" value="导出" ></div>' +
                        '</form>' +
                        '<h4 class="text-center"> 学生信息统计 </h4>' +
                        '<div class="table-responsive">'+
                        '<table class="table table-bordered table-condensed" id="infoTable">' +
                        '<thead>' +
                        '<tr>' +
                        '<td class="col0">姓名</td>' +
                        '<td class="col1">学号</td>' +
                        '<td class="col2">班级</td>' +
                        '<td class="col3">身份证号</td>' +
                        '<td class="col4">性别</td>' +
                        '<td class="col5">年龄</td>' +
                        '<td class="col6">邮箱</td>' +
                        '<td class="col7">微信</td>' +
                        '<td class="col8">QQ</td>' +
                        '<td class="col9">手机号</td>' +
                        '<td class="col10">家庭住址</td>' +
                        '<td class="col11">民族</td>' +
                        '</tr>' +
                        '</thead>' +
                        '<tbody>' + tableBody +
                        '</tbody>' +
                        '</table>'+
                        '</div>'+
                        '</div>'
                    );
                }
                function getCheckVal() {
                    var obj = document.getElementsByClassName("infoHead");
                    checkVal.splice(0,checkVal.length);
                    classCheckVal.splice(0,classCheckVal.length);
                    for(var k in obj){
                        if(obj[k].checked)
                            checkVal.push(obj[k].value);
                    }
                    
                    var obj1 = document.getElementsByClassName("classesCheck");
                    for(var k in obj1){
                        if(obj1[k].checked)
                            classCheckVal.push(obj1[k].value);
                    }
                }
                function infoSubmit() {
                    getCheckVal();
                    //alert(classCheckVal.length);
                    $('#infoTable tr td').hide();
                    for (var i = 0;i<checkVal.length;i++){
                        var msg = 'col'+ checkVal[i];
                        $('.' + msg).show();
                    }
                    
                    if(classCheckVal.length != 0){
                    	for (var i = 0;i<classCheckVal.length;i++){
                            $("table tbody tr")
                                .hide()
                                .filter(":contains('"+ classCheckVal[i] +"')")
                                .show();
                        }
                    }else{
                    	$("table tbody tr")
                       .hide()
                       .show();
                   }
                    
                }
                function checkSubmit() {
                    getCheckVal();
                    var head = ['姓名','学号', '班级','身份证','性别','年龄','邮箱','微信','QQ','手机号','家庭住址','民族'];
                    var data = [];
                    for (var i = 0;i<checkVal.length;i++){
                        data = head[checkVal[i]]
                    }
                    $.ajax({
                        url:'./alpha/studentms/controller/TeacherOutputStudentInfoController',
                        data:data,
                        type:'POST',
                        success:allInfo()
                    });
                }
                /**
                 * 发布通知
                 **/
                function setNotice() {
                    $('#centerTitle').show();
                    $('#centerTitle').text("新生信息");
                    if ($('#centerContent').length > 0){
                        $('#centerContent').empty();
                    }
                    $('#centerContent').append('<form action="/StudentMS/servlet/teacherUploadMessage" method="post" id="noticeForm" enctype="multipart/form-data"> ' +
                        '<div class="form-group"> ' +
                        '<label >通知标题：</label> ' +
                        '<input type="text" class="form-control" id="" name="title" placeholder="请输入标题"> ' +
                        '</div> ' +
                        '<div class="form-group"> ' +
                        '<label for="exampleInputPassword1">内容：</label> ' +
                        '<textarea class="form-control" rows="3" form="noticeForm" name="content"></textarea>'+
                        '</div>' +
                        '<a class="btn btn-default" onclick="uploadFiles()">上传附件</a>'+
                        '<input type="hidden" id="uploadFilesFlag" value="0" name="flag"> ' +
                       '<div class="form-group" id="uploadFile" style="display: none">' +
                        '<label >上传附件</label>' +
                        '<input type="file" id="file" name="file"  multiple="multiple">' +
                        '</div>'+
                    '<button type="submit" class="btn btn-default">发布</button> </form>');
                }
                function uploadFiles() {
                    var upload = $('#uploadFile');
                    var flag = $('#uploadFilesFlag');
                    var file = $('#file');
                    if (upload.css('display') == 'none'){
                        flag.val(1);//有附件
                        upload.show();
                    }else {
                        if (file.val() == ""){
                            flag.val(0);//无附件
                            upload.hide();
                        }
                    }
                }
                function customize() {
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
                                '<input type="text" name="myTask[]" id="field_'+ FieldCount +'"/><a href="#" class="removeclass">×</a>' +
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
                /**
                 * 通知回收
                 */
                function noticeManage(){
                    var notice = '';
                    $.ajaxSettings.async = false;
                    $.getJSON("/StudentMS/servlet/teacherMessageRecycle",function(data){
                        notice = data;
                    });
                    $('#centerTitle').show();
                    $('#centerTitle').text("通知管理");
                    if ($('#centerContent').length > 0){
                        $('#centerContent').empty();
                    }
                    var content = $('#centerContent');
                    var lis = '';
                    for (var i = 0;i<notice.length;i++){
                        lis += '<li><a onclick="noticeFile(\'' +notice[i].id +  '\')">'+ notice[i].title + '</a></li>';
                    }
                    content.append('<ul>'+ lis +'</ul>');
                }
                function noticeFile(id) {
                    var content = $('#centerContent');
                    var files = '';
                    $.getJSON('/StudentMS/servlet/teacherDocumentRecycle?id=' + id,function (data) {
                        files = data;
                    }); 
                    content.empty();
                    var lis = '';
                    for (var i = 0;i<files.length;i++){
                        lis += '<li><a href="' + "/StudentMS/servlet/studentDownLoad?filename=" + files[i].name + '">' + files[i].name + '</a></li>';
                    }
                    content.append('<ul>'+ lis +'</ul>' +
                        '<a onclick="noticeManage()">返回</a>');
                }
                
                /**
                 * 查询团员情况
                 **/
                function CYLSetTitle() {
                    $('#centerTitle').show();
                    $('#centerTitle').text("团员情况");
                }
                function CYL() {
                    $.ajaxSettings.async = false;
                    $.getJSON("/StudentMS/servlet/assistantOutputAllStudentCYLInfo",function(data){
                        checkArr = data;
                    });
                }
                function getCYL() {
                    CYLSetTitle();
                    CYL();
                    if ($('#centerContent').length > 0){
                        $('#centerContent').empty();
                    }
                 
                    if ($('#rowCheck').length <= 0){
                        var info = '';
                        for (var i =0;i<checkArr.length;i++){
                            info +=
                                '<tr>' +
                                '<td>'+ checkArr[i].name +'</td>' +
                                '<td>'+ checkArr[i].isCYL +'</td>' +
                                '</tr>'
                        }
                        var content = $('#centerContent');
                        content.append('<div class="row" id="rowCheck">' +
                            '<a class="btn btn-default" onclick="getAllCYL()">所有新生</a>' +
                            '<a class="btn btn-default" onclick="isNotCYL()">非团员新生</a> ' +
                            '<a class="btn btn-default" onclick="isCYL()">团员新生</a> '+
                            '<form method="post" action="/StudentMS/servlet/assistantOutputCYLInfo">'+
                            '<input value="all" name="check" id="downloadContent" type="hidden"/> ' +
                            '<input class="btn btn-default" type="submit" value="下载导出"/>'+
                            '</form>'+
                            '</div>' +
                            '<table class="table table-bordered table-condensed">' +
                            '<thead>' +
                            '<tr>' +
                            '<th>姓名</th>' +
                            '<th>是否为团员</th>' +
                            '</tr>' +
                            '</thead>' +
                            '<tbody>' + info + '</tbody></table>'
                        );
                    }
                }
                function getAllCYL() {
                    $('#downloadContent').val('all');
                    $("table tbody tr")
                        .hide()
                        .show();
                }
                function isNotCYL() {
                    $('#downloadContent').val('isNotCheck');
                    $("table tbody tr")
                        .hide()
                        .filter(":contains('"+ "否" +"')")
                        .show();
                }
                function isCYL() {
                    $('#downloadContent').val('isCheck');
                    $("table tbody tr")
                        .hide()
                        .filter(":contains('"+ "是" +"')")
                        .show();
                }
                
                
                /**
                 * 工作日志编辑
                 **/
                function customize() {
                    $('#centerTitle').show();
                    $('#centerTitle').text("工作日志");
                    if ($('#centerContent').length > 0){
                        $('#centerContent').empty();
                    }
                    $('#centerContent').append('<form action="/StudentMS/servlet/TeacherAddMemoController" method="post" id="memoForm"> ' +
                        '<div class="form-group"> ' +
                        '<label >日志标题：</label> ' +
                        '<input type="text" class="form-control" id="" name="memoTitle" placeholder="请输入标题"> ' +
                        '</div> ' +
                        '<div class="form-group"> ' +
                        '<label>内容：</label> ' +
                        '<textarea class="form-control" rows="3" form="memoForm" name="content"></textarea>'+
                        '</div>' +
                        '<button type="submit" class="btn btn-default">保存</button> </form>');
                }
                function memoContent(memoid) {
                    var url = '/StudentMS/servlet/TeacherShowOneMemoController?memoid='+ memoid;
                    var memoData = '';
                    $.getJSON(url,function (data) {
                        memoData = data;
                        if ($('#centerContent').length > 0){
                            $('#centerContent').empty();
                        }
                        $('#centerContent').append('<div id="notice"> ' +
                            '<h2>' + memoData.title + '</h2>' +
                            '<p>'+ memoData.content + '</p>' +
                            '</div>');
                    });
                
                }
        </script>

</div>
</div>

</body>
</html>