package alpha.studentms.serviceImple;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import alpha.studentms.bean.Memo;
import alpha.studentms.bean.Post;
import alpha.studentms.bean.Student;
import alpha.studentms.dao.MemoDAO;
import alpha.studentms.dao.PostDAO;
import alpha.studentms.dao.StableMemoDAO;
import alpha.studentms.dao.StudentDAO;
import alpha.studentms.service.StudentService;
import alpha.studentms.util.UUIDGenerater;

public class StudentServiceImple implements StudentService{
	
	/**
	 * DAO
	 */
	private StudentDAO studentDAO;
	private MemoDAO memoDAO;
	private StableMemoDAO stableMemoDAO;
	private PostDAO postDAO;
	
	/**
	 * constructor
	 */
	public StudentServiceImple() {
		studentDAO = new StudentDAO();
		memoDAO = new MemoDAO();
		stableMemoDAO = new StableMemoDAO();
		postDAO = new PostDAO();
	}

	/**
	 * get english grade
	 */
	@Override
	public float getEnglishGrade(String id) {
		Student student = studentDAO.select_by_id(id, StudentDAO.PRIMARY_ID_CODE);
		if (student != null) {
			return student.getEnglish_grade();
		}
		return -1;
	}

	/**
	 * get class id
	 */
	@Override
	public String getClassId(String id) {
		Student student = studentDAO.select_by_id(id, StudentDAO.PRIMARY_ID_CODE);
		if (student != null) {
			return student.getClass_id();
		}

		return null;
	}

	/**
	 * gather basic info and update basic info
	 */
	@Override
	public void gatherAndUpdateInfomation(Student student) {
		studentDAO.update_basic_info_by_id(student);
	}

	/**
	 * 返回必做任务列表
	 */
	@Override
	public List<Memo> getMustMemo(String id) {
		return stableMemoDAO.getAllCompulsoryMemo(id);
	}

	/**
	 * 返回选做任务列表
	 */
	@Override
	public List<Memo> getOptionMemo(String id) {
		return stableMemoDAO.getAllOptionalMemo(id);
	}

	/**
	 * 返回自定义任务列表
	 */
	@Override
	public List<Memo> getCustomMemo(String id) {
		return memoDAO.searchAllMemoByUser(id);
	}

	/**
	 * 插入自定义任务
	 */
	@Override
	public void insertCustomMemo(Memo memo) {
		// TODO Auto-generated method stub
		memoDAO.insertMeno(memo);
	}

	/**
	 * 插入选做任务
	 */
	@Override
	public void insertOptionMemo(Memo memo) {
		// TODO Auto-generated method stub
		stableMemoDAO.insertStableMemo(UUIDGenerater.getUUID(), memo.getUser(), memo.getId());
	}

	@Override
	public Student getStudentByUsername(String username) {
		return studentDAO.select_by_id(username, StudentDAO.USERNAME_CODE);
	}
	
	@Override
	public String getTeacherId(String student_id) {
		String classId = studentDAO.select_class_id(student_id);
		return studentDAO.select_teacher_id(classId);
	}

	@Override
	public List<Student> getAllStudentInfo(String teacherClass) {
		// TODO Auto-generated method stub
		List<Student> result = new ArrayList<Student>();
		result = studentDAO.select_all();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Post> getCollectPost(String id) {
		List<Post> posts = new LinkedList<>();
		String mapJson = studentDAO.select_collection_by_id(id);
		Map<String, Object> map = new JSONObject(mapJson).toMap();
		for(Object postId : (ArrayList<String>)map.get("collect")){
			Post post = postDAO.select_by_id((String)postId);
			if (post != null) {
				posts.add(post);
			}
		}
		return posts;
	}

	@Override
	public void deleteCollectPost(String postId, String studentId) {
		String listJson = studentDAO.select_collection_by_id(studentId);
		JSONArray array = new JSONArray(listJson);
		for(int i = 0; i < array.length(); i++){
			String tmp = (String)array.get(i);
			if (tmp.equals(postId)) {
				array.remove(i);
			}
		}
		// 更新collect到数据库
		studentDAO.update_collection_by_id(array.toString(), studentId);
	}

	@Override
	public boolean addCollectPost(String studentId, String postId) {
		boolean flag = false;
		String mapJson = studentDAO.select_collection_by_id(studentId);
		JSONObject object = new JSONObject(mapJson);
		Map<String, Object> map = object.toMap();
		@SuppressWarnings("unchecked")
		ArrayList<String> list = (ArrayList<String>) map.get("collect");
		if (list.contains(postId)) {
			// 表示已经收藏过
			return flag;
		}else{
			// 之前没有收藏
			list.add(postId);
			// 收藏夹更新到数据库
			studentDAO.update_collection_by_id(new JSONObject(map).toString(), studentId);
			return true;
		}
		
	}

	@Override
	public Student getStudentById(String userId) {
		return studentDAO.select_by_id(userId, StudentDAO.PRIMARY_ID_CODE);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getReplyInCollection(String userId) {
		String collection = studentDAO.select_collection_by_id(userId);
		JSONObject obj = new JSONObject(collection);
		Map<String, Object> map = obj.toMap();
		return (ArrayList<String>)map.get("reply");
	}

}
