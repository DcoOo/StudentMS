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

建议对alpha.studentms.bean和alpha.studentms.dao包中修改内容及alpha.studentms.serviceImple包中所有内容进行测试。
