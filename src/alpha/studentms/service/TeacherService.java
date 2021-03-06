package alpha.studentms.service;

import java.util.List;

import alpha.studentms.bean.Assistant;
import alpha.studentms.bean.ClassAdvicer;
import alpha.studentms.bean.Memo;
import alpha.studentms.bean.Message;
import alpha.studentms.bean.ModelDocument;
import alpha.studentms.bean.Post;
import alpha.studentms.bean.Student;

public interface TeacherService {
	
	/**
	 * 添加工作日志
	 */
	void addMemo(String userId,String title,String content);
	
	
	/**
	 * 查询报道情况
	 * 查询一个班级（或整个年级）的已报道学生的信息，传入一个班级（或年级）id，返回一个学生（student）list
	 */
	List<Student> searchRegisterYes(String whClass);
	
	
	/**
	 * 查询报道情况
	 * 查询一个班级（或整个年级）的未报道学生的信息，传入一个班级（或年级）id，返回一个学生（student）list
	 */
	List<Student> searchRegisterNo(String whClass);
	
	
	/**
	 * 查询团员信息
	 * 查询整个年级的团员信息，传入一个年级id，返回一个学生（student）与是否报到的键值对list
	 */
	List<Student> searchCYL(String whClass);
	
	
	/**
	 * 查询整个班级学生信息
	 * 查询一个班级（或整个年级）学生的信息，传入一个班级（或年级）id，返回一个学生（student）list
	 */
	List<Student> searchAllClass(String whClass);
	
	
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
	
	/**
	 * 根据id查询老师
	 */
	Assistant selectById(String id);
	
	ClassAdvicer selectByNum(String num);
	
	
	/**
	 * 根据教师id查询所有日志
	 */
	List<Memo> searchAllMemoById(String id);
	
	
	/**
	 * 查询所有班级列表
	 */
	List<String> searchAllClassId();
	
	
	/**
	 * 获取所有学生信息
	 */
	List<Student> searchAllStudnet();

	/**
	 * 获取到用户收藏的帖子id
	 * @param id
	 * @return
	 */
	List<Post> getCollectPost(String id);
	
	/**
	 * 更新收藏的
	 * @param postIdLists
	 */
	void deleteCollectPost(String teacherId, String postId);
	
	/**
	 * 添加收藏的帖子
	 * @param post
	 */
	boolean addCollectPost(String teacherId, String postId);
	
	/**
	 * 根据教师id获取其工号
	 * @param teacherId
	 * @return
	 */
	String getTeacherNumber(String teacherId);
	
	/**
	 * 根据教师id获取其collection字段
	 * @param userId
	 * @return
	 */
	String getTeacherCollectionByUserId(String userId);
	
	/**
	 * 根据老师工号获取老师
	 * @param number
	 */
	ClassAdvicer getTeacherByTeacherNumber(String number);
	

	/**
	 * 获取所有回复该用户所发布的帖子的回复
	 * @param userId
	 * @return
	 */
	List<String> getReplyInCollection(String userId);
	
	/**
	 * 获取所有辅导员id
	 * @return
	 */
	List<Assistant> getAllAssistants();


}
