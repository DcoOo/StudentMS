StudentMS
======
2017.11.8
---------
实现了bean，包的路径为alpha.studentms.bean。

实现了DAO，包的路径为alpha.studentms.dao。

建议对以上包中所有内容进行测试。

2017.11.11
----------
对alpha.studentms.bean中的Message.java进行了修改，添加了private Timestamp optime变量及其get和set方法。

对alpha.studentms.dao进行修改，其中，
AssistantDAO.java改用PreparedStatement；
ClassAdvicerDAO.java改用PreparedStatement；
DocumentDAO.java改用PreparedStatement；
增加了DocModelRelDAO.java。

定义了service接口，包路径alpha.studentms.service。

实现了service接口，包路径alpha.studentms.serviceImple。

建议对alpha.studentms.bean和alpha.studentms.dao包中修改内容，alpha.studentms.serviceImple包中的LoginServiceImple.java、MessageServiceImple.java、StudentServiceImple.java进行测试。

建议对登陆功能进行测试，能够按照用户名和密码正常登录（对于输入错误的情况，由于前端缺少对应的提示部分，所以没有体现登陆失败）

建议对alpha.studentms.serviceImple.StudentServiceImple的getStudentByUsername（）方法进行测试（其中username是t_student表中的id_num字段）

建议对待办事务的显示进行测试（由于路径问题，显示有bug，所以希望只对内容进行测试）
