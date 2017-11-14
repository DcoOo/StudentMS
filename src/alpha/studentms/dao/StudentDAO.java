package alpha.studentms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import alpha.studentms.bean.Student;
import alpha.studentms.util.JdbcUtils;
import alpha.studentms.util.JsonParser;

public class StudentDAO {

	
	/**
	 * id code
	 */
	public static final int PRIMARY_ID_CODE = 0;
	public static final int USERNAME_CODE = 1;
	public static final int STUDENT_NUMBER_CODE = 2;
	
	/**
	 * select by class id
	 */
	public static String SELECT_BY_CLASS_ID = "SELECT * FROM t_student WHERE fk_class = ?";
	
	/**
	 * select by id primary key id, student number
	 */
	public static String SELECT_BY_ID = "SELECT * FROM t_student WHERE %s = ?";
	
	/**
	 * select all the students
	 */
	public static String SELECT_ALL = "SELECT * FROM t_student";

	/**
	 * select collection by student id
	 */
	public static String SELECT_COLLECTION_BY_ID = "SELECT collection FROM t_student WHERE pk_id = ?";
	
	/**
	 * select otherinfo by student id
	 */
	public static String SELECT_OTHER_INFO_BY_ID = "SELECT otherinfo FROM t_student WHERE pk_id = ?";

	/**
	 * delete all student
	 */
	public static String DELETE_ALL = "DELETE FROM t_student";
	
	/**
	 * 获取班级ID
	 */
	public static String GET_CLASS_ID = "SELECT fk_class FROM t_student WHERE pk_id = ?";
	
	/**
	 * 获取老师ID
	 */
	public static String GET_TEACHER_ID = "SELECT fk_teacher FROM t_class WHERE pk_id = ?";
	
	/**
	 * insert student
	 */
	public static String INSERT = "INSERT INTO t_student "
			+ "(pk_id, fk_class, id_num, nation, number, name, sex, age, email, wechat, qq, phone, address, isCYL, register, englishgrade)"
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	/**
	 * update badic info
	 */
	public  static String UPDATE_BASIC_INFO = "UPDATE t_student "
			+ "SET nation = ?, name = ?, sex = ?, age = ?, email = ?, wechat = ?, qq = ?, phone = ?, address = ?, isCYL = ?, englishgrade = ?"
			+ "WHERE id_num = ?";

	/**
	 * update collection
	 */
	public static String UPDATE_COLLECTION_BY_ID = "UPDATE t_student SET collection = ? WHERE pk_id = ?";
	
	/**
	 * update other info
	 */
	public static String UPDATE_OTHER_INFO = "UPDATE t_student SET otherinfo = ? WHERE pk_id = ?";

	
	
	
	
	/**
	 * preparedstatement
	 */
	private PreparedStatement preState_select_by_id;
	private PreparedStatement preState_select_collection_by_id;
	private PreparedStatement preState_select_otherinfo_by_id;
	private PreparedStatement preState_select_all;
	private PreparedStatement preState_delete_all;
//	private PreparedStatement preState_insert;
	private PreparedStatement preState_update_basic_info_by_id;
	private PreparedStatement preState_update_other_info_by_id;
	private PreparedStatement preState_update_collection_by_id;
	private PreparedStatement preState_select_by_class_id;
	private PreparedStatement preState_get_class_id;
	private PreparedStatement preState_get_teacher_id;
	
	/**
	 * connection
	 */
	private Connection connection;
	
	/**
	 * constructor
	 */
	public StudentDAO(){
		connection = JdbcUtils.getConnection();
		// initialize preparedstatement
		try {
			preState_select_all = connection.prepareStatement(SELECT_ALL);
			preState_select_otherinfo_by_id = connection.prepareStatement(SELECT_OTHER_INFO_BY_ID);
			preState_select_collection_by_id = connection.prepareStatement(SELECT_COLLECTION_BY_ID);
			preState_select_by_class_id = connection.prepareStatement(SELECT_BY_CLASS_ID);
			preState_delete_all = connection.prepareStatement(DELETE_ALL);
//			preState_insert = connection.prepareStatement(INSERT);
			preState_update_basic_info_by_id = connection.prepareStatement(UPDATE_BASIC_INFO);
			preState_update_other_info_by_id = connection.prepareStatement(UPDATE_OTHER_INFO);
			preState_update_collection_by_id = connection.prepareStatement(UPDATE_COLLECTION_BY_ID);
			preState_get_class_id = connection.prepareStatement(GET_CLASS_ID);
			preState_get_teacher_id = connection.prepareStatement(GET_TEACHER_ID);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * select students by class id
	 * @param class_id
	 * @return
	 */
	public List<Student> select_by_class_id(String class_id){
		try {
			List<Student> list = new LinkedList<>();
			preState_select_by_class_id.setString(1, class_id);
			ResultSet set = preState_select_by_class_id.executeQuery();
			while (set.next()) {
				list.add(resultset2student(set));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * select by id
	 * @param id
	 * @param code
	 * @return
	 */
	public Student select_by_id(String id, int code){
		switch (code) {
		case StudentDAO.PRIMARY_ID_CODE:
			SELECT_BY_ID = String.format(SELECT_BY_ID, "pk_id");
			break;
		case StudentDAO.STUDENT_NUMBER_CODE:
			SELECT_BY_ID = String.format(SELECT_BY_ID, "number");
			break;
		case StudentDAO.USERNAME_CODE:
			SELECT_BY_ID = String.format(SELECT_BY_ID, "id_num");
		}
		try {
//			System.out.println(SELECT_BY_ID);
			preState_select_by_id = connection.prepareStatement(SELECT_BY_ID);
			preState_select_by_id.setString(1, id);
			System.out.println(preState_select_by_id.toString());
			ResultSet set = preState_select_by_id.executeQuery();
			if (set.next()) {
				return resultset2student(set);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * select all students
	 * @return
	 */
	public List<Student> select_all(){
		
		try {
			LinkedList<Student> list = new LinkedList<>();
			ResultSet set = preState_select_all.executeQuery();
			while (set.next()) {
				list.add(resultset2student(set));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * select collection by id
	 * @param id
	 * @return
	 * a array with the id of post
	 */
	public List<Object> select_collection_by_id(String id){
		try {
			preState_select_collection_by_id.setString(1, id);
			ResultSet set = preState_select_collection_by_id.executeQuery();
			if (set.next()) {
				return JsonParser.toList(set.getString("collection"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * select otherinfo by id
	 * @param id
	 * @return
	 */
	public Map<String, Object> select_other_info(String id){
		try {
			preState_select_otherinfo_by_id.setString(1, id);
			ResultSet set = preState_select_otherinfo_by_id.executeQuery();
			if (set.next()) {
				return JsonParser.toMap(set.getString("otherinfo"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * delete all
	 */
	public void delete_all(){
		try {
			preState_delete_all.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * update badic info by id
	 * @param student
	 */
	public void update_basic_info_by_id(Student student){
		try {
			preState_update_basic_info_by_id.setString(1, student.getNation());
			preState_update_basic_info_by_id.setString(2, student.getName());
			preState_update_basic_info_by_id.setInt(3, student.getSex());
			preState_update_basic_info_by_id.setInt(4, student.getAge());
			preState_update_basic_info_by_id.setString(5, student.getEmail());
			preState_update_basic_info_by_id.setString(6, student.getWechat());
			preState_update_basic_info_by_id.setString(7, student.getQq());
			preState_update_basic_info_by_id.setString(8, student.getPhone());
			preState_update_basic_info_by_id.setString(9, student.getAddress());
			preState_update_basic_info_by_id.setInt(10, student.getIs_cyl());
			preState_update_basic_info_by_id.setFloat(11, student.getEnglish_grade());
			preState_update_basic_info_by_id.setString(12, student.getId_num());
			preState_update_basic_info_by_id.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * update other info by id
	 * @param other_info
	 * @param id
	 */
	public void update_other_info_by_id(String other_info, String id){
		try {
			preState_update_other_info_by_id.setString(1, other_info);
			preState_update_other_info_by_id.setString(2, id);
			preState_update_other_info_by_id.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * update collection by id
	 * @param collection
	 * @param id
	 */
	public void update_collection_by_id(String collection, String id){
		try {
			preState_update_collection_by_id.setString(1, collection);
			preState_update_collection_by_id.setString(2, id);
			preState_update_collection_by_id.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 插入学生1
	 */
	
	
	/**
	 * convert from resultset to student bean
	 * @param set
	 * @return
	 */
	private Student resultset2student(ResultSet set){
		try {
			String pk_id = set.getString("pk_id");
			String class_id = set.getString("fk_class");
			String id_num = set.getString("id_num");
			String nation = set.getString("nation");
			String number = set.getString("number");
			String name = set.getString("name");
			int sex = set.getInt("sex");
			int age = set.getInt("age");
			String email = set.getString("name");
			String wechat = set.getString("name");
			String qq = set.getString("name");
			String phone = set.getString("name");
			String address = set.getString("name");
			int isCYL = set.getInt("isCYL");
			int register = set.getInt("register");
			String other_info = set.getString("otherinfo");
			float english_grade = set.getFloat("englishgrade");
			String collection = set.getString("collection");
			return new Student(pk_id, class_id, id_num, nation, number, name, sex, age, email, wechat, qq, phone, 
					address, isCYL, register, other_info, english_grade, collection);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取班级id
	 * @param studentId
	 * @return
	 */
	public String select_class_id(String studentId){
		try {
			preState_get_class_id.setString(1, studentId);
			ResultSet set = preState_get_class_id.executeQuery();
			if (set.next()) {
				return set.getString("fk_class");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取老师id
	 * @param classId
	 * @return
	 */
	public String select_teacher_id(String classId){
		ResultSet set;
		try {
			preState_get_teacher_id.setString(1, classId);
			set = preState_get_teacher_id.executeQuery();
			if (set.next()) {
				return set.getString("fk_teacher");
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	
	
	
	
	
	
}
