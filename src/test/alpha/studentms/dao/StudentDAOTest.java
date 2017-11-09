package test.alpha.studentms.dao;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import alpha.studentms.bean.Student;
import alpha.studentms.dao.StudentDAO;

public class StudentDAOTest {

	private StudentDAO studentDao = new StudentDAO();
	
	
	@Test
	public void test_select_by_id() {
		Student student = studentDao.select_by_id("aaa", StudentDAO.PRIMARY_ID_CODE);
		System.out.println(student.getEnglish_grade());
	}
	
	@Test
	public void test_select_all(){
		List<Student> list = studentDao.select_all();
		System.out.println(list.size());
		System.out.println(list.get(0).getEnglish_grade());
		assertTrue(list.size() == 4);
	}
	
	@Test
	public void test_other_info2map(){
		Map<String, Object> map = studentDao.select_other_info("1");
		System.out.println(map.keySet());
	}
	
	@Test
	public void test_select_collection_by_id(){
		List<Object> list = studentDao.select_collection_by_id("1");
		System.out.println(list.size());
	}
	
	@Test
	public void test_update_collection_by_id(){
		studentDao.update_collection_by_id("collection", "a");
	}
	
	@Test
	public void test_update_other_info_by_id(){
		studentDao.update_other_info_by_id("otherinfo", "a");
	}
	
	@Test
	public void test_update(){
		Student student = new Student("a", "333", "3333", "汉族", "2014011451", 
				"段金昊", Student.MAN, 21, "18510786796@163.com", "d153408463", "153408463", "18510786796", "北京市朝阳区北四环中路", Student.IS_CYL, Student.IS_REIGSTER, "adf", 90, "aa");
		studentDao.update_basic_info_by_id(student);
	}
	
	@Test
	public void test_select_by_class_id(){
		List<Student> list = studentDao.select_by_class_id("2");
		assertTrue(list.size() == 4);
	}

}
