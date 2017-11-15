package alpha.studentms.service;

import java.util.List;

import alpha.studentms.bean.Memo;
import alpha.studentms.bean.Student;

public interface StudentService {
	
	/**
	 * 按照用户登录名查询学生信息
	 * @param username
	 * @return
	 */
	Student getStudentByUsername(String username);

	/**
	 * 查询英语成绩
	 * @param id
	 * 			用户id
	 * @return
	 */
	float getEnglishGrade(String id);
	
	
	/**
	 * 查询班级号
	 * @param id
	 * 			用户id
	 * @return
	 */
	String getClassId(String id);
	
	
	/**
	 * 更新或者采集基本信息，其中基本信息包括以下：
	 * nation、name、sex、age、email、wechat、qq、
	 * phone、address、isCYL、register、englishgrade
	 * @param student 更新数据后的学生bean
	 */
	void gatherAndUpdateInfomation(Student student);
	
	/**
	 * 获取必做任务列表
	 * @param id 
	 * 			用户id
	 * @return
	 */
	List<Memo> getMustMemo(String id);
	
	/**
	 * 获取选座任务列表
	 * @param id
	 * 			用户id
	 * @return
	 */
	List<Memo> getOptionMemo(String id);
	
	/**
	 * 获取自定义任务列表
	 * @param id
	 * 			用户id
	 * @return
	 */
	List<Memo> getCustomMemo(String id);
	
	/**
	 * 添加自定义任务
	 * @param memo
	 * 			新的自定义任务bean
	 */
	void insertCustomMemo(Memo memo);
	
	/**
	 * 添加选做任务
	 * @param memo
	 * 			新的选做任务
	 */
	void insertOptionMemo(Memo  memo);
	
	/**
	 * 获取学生班主任
	 * @param student_id
	 * @return
	 */
	String getTeacherId(String student_id);
	
	
	
}
