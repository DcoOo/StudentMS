package alpha.studentms.serviceImple;

import java.util.ArrayList;
import java.util.List;

import alpha.studentms.bean.Memo;
import alpha.studentms.bean.Student;
import alpha.studentms.dao.MemoDAO;
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
	
	/**
	 * constructor
	 */
	public StudentServiceImple() {
		studentDAO = new StudentDAO();
		memoDAO = new MemoDAO();
		stableMemoDAO = new StableMemoDAO();
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
	public List<Student> getAllStudentInfo(String teacherClass) {
		// TODO Auto-generated method stub
		List<Student> result = new ArrayList<Student>();
		result = studentDAO.select_all();
		return result;
	}

}
