package test.alpha.studentms.serviceImple;

import static org.junit.Assert.*;

import org.junit.Test;

import alpha.studentms.bean.Student;
import alpha.studentms.serviceImple.StudentServiceImple;

public class StudentServiceImpleTest {

	private StudentServiceImple studentService = new StudentServiceImple();
	
	@Test
	public void test_get_english_grade() {
		assertTrue(studentService.getEnglishGrade("1") == 48);
	}
	
	@Test
	public void test_get_classid(){
		System.out.println(studentService.getClassId("1"));
		assertTrue(studentService.getClassId("1").equals("1607"));
	}
	
	@Test
	public void test_GatherAndUpdateBasicInfo(){
		Student student = new Student("a", "333", "33", "汉族", "201011451", 
				"段金昊-", Student.MAN, 20, "1851078679@163.com", "d13408463", "15408463", "18510786796", "北京市朝阳区北四环中路", Student.IS_CYL, Student.IS_REIGSTER, "adf", 85, "aa");
		studentService.gatherAndUpdateInfomation(student);
	}
	
	@Test
	public void test_getStudentByUsername(){
		Student student = studentService.getStudentByUsername("3713111996005063110");
		System.out.println(student.getId()+"---");
		assertTrue(student.getId().equals("a"));
	}

}
