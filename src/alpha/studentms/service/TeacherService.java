package alpha.studentms.service;

import java.util.List;
import alpha.studentms.bean.Message;
import alpha.studentms.bean.ModelDocument;
import alpha.studentms.bean.Student;

public interface TeacherService {
	
	/**
	 * 添加工作日志
	 */
	void addMemo(String userId);
	
	
	/**
	 * 查询报道情况
	 * 查询一个班级（或整个年级）的报道信息，传入一个班级（或年级）id，返回一个学生（student）list
	 */
	List<Student> searchRegister(String whClass);
	
	
	/**
	 * 查询团员信息
	 * 查询整个年级的团员信息，传入一个年级id，返回一个学生（student）与是否报到的键值对list
	 */
	List<Student> searchCYL(String whClass);
	
	
	/**
	 * 筛选导出新生信息
	 * 输入班级（或年级）id和筛选条件，返回?
	 * 其中，筛选条件用list的形式表示
	 */
	void outputInfor(String classId,List<String> condition);
	
	
	/**
	 * 发布通知
	 * 输入Message，无返回
	 */
	void releaseMessage(Message message,ModelDocument modelDocument);
	
	

}
