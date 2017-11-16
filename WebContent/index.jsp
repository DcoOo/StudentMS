<%@page import="org.omg.CORBA.portable.ValueBase"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.lang.StringBuilder" %>
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
        <li class="nav-header" onclick="jQuery:window.location.reload()">
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
                <a href="#" onclick="rules()">学校规章制度</a>
            </li>
            <li>
                <a href="#" onclick="intro()">学校部门介绍</a>
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
        $.getJSON("/StudentMS/servlet/studentInfoShow",function (data) {
            $('#centerContent').append('<div class="table-responsive">\n' +
                    '                <form action="/StudentMS/servlet/studentInfoUpdate" method="post">' +
                '        <table class="table table-bordered table-condensed">\n' +
                '            <tr>\n' +
                '                <th>邮箱</th>\n' +
                '                <th>年龄</th>\n' +
                '                <th>手机号</th>\n' +
                '            </tr>\n' +
                '            <tr>\n' +
                '                <td><input type="text" name="studentEmail" value="'+ data.email + '"></td>\n' +
                '                <td><input type="text" name="studentAge" value="'+ data.age + '"></td>\n' +
                '                <td><input type="text" name="studentPhone" value="'+ data.phone + '"></td>\n' +
                '            </tr>\n' +
                '            <tr>\n' +
                '                <th>民族</th>\n' +
                '                <th>性别</th>\n' +
                '                <th>团员</th>\n' +
                '            </tr>\n' +
                '            <tr>\n' +
                '                <td><input type="text" name="studentNation" value="'+ data.nation + '"></td>\n' +
                '                <td><input type="text" name="studentSex" value="'+ data.sex + '"></td>\n' +
                '                <td><input type="text" name="studentCYL" value="'+ data.is_cyl + '"></td>\n' +
                '            </tr>\n' +
                '            <tr>\n' +
                '                <th>微信</th>\n' +
                '                <th>QQ</th>\n' +
                '                <th>家庭住址</th>\n' +
                '            </tr>\n' +
                '            <tr>\n' +
                '                <td><input type="text" name="studentWechat" value="'+ data.wechat + '"></td>\n' +
                '                <td><input type="text" name="studentQQ" value="'+ data.qq + '"></td>\n' +
                '                <td><input type="text" name="studentAddress" value="'+ data.address + '"></td>\n' +
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
        $('#centerContent').append('<form action="/StudentMS/servlet/updatepasswdcontroller" id="updatePasswdForm" method="post">' +
            '<div class="form-group">' +
            '<label for="oldPassword">旧密码：</label> ' +
            '<input type="password" class="form-control" id="oldPassword" placeholder="请输入旧密码"> ' +
            '</div> ' +
            '<div class="form-group">' +
            ' <label for="newPassword">新密码：</label> ' +
            '<input type="password" name="newpasswd" class="form-control" id="newPassword" placeholder="请输入新密码"> '+
            '</div>'+
            '<div class="form-group">' +
            ' <label for="newPasswordRepeat">新密码：</label> ' +
            '<input type="password" class="form-control" id="newPasswordRepeat" placeholder="请再次输入新密码"> '+
            '</div>'+
            '<button type="button" onclick="formSubmit()" class="btn btn-default">确定修改</button> ' +
            '</form>'
        );
    }
    
    function formSubmit() {
        var oldpsd = "<%=session.getAttribute("passwd")%>";
        var oldpsdInput = $('#oldPassword').val();
        if (oldpsd == oldpsdInput){
        	if ($('#newPassword').val() == $('#newPasswordRepeat').val()){
        		alert('修改密码成功');
        		$('#updatePasswdForm').submit();
        		
        	}else{
        		alert("两次密码输入不一致，请重新输入");
        	}
        }else{
        	alert("原密码输入错误，请重新输入");
        }
    }
    
    
    
    function examResult() {
        $('#centerTitle').text("考试结果");
        $('#centerContent>*').hide();
        $.getJSON("/StudentMS/servlet/studentClassAndGradeInfo",function (data) {
            $('#centerContent').append('<div class="table-responsive">\n' +
                '        <table class="table table-bordered table-condensed">\n' +
                '            <tr>\n' +
                '                <th>姓名</th>\n' +
                '                <th>英语成绩</th>\n' +
                '                <th>班级</th>\n' +
                '            </tr>\n' +
                '            <tr>\n' +
                '                <td>'+ data.studentName + '</td>\n' +
                '                <td>'+ data.englishGrade + '</td>\n' +
                '                <td>'+ data.classInfo + '</td>\n' +
                '            </tr>\n' +
                '        </table>\n' +
                '        </div>'
            );
        });
    }
    
    function showNotice(id, title, content, optime, teacher, files_json) {
    	var builder = '';
    	file_json = files_json.replace(/\@/g, '\"');
        $('#centerTitle').text("通知");
        $('#centerContent>*').hide();
        $('#centerContent').append('<h2>'+title+'</h2>' +
            '<p>'+content+'</p>');
        $.each($.parseJSON(file_json), function(n, value){
        	builder += '<a href='+value.address+' download="name">'+value.name +'</a>'+
			'<input type="hidden" name="modelDocID" value="' + value.modelDoc + '">'+
			'<input type="file" name="file1">';
        });
        
        $('#centerContent').append(''+
    	'<form action="/StudentMS/servlet/studentUploadHandle" enctype="multipart/form-data" method="post">' +
    		builder +
    		'<input type="submit" value="提交">' +
    		'</form>');
    }
    
    function rules() {
        $('#centerTitle').text("学校规章制度");
        $('#centerContent>*').hide();
        var info;
        $('#centerContent').append('<div class="table-responsive"><strong>大学章程</strong><hr>　北京信息科技大学（以下简称“学校”）是在原北京机械工业学院和原北京信息工程学院的基础上合并组建， 2008年3月26日经教育部批准设立的全日制普通高等学校，办学历史可追溯到1930年代。学校以人才培养、科学研究、社会服务和文化传承创新为主要职能实施高等教育，不断拓展继续教育，积极开展对外交流合作，面向社会依法自主办学。<br>\n' +
            '　　为完善现代大学制度，保障学校依法自主办学，依照《中华人民共和国教育法》、《中华人民共和国高等教育法》、《中国共产党普通高等学校基层组织工作条例》、《高等学校章程制定暂行办法》等法律法规，制定本章程。<br>\n' +
            '　　学校以本章程为依据，制定内部管理制度及规范性文件、实施各项办学活动。<br>\n' +
            '　　第一章 总 则<br>\n' +
            '　　第一条 学校在北京市机构编制委员会办公室登记，名称为：北京信息科技大学，简称为：信息科大。英文译名为：Beijing Information Science and Technology University，英文缩写为：BISTU。学校网址为：http://www.bistu.edu.cn。学校法定注册地址为：北京市海淀区清河小营东路12号。学校设有五个校区，地址分别为：北京市海淀区清河小营东路12号、北京市朝阳区北四环中路35号、北京市海淀区清河昌平路南段26号、北京市朝阳区金台西路2号院、北京市朝阳区酒仙桥六街坊1号院。<br>\n' +
            '　　第二条 学校由北京市人民政府举办。学校的分立、合并、终止等重要事项，依照法定程序由国家教育行政部门审批。北京市人民政府为学校提供办学经费，保障学校办学的所需条件，支持学校依照法律、法规和学校章程自主办学，对学校进行宏观指导，保护学校的合法权益。学校接受北京市人民政府教育行政部门对学校的领导、管理、监督与考核，履行办学职责。<br>\n' +
            '　　第三条 学校坚持社会主义办学方向，依法实行中国共产党北京信息科技大学委员会（以下简称“学校党委”）领导下的校长负责制，贯彻落实党的教育方针，坚持育人为本、德育为先、素质为重，着力增强受教育者的社会责任感、创新精神、实践能力。<br>\n' +
            '　　第四条 学校是非营利性事业组织，具有独立法人资格。校长是学校的法定代表人。<br>\n' +
            '　　第五条 学校立足北京、面向全国，建设以工管为主体、工管理经文法多学科协调发展，在电子信息、现代制造与光机电一体化、知识管理与技术经济等领域特色鲜明，培养高素质应用型人才为主的教学研究型大学。<br>\n' +
            '　　第二章 学校使命与实现<br>\n' +
            '　　第六条 学校以人才培养、知识创新、服务社会、文化传承为使命，全面提高教育教学质量，提升人才培养水平，增强科学研究能力，服务经济社会发展，推进文化传承创新。<br>\n' +
            '　　第七条 学校以人才培养为根本任务，教育教学是学校办学活动的基本内容。在经费保障、资源配置等方面，学校优先保障教育教学的基本需要。<br>\n' +
            '　　第八条 学校根据国家、社会发展需要和自身条件，依照国家核定的办学规模，制定招生方案，自主调节系科招生比例，优化教育结构。<br>\n' +
            '　　第九条 学校主要教育形式是全日制学历教育。学校依法确定和调整学历教育修业年限，实行学分制和弹性学制。学校根据受教育者的需要，开展非全日制学历教育与非学历教育、国际学生教育和中外合作教育。<br>\n' +
            '　　第十条 学校遵循教育发展规律，根据社会经济及行业发展对人才的需求，在学校权限范围内自主设置和调整学科、专业。<br>\n' +
            '　　第十一条 学校根据学科优势和办学特色，自主调整学校人才培养模式。对达到相关培养标准的学生，自主颁发相应毕业证书或授予相应学位，并报上级教育行政部门备案。<br>\n' +
            '　　第十二条 学校建立健全教学质量监控体系，定期公布教学质量报告，不断提高教学质量。<br>\n' +
            '　　第十三条 学校尊重学术自由，鼓励学术创新，开展科学研究，推进科技成果转化应用，依法保护知识产权，促进学术进步。<br>\n' +
            '　　第十四条 学校发挥学科专业特色和人才培养优势，开展社会合作共建，通过科技合作研究、共建教学科研基地、互聘人员、联合培养学生等方式，推进产学研相结合，积极与政府、企事业单位、社会团体、其他社会组织及个人合作，形成社会支持与服务社会的双向互动。<br>\n' +
            '　　第十五条 学校培育和践行社会主义核心价值观，培育学生的科学素养和人文精神，推动社会主义先进文化的传承、创新和建设。传承学校精神，传播学校文化。<br>\n' +
            '　　第十六条 学校开展全方位、多层次、宽领域的国际交流与合作，开展高水平国际合作办学，引进和联合培养高端智力人才，不断提升学校的国际影响力和竞争力。<br>\n' +
            '　　第三章 治理结构\n' +
            '　　第十七条 依据《中国共产党普通高等学校基层组织工作条例》的规定设立学校党委。学校党委接受中共北京市委领导，接受中共北京市委教育工作委员会的指导。学校党委是学校的领导核心，统一领导学校工作，按照民主集中制原则进行决策，支持校长依法独立负责地行使职权。<br>\n' +
            '　　（一）学校党委由党员代表大会按照相关规定选举产生，每届任期五年。学校党委对党员代表大会负责并报告工作。经上级党组织批准，学校党委设常务委员会（以下简称“常委会”）。常委会由学校党委全体委员会议（以下简称“全委会”）选举产生。常委会在全委会闭会期间，行使党委职权，定期向全委会报告工作。学校党委设党委书记1人，党委副书记若干人；党委书记、党委副书记按照相关规定产生。党委书记主持学校党委的全面工作，主持学校党委全委会、常委会，党委副书记协助党委书记工作。<br>\n' +
            '　　（二）学校党委的主要领导职责是：执行中国共产党的路线、方针、政策，坚持社会主义办学方向，领导学校的党建和思想政治工作，讨论决定学校内部组织机构的设置和内部组织机构负责人的人选，讨论决定学校的改革、发展和基本管理制度等重大事项，保证以人才培养为中心的各项任务的完成。<br>\n' +
            '　　（三）学校党委全委会、常委会议事规则另行制定。<br>\n' +
            '　　（四）学校党委根据工作需要和党员人数设立党的基层组织。<br>\n' +
            '　　第十八条 中国共产党北京信息科技大学纪律检查委员会是学校的党内监督机构，由党员代表大会选举产生，在学校党委和上级纪委的领导下，围绕学校的中心工作，检查党的路线方针政策和决议及学校重大决策的执行情况，开展党纪和廉洁教育，协助学校党委加强党风廉政建设，组织协调学校反腐倡廉工作，检查、处理违反党章和其他党内法规的案件，受理党员的控告和申诉，维护党章规定的党员权利，保障和促进学校各项事业健康发展。<br>\n' +
            '　　第十九条 学校设校长1人，副校长若干人。校长、副校长由北京市人民政府任命。根据学校工作的需要，经上级有关部门批准，学校可设校长助理若干人，其产生程序及任免按照有关规定执行。校长全面负责学校的教学、科学研究和其他行政管理工作，副校长、校长助理协助校长工作。<br>\n' +
            '　　（一）校长行使下列职权：拟订发展规划，制定具体规章制度和年度工作计划并组织实施；组织教学活动、科学研究和思想品德教育； 拟订内部组织机构的设置方案，推荐副校长人选，任免内部组织机构的负责人；聘任与解聘教师以及内部其他工作人员，对学生进行学籍管理并实施奖励或者处分；拟订和执行年度经费预算方案，保护和管理校产，维护学校的合法权益；本章程规定的其他职权。<br>\n' +
            '　　（二）校长主持校长办公会，处理前款规定的有关事项。按照集体讨论、校长决定的方式，校长在充分听取意见的基础上进行决策。<br>\n' +
            '　　（三）校长办公会议事规则另行制定。\n' +
            '　　第二十条 学校设立学术委员会，健全以学术委员会为核心的学术管理体系与组织架构。学术委员会为校内最高学术机构，统筹行使学术事务的决策、审议、评定和咨询等职权。<br>\n' +
            '　　学术委员会委员按照学术委员会章程规定、经自下而上的民主推荐、公开公正的遴选方式等程序确定，由校长聘任，任期四年,可连选连任，但连任最长不超过两届。主任委员由校长提名，全体委员选举产生。学术委员会依其章程独立行使职权，尊重学术自由和学术平等，遵守学术道德和学术规范。\n' +
            '　　第二十一条 学校设立学位评定委员会。学位评定委员会依照法律和工作规程负责学位点申报、学位的评定、授予、撤销、研究生指导教师遴选、有关学位争议处理及其他学位事务等事宜。<br>\n' +
            '　　学校学位评定委员会主任由校长担任，委员由各学院推荐，经校长办公会批准，报北京市教育委员会批准，并转报国务院学位委员会备案，任期三年。<br>\n' +
            '　　第二十二条 学校设立教学工作委员会，负责学校教学工作的指导、评估、研究和监督等，为学校教学管理决策提供建议意见。<br>\n' +
            '　　教学工作委员会主任委员由主管教学工作的副校长担任，副主任委员由教务处处长担任，委员由各教学单位主管教学领导、教务处副处长及有关专家组成。委员会组成人员需由校长办公会审议批准，任期三年。<br>\n' +
            '　　第二十三条 学校根据需要设立相关工作的专门委员会，就相关事宜进行论证、审议、协调、评估等。各委员会依据学校授权或各自章程履行职责。<br>\n' +
            '　　第二十四条 教职工代表大会（以下简称“教代会”）是在学校党委领导下教职工参与学校民主管理和民主监督，维护教职工合法权益的重要形式。教职工代表按相关规定由教职工直接选举产生，以教师为主，每届任期五年，可连选连任。<br>\n' +
            '　　教代会设立执行委员会（以下简称“执委会”），执委会由教代会选举产生并对其负责，在教代会闭会期间代行其职能。教代会、执委会按照相关工作规程开展工作。<br>\n' +
            '　　第二十五条 校学生代表大会、研究生代表大会是在学校党委领导下学生实现自我教育、自我管理和自我服务，并参与学校民主管理和监督的重要形式，是学生自己的群众性组织，是学校联系学生的桥梁和纽带。代表由各学院学生选举产生，每届任期两年。<br>\n' +
            '　　第二十六条 学校根据需要设置内部组织机构，决定其职权职责配置。<br>\n' +
            '　　学校内部组织机构包括党政管理机构、教学机构、科研机构、直属机构和校资企业。<br>\n' +
            '　　学校党政管理机构、直属机构根据学校授权履行管理和服务职责。<br>\n' +
            '　　学校教学机构、科研机构包括学院和具有独立建制的科研院所，在学校授权范围内依法自主管理。<br>\n' +
            '　　学校附属的具有独立法人资格的校资企业，依照法律和学校规定对学校资产承担保值增值的责任，实行相对独立运营与管理。<br>\n' +
            '　　第二十七条 学校依法设立工会、共青团等群众组织。各群众组织由学校党委领导，依照法律法规、学校规定及各自章程独立开展活动，参与学校民主管理。<br>\n' +
            '　　学校内各民主党派的基层组织，按照各自章程开展活动，参与学校民主管理。<br>\n' +
            '　　第二十八条 根据需要学校可以设立理事会。理事会由政府、行业、企事业单位以及其他社会组织代表参加，对学校的建设、发展和管理提出意见和建议，进行监督和支持。<br>\n' +
            '　　理事会依照法律法规、学校规定及其章程开展活动。<br>\n' +
            '　　第二十九条 学校实行校院两级管理体制。学校对学院工作进行部署、指导、检查和考核。学院作为学校学科建设、人才培养、科学研究等工作的具体组织实施单位，在学校授权范围内实行自主管理。\n' +
            '　　第三十条 学院根据学校的规划、规定或授权，制订学院发展规划，进行学科专业建设、师资队伍建设、课程建设，实施培养计划，组织开展科学研究、社会服务、国际交流与合作和其他学术活动，设置内部机构，制定内部工作规则和办法，决定学院人员的聘用和管理，负责学生的教育和管理，管理和使用学校核拨的办学经费、设备和房屋等，行使学校赋予的其他职权。<br>\n' +
            '　　第三十一条 根据学院党员人数和工作需要，经学校党委批准，设立学院党的基层组织（以下简称“学院党组织”），学院党组织委员会由党员大会选举产生。学院党组织在学校党委的领导下开展工作，发挥政治核心作用，负责学院思想政治和党的建设工作，保证党和国家的各项方针、政策和学校的决定在本学院的贯彻执行，支持院长履行其职责。学院党组织书记、副书记通过公推直选方式由党员大会选举产生。<br>\n' +
            '　　第三十二条 学院设院长1人，副院长若干人，由学校按照《党政领导干部选拔任用工作条例》的规定，结合岗位任职条件等选拔产生。院长全面负责学院的教学、科学研究和其他行政管理工作，副院长协助院长工作。<br>\n' +
            '　　院长定期向本学院全体教职工或教职工代表大会报告工作。<br>\n' +
            '　　第三十三条 学院党政联席会议（以下简称“学院党政联席会”）由学院院长、党组织书记、副院长、党组织副书记等相关人员组成，是学院的最高决策机构。学院党政联席会由党组织书记主持，按照民主集中制原则实行集体决策。<br>\n' +
            '　　第三十四条 学院设立学术分委员会。学术分委员会的的职责、组成由学术委员会章程规定。<br>\n' +
            '　　第三十五条 学院设立学位评定分委员会。学位评定分委员会负责审查本单位学位授予等有关事宜。<br>\n' +
            '　　第三十六条 独立建制的科研机构，按照学校授权和自身职能，参照学校对学院的管理方式进行自主管理，承担相应的人才培养、科学研究、社会服务等任务。<br>\n' +
            '　　第四章 教职工<br>\n' +
            '　　第三十七条 学校教职工由教师、其他专业技术人员、管理人员和工勤技能岗位人员组成。<br>\n' +
            '　　教师是学校的办学主体，学校尊重和爱护人才，维护学术民主与学术自由，为教师和科研人员开展教学和科研活动、自主进行学术创新提供必要的条件和保障。教师和科研人员应当树立良好的学术道德风尚，学术行为应符合国家和学校规范。<br>\n' +
            '　　第三十八条 教职工享有下列权利：<br>\n' +
            '　　（一）公平使用学校的公共资源、享受工资福利待遇。<br>\n' +
            '　　（二）公平获得自身发展所需的机会和工作条件。<br>\n' +
            '　　（三）在品德、能力和业绩等方面获得公正评价。<br>\n' +
            '　　（四）公平获得各种奖励及荣誉称号。<br>\n' +
            '　　（五）知悉学校改革、建设和发展及其他涉及切身利益的重大事项。<br>\n' +
            '　　（六）通过教职工代表大会或者其他形式，参与学校民主管理，对学校工作提出意见、建议。<br>\n' +
            '　　（七）就职务聘任、福利待遇、评优评奖、纪律处分等事项按程序提出申诉。<br>\n' +
            '　　（八）法律法规和聘用合同、劳动合同规定的其他权利。<br>\n' +
            '　　第三十九条 教职工应履行下列义务：<br>\n' +
            '　　（一）珍惜学校名誉，维护学校权益。<br>\n' +
            '　　（二）坚持育人为本，尊重爱护学生。<br>\n' +
            '　　（三）恪守职业规范，履行岗位职责。<br>\n' +
            '　　（四）遵守学校规章制度，完成聘任合同约定的岗位任务。<br>\n' +
            '　　（五）法律法规和聘用合同、劳动合同规定的其他义务。<br>\n' +
            '　　第四十条 学校根据实际情况，合理设置教师岗位、其他专业技术岗位、管理岗位和工勤岗位，设置各类岗位的高、中、初级类别。在事业编制人员无法满足实际工作需要时，适当聘任非事业编制人员、合同制工作人员。<br>\n' +
            '　　第四十一条 学校对教职工实行下列聘用制度：<br>\n' +
            '　　（一）教师实行教师资格认证和岗位聘任制度。<br>\n' +
            '　　（二）管理人员和其他专业技术人员实行岗位聘用制度。<br>\n' +
            '　　（三）工勤技能岗位人员实行岗位聘用、劳动合同制度。<br>\n' +
            '　　第四十二条 学校依法制定人事管理制度，对各类教职工实行分类管理，定期对教职工进行考核，考核结果作为其聘任、晋升、奖惩、解聘的依据。<br>\n' +
            '　　第四十三条 学校建立教职工发展制度，推进学习型组织建设，构建完善的培训体系，鼓励和支持教师开展国内外学术交流与合作。<br>\n' +
            '　　第四十四条 学校建立统一的奖励和处罚制度，对为学校及社会做出突出贡献的教职工给予表彰、奖励；对违反法律法规、学校规定和聘用合同规定的教职工依法依规进行处理。<br>\n' +
            '　　第四十五条 学校鼓励和支持教职工参加学校的民主管理和监督，对学校的工作提出意见或建议。<br>\n' +
            '　　第四十六条 学校依法建立以校工会为主体的教职工权利救济机构及相应的权利保护机制。建立教职工申诉委员会，对受理的事项进行听证、调查，提出处理意见，维护教职工合法权益。<br>\n' +
            '　　第四十七条 讲座教授、兼职教授、名誉教授、客座教授、访问学者、进修教师等其他教育工作者，在本校从事教学、科研、进修活动期间，依照法律政策规定、学校规定和合同约定，享受相应的权利，履行相应的义务，学校为其提供必要的条件和帮助。<br>\n' +
            '　　第五章 学生及校友<br>\n' +
            '　　第四十八条 学生是指被学校依法录取、取得入学资格，具有学校学籍的受教育者。<br>\n' +
            '　　第四十九条 学生享有下列权利：<br>\n' +
            '　　（一）公平接受学校教育，参加学校培养计划安排的各项活动，平等利用学校提供的公共教育资源。<br>\n' +
            '　　（二）公平获得在国内外学习深造和参加学术文化交流活动的机会。<br>\n' +
            '　　（三）依照法律和学校规定参加合法的社会活动及文体活动，在校内组织、参加学生社团及文化体育等活动。<br>\n' +
            '　　（四）在思想品德、综合素质、学业成绩等方面获得公正评价，公平获得各级各类荣誉称号和奖励，达到学校规定学业标准时获得相应的学历证书、学位证书。<br>\n' +
            '　　（五）按国家及学校规定的标准和程序申请奖学金、助学金、助学贷款，以及勤工助学、困难补助或减免学费等。<br>\n' +
            '　　（六）知悉涉及个人切身利益的事项，参与学校民主管理，对学校教学活动、学术研究、校园文化、管理服务等工作提供意见和建议。<br>\n' +
            '　　（七）就纪律处分和涉及自身利益的相关规定，按程序提出申诉。<br>\n' +
            '　　（八）法律法规和学校规定的其他权利。<br>\n' +
            '　　第五十条 学生应履行下列义务：<br>\n' +
            '　　（一）珍惜学校名誉，维护学校权益。<br>\n' +
            '　　（二）遵守学校制度，恪守行为规范。<br>\n' +
            '　　（三）参加学习实践，完成规定学业。<br>\n' +
            '　　（四）按规定缴纳学费及有关费用，履行助学贷款和助学金的相应义务。<br>\n' +
            '　　（五）法律法规和学校规定的其他义务。<br>\n' +
            '　　第五十一条 学校建立学生事务管理制度，健全学生资助、心理咨询、素质拓展、职业发展等服务体系，为学生学习、成长创造条件。\n' +
            '　　第五十二条 学校建立学生奖励和处罚制度，完善学生考核评价机制，对取得突出成绩和为学校争得荣誉的学生集体和个人进行表彰奖励；对违纪学生给予相应的纪律处分。<br>\n' +
            '　　第五十三条 学校支持学生组织（学生会、研究生会等）在法律、法规和国家相关规定的范围内，按照其章程开展活动。学生可依法向学校申请组织学生社团，学生社团经学校批准成立，在法律允许范围内开展活动，接受学校的领导和管理。<br>\n' +
            '　　第五十四条 学校支持和保障学生参与学校的民主管理和监督，建立和完善学生权利的救济机制，维护学生合法权益。学校成立校学生申诉处理委员会，处理学生的申诉申请。<br>\n' +
            '　　第五十五条 留学生的管理工作，按照国家及学校有关规定执行。<br>\n' +
            '　　第五十六条 对于不具有学校学籍的受教育者，由学校或学校授权相关职能部门依法另行制定相关规定，该类受教育者依据相关规定享有权利、履行义务。<br>\n' +
            '　　第五十七条 学校校友包括在北京信息科技大学及其前身学习或工作过的学生和教职工、被学校授予各种荣誉学位和荣誉职衔的中外各界人士。<br>\n' +
            '　　学校设立校友会。校友会依其章程开展活动，以多种方式联系和服务校友，定期向校友通报学校发展情况与发展设想，听取校友的意见和建议。<br>\n' +
            '　　第六章 经费与条件保障<br>\n' +
            '　　第五十八条 学校经费来源以财政拨款为主、其他多种渠道筹措办学经费为辅。学校的举办者应提供稳定的办学经费和政策保障。<br>\n' +
            '　　学校积极拓展办学经费来源，筹集办学资金，不断加大办学投入；努力节约支出、提高资金使用效益。<br>\n' +
            '　　第五十九条 对各类捐赠，学校充分尊重捐赠者的意愿，本着节俭高效的原则加以使用，确保捐赠目的的实现。<br>\n' +
            '　　第六十条 学校实行统一领导、集中核算、分级管理的财务管理体制。建立健全学校各项财务管理制度，规范学校经济秩序；构建财务监督体系，建立健全经济责任制度、内部审计制度与监督制度，严格控制和管理财务预算，防范财务风险，保障资金安全。<br>\n' +
            '　　第六十一条 学校通过建立健全资产管理制度，按照公平、公正的原则向各内部组织机构分配各类办学资源，加强学校资产管理，提高资源使用效益，实现资产保值增值。<br>\n' +
            '　　第六十二条 学校依照法律法规和学校规定，加强对学校无形资产的管理，维护学校的合法权益和良好形象。<br>\n' +
            '　　第六十三条 学校加强校园基础条件建设，完善基础设施、信息网络、图书、档案等工作，构建社会化的后勤管理和服务保障体系，为师生提供优质、安全、便捷的服务。<br>\n' +
            '　　第七章 校训、校标、校庆日<br>\n' +
            '　　第六十四条 学校的校训是“勤以为学，信以立身”。<br>\n' +
            '　　第六十五条 学校的校标基本构图由“信息科大”汉语拼音的声母组成，标志外圈的四个线条组成的图案代表宇宙的宏观世界，标志的中心图案代表宇宙的微观世界，图案以冷、暖两色调组成，暖色代表活力创新，冷色调代表智慧科技。整个图案寓意北京信息科技大学探索、追求宇宙宏观与微观世界的真理。<br>\n' +
            '　　第六十六条 学校校庆日为5月18日。<br>\n' +
            '　　第八章 附则<br>\n' +
            '　　第六十七条 本章程的制定和修订需经学校教职工代表大会讨论并征求意见、校长办公会议审议通过后，由学校党委会审定，报北京市教育委员会核准，并报教育部备案。经核准后，学校予以公开发布。<br>\n' +
            '　　第六十八条 学校发生分立、合并、终止，或者名称、类别层次、办学宗旨、发展目标、管理体制等发生变化导致本章程全部或部分条款不适用时，依照前款规定的程序进行修订。<br>\n' +
            '　　第六十九条 本章程如需修订，由学校校长或教职工代表大会五分之一以上代表提议，学校党委常委会同意后修订。章程修订案的审核程序依照第六十七条规定执行。<br>\n' +
            '　　第七十条 学校教职工代表大会负责监督本章程的执行情况，依照本章程审查学校内部规章制度、规范性文件，受理对违反本章程的管理行为、办学活动的举报和投诉。<br>\n' +
            '　　第七十一条 本章程由学校党委常委会负责解释。<br>\n' +
            '　　第七十二条 本章程自北京市教育委员会核准之日起生效。\n<div>\n'
        );
        $.getJSON("",function (e) {
            info = e;
        })
    }
    function intro() {
        $('#centerTitle').text("部门");
        $('#centerContent>*').hide();
        var info;
        $('#centerContent').append( '<table style="width:650px"><tr><td  colspan="3"><strong><hr>学校部门介绍<hr></strong></td></tr>\n'+
            '<tr><td><a onclick="show1()">教务处</a></td><td><a onclick="show11()">财务处</a></td><td><a onclick="show111()">宿舍管理处</a></td></tr>\n'+
            '<tr><td><a onclick="show3()">安稳处</a></td><td><a onclick="show33()">网络中心</a></td><td><a onclick="show333()">医务处</a></td></tr>\n'+
            '<tr><td><a onclick="show9()">图书馆</a></td></tr></table>\n'
        );
        $.getJSON("",function (e) {
            info = e;
        })
    }
    function show1() {
        $('#centerTitle').text("教务处");
        $('#centerContent>*').hide();
        var info;
        $('#centerContent').append( '<div><strong>教务处介绍</strong><img src="/StudentMS/img/2.jpg"><hr><strong>教务处职能：</strong><br><span style="color: #0099ff"><a>教务处新生信息导出</a></span><br><span style="color: #0099ff">新生专业课程介绍</span><br><br><span style="color: #0099ff">新生专业学分介绍</span><br><br><span style="color: #0099ff">新生专业基本规划介绍</span><br><br><span style="color: #0099ff">教务处学校新生有关通知</span><br><span style="color: #0099ff">教务处辅导员及班主任浏览及查询</span><br></div>\n'+
            '<a style="float: right" onclick="intro()">返回</a>\n'
        );
        $.getJSON("",function (e) {
            info = e;
        })
    }
    function show11() {
        $('#centerTitle').text("财务处");
        $('#centerContent>*').hide();
        var info;
        $('#centerContent').append( '<div><strong>财务处介绍</strong><hr><strong>财务处职能：</strong><br><span style="color: #0099ff">财务处银行卡激活流程介绍</span><br><span style="color: #0099ff">新生学费缴纳流程介绍</span><br><br><span style="color: #0099ff">餐卡充值流程介绍</span><br><br><span style="color: #0099ff">助学金申请流程介绍</span><br><br><span style="color: #0099ff"><a>助学金申请表导出</a></span><br><span style="color: #0099ff">国家助学贷款表流程介绍</span><br><span style="color: #0099ff"><a>国家助学贷款申请表导出</a></span><br><span style="color: #0099ff">国家助学贷款表流程介绍</span><br><span style="color: #0099ff">绿色通道申请流程介绍</span><br><span style="color: #0099ff"><a>绿色通道申请表导出</a></span><br></div>\n'+
            '<a style="float: right" onclick="intro()">返回</a>\n'
        );
        $.getJSON("",function (e) {
            info = e;
        })
    }
    function show111() {
        $('#centerTitle').text("宿舍管理处");
        $('#centerContent>*').hide();
        var info;
        $('#centerContent').append( '<div><strong>宿舍管理处介绍</strong><hr><strong>宿舍管理处职能：</strong><br><span style="color: #0099ff">新生宿舍规章制度</span><br><span style="color: #0099ff">新生宿舍消防演练流程</span><br><span style="color: #0099ff">缴纳电费</span><br></div>\n'+
            '<a style="float: right" onclick="intro()">返回</a>\n'
        );
        $.getJSON("",function (e) {
            info = e;
        })
    }
    function show3() {
        $('#centerTitle').text("安稳处");
        $('#centerContent>*').hide();
        var info;
        $('#centerContent').append( '<div><strong>安稳处介绍</strong><br><hr><strong>安稳处职能：</strong><br><span style="color: #0099ff">安稳处新生校园安全通知</span><br><span style="color: #0099ff"><br>安稳处新生个人安全演练</span><br>活动是为了深入贯彻落实《消防法》，深化“平安校园”创建成果，进一步提高新生的消防安全意识和应急自救逃生能力而精心组织的。活动由学校安稳处牵头，学生处、研究生部、后勤管理处以及各院系协助配合。演练内容包括应对突发情况，掌握逃生技能、开展自救互救、消防安全“四会”知识（会报火警、会扑灭初期火灾、会逃生、会正确使用基本消防器材）等。 <br><span style="color: #0099ff">安稳处新生节假日个人安全提醒<br></span>紧密结合冬季校园安全和元旦、寒假将至的特点，突出了对关系师生生命财产安全的防火、防盗、防电信诈骗、火灾逃生等安全知识和重点内容的宣传。宣传教育活动采取了悬挂条幅、张贴海报、摆放展板、发放宣传单页相结合的方式进行，向过往师生发放《警方安全提示》宣传单页5000余份。<br><span style="color: #0099ff">新生户籍变更<br></span><span style="color: blue">新生户籍变更介绍</span><br><span style="color:blue"><a>新生户籍表导出</a></span></div>\n'+
            '<a style="float: right" onclick="intro()">返回</a>\n'
        );
        $.getJSON("",function (e) {
            info = e;
        })
    }
    function show33() {
        $('#centerTitle').text("网络中心");
        $('#centerContent>*').hide();
        var info;
        $('#centerContent').append( '<div><strong>网络中心介绍</strong><hr>北京信息科技大学网络管理中心成立于2004年11月，由原北京机械工业学院网络管理中心和原北京信息工程学院网络信息中心合并而成，是学校负责信息化建设以及网络运行维护的直属单位。网络管理中心现有工作人员12人，其中硕士研究生8人，本科4人，平均年龄32岁，是一支充满朝气的年青队伍。目前网络管理中心各项工作运行良好，正在为学校的教学、科研、管理提供着有力的支持。学校网络管理中心协助保障招就办圆满完成全校网上招生和录取工作，“2006、2007年连续荣获CERNET北京高招畅通工程网络保障先进单位”称号。<br>校园网目前主要分成大的四个自治区域，四个区域已用光缆以路由方式互联。核心采用OSPF协议，组成可以快速收敛的骨干环，以增加网络的可靠性，同时也统一了校园网络的CERNET和CHINANET出口；截止 2007年底出口带宽已经达到1000M接入CERNET， 100M接入电信通， 100M接入方宽， 100M接入教委城域网。<br><strong>网络中心职能</strong><br><span style="color: #0099ff">网络中心新生自助缴费流程介绍</span><br>新账号充值，自2012年10月15日起可使用校园的一卡通圈存机上“上网缴费”自助缴纳上网费，新系统将校园一卡通与上网账号进行了绑定，只能给本人上网账号充值。<br><span style="color: #0099ff">网络中心网络报修流程介绍</span><br><span style="color: #0099ff">网络中心校园网络有关通知</span><br></div>\n'+
            '<a style="float: right" onclick="intro()">返回</a>\n'
        );
        $.getJSON("",function (e) {
            info = e;
        })
    }
    function show333() {
        $('#centerTitle').text("医务处");
        $('#centerContent>*').hide();
        var info;
        $('#centerContent').append( '<div><strong>医务处介绍</strong><hr><strong>医务处职能</strong><br><span style="color: #0099ff">医务处规章制度</span><br><span style="color: #0099ff">新生医保办理介绍</span><br><span style="color: #0099ff">新生入学健康检查介绍</span><br><span style="color: #0099ff">新生医疗报销流程介绍</span><br></div>\n'+
            '<a style="float: right" onclick="intro()">返回</a>\n'
        );
        $.getJSON("",function (e) {
            info = e;
        })
    }
    function show9() {
        $('#centerTitle').text("图书馆");
        $('#centerContent>*').hide();
        var info;
        $('#centerContent').append( '<div><strong>图书馆介绍</strong><br>北京信息科技大学图书馆由小营校区图书馆、健翔桥校区图书馆、清河校区图书馆组成。馆舍总面积约9525平方米，阅览座位1180个，纸质图书106万余册，电子图书近178万册，中外文数据库60余个，声像资料3922件，中外文期刊1343种、报纸103种。（数据截至2016年12月）<br>\n' +
            '     图书馆设有办公室、采编部、图书阅览部、流通部、期刊服务部、参考咨询部、特色资源建设部、技术支持部八个部门。<hr><strong>图书馆职能</strong><br><span style="color: #0099ff">图书馆新生借书流程介绍</span><br>1. 先注册成为馆际互借正式用户。点击BALIS馆际互借中心，进入“用户服务系统”，实名注册。管理员会在2天之内审核通过，注册后不必到图书馆确认。<br>\n' +
            '2. 进入读者登录界面即可登录联合检索进行检索，找到所需文献后，点击下方的馆际互借填写申请表单，发送馆际互借请求。<br>\n' +
            '3.   如果出借馆能够满足出借请求，您在2-5个工作日内就可获得所需文献。<br>\n' +
            '4.   当您接到本馆给您发的“文献到馆”通知后，您就可以直接到本馆“馆际互借处”办理取书手续了。<br>\n' +
            '5.   阅览完毕图书归还我校图书馆“馆际互借处”，借期30天。特殊情况依出借馆要求按时归还。<br>\n' +
            '6.   借阅册数为5册，如有过期以及污损图书的现象，按借出馆的有关规定进行处罚。<br><span style="color: #0099ff">图书馆规章制度</span><br>一、校内读者凭本人校园一卡通入馆。卡证不得转借他人使用。<br>\n' +
            '二、严禁在馆内任何地方吸烟、用火。\n' +
            '三、自觉维护馆内秩序，出入图书馆请走规定通道，如遇监测器报警请主动配合工作人员查明原因。<br>\n' +
            '四、保持馆内安静、整洁和安全，不喧哗、不打闹，入馆后主动将通讯设备置于静音状态，不在阅览室内接打电话。<br>\n' +
            '五、严禁携带任何食品、饮料及暖瓶进入图书馆，严禁在阅览室内进食、扔废弃物。<br>\n' +
            '六、进入阅览室前，将个人书包存入存包柜里，或有序存放在阅览室外的存包架上，个人的贵重物品随身携带。馆内禁止隔夜存放各种物品。<br>\n' +
            '七、请勿抢占座位。凡座位只放物品而无人使用超过30分钟者，其他读者有权使用该座位，工作人员对占位物品不负保管责任。本人如离开半小时以上及闭馆时，请将个人的所有物品全部带出。<br>\n' +
            '八、爱护馆内设备、书刊及书标等附件，不得圈画、撕剪、污损、批注等，否则按有关规定处理。<br> \n' +
            '九、出馆时不得将未办理借阅手续的书刊、光盘等文献资料带出图书馆。<br>\n' +
            '十、闭馆前10分钟，原则上不可以进入阅览室。工作人员提前10分钟提醒读者整理自己物品，以保证闭馆前的内部整理工作顺利进行。<br>\n' +
            '十一、如遇突发事件，在图书馆工作人员的疏导下，有序疏散、撤离。<br>\n' +
            '十二、做文明读者，自觉遵守图书馆的各项规章制度，尊重和服从工作人员的管理和指导。</div>\n'+
            '<a style="float: right" onclick="intro()">返回</a>\n'
        );
        $.getJSON("",function (e) {
            info = e;
        })
    }
</script>
</html>