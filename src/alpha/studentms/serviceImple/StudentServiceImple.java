package alpha.studentms.serviceImple;

import java.util.List;

import alpha.studentms.bean.Memo;
import alpha.studentms.bean.Student;
import alpha.studentms.service.StudentService;

public class StudentServiceImple implements StudentService{
	

	@Override
	public float getEnglishGrade(String id) {
		return 0;
	}

	@Override
	public String getClassId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void gatherAndUpdateInfomation(Student student) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Memo> getMustMemo(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Memo> getOptionMemo(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Memo> getCustomMemo(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertCustomMemo(Memo memo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertOptionMemo(Memo memo) {
		// TODO Auto-generated method stub
		
	}

}
