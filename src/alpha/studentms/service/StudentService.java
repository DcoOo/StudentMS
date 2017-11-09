package alpha.studentms.service;

import java.util.List;

import alpha.studentms.bean.Memo;
import alpha.studentms.bean.Student;

public interface StudentService {

	/**
	 * select english grade
	 */
	float getEnglishGrade(String id);
	
	/**
	 * select class
	 */
	String getClassId(String id);
	
	/**
	 * select english grade and class
	 */
	
	/**
	 * get basic information
	 */
	void gatherAndUpdateInfomation(Student student);
	
	/**
	 * get must memo
	 */
	List<Memo> getMustMemo(String id);
	
	/**
	 * get option memo
	 */
	List<Memo> getOptionMemo(String id);
	
	/**
	 * get custonm memo
	 */
	List<Memo> getCustomMemo(String id);
	
	/**
	 * add custom memo
	 */
	void insertCustomMemo(Memo memo);
	
	/**
	 * add option memo
	 */
	void insertOptionMemo(Memo  memo);
	
	
	
}
