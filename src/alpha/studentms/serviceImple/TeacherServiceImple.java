package alpha.studentms.serviceImple;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import alpha.studentms.bean.Assistant;
import alpha.studentms.bean.ClassAdvicer;
import alpha.studentms.bean.Memo;
import alpha.studentms.bean.Message;
import alpha.studentms.bean.ModelDocument;
import alpha.studentms.bean.Student;
import alpha.studentms.dao.AssistantDAO;
import alpha.studentms.dao.ClassAdvicerDAO;
import alpha.studentms.dao.MemoDAO;
import alpha.studentms.dao.MessageDAO;
import alpha.studentms.dao.ModelDocumentDAO;
import alpha.studentms.dao.StudentDAO;
import alpha.studentms.service.TeacherService;
import alpha.studentms.util.UUIDGenerater;

public class TeacherServiceImple implements TeacherService {
	MemoDAO memoDAO = new MemoDAO();
	StudentDAO studentDAO = new StudentDAO();
	MessageDAO messageDAO = new MessageDAO();
	ModelDocumentDAO modelDocumentDAO = new ModelDocumentDAO();
	AssistantDAO assistantDAO = new AssistantDAO();
	ClassAdvicerDAO classAdvicerDAO = new ClassAdvicerDAO();

	@Override
	public void addMemo(String userId,String title,String content) {
		// TODO Auto-generated method stub
		if(!userId.equals("")){
			Memo memo = new Memo();
			memo.setId(UUIDGenerater.getUUID());
			memo.setUser(userId);
			memo.setTitle(title);
			memo.setContent(content);
			memoDAO.insertMeno(memo);
		}
	}

	@Override
	public List<Student> searchRegisterYes(String whClass) {
		// TODO Auto-generated method stub
		List<Student> result = new ArrayList<Student>();
		
		if(!whClass.equals("")){
			List<Student> tempResult = new ArrayList<Student>();
			tempResult.addAll(studentDAO.select_by_class_id(whClass));
			
			Iterator<Student> iterator = tempResult.iterator();
			while(iterator.hasNext()){
				Student student = iterator.next();
				if(student.getRegister() == 1){
					result.add(student);
				}
			}
		}
		
		return result;
	}

	@Override
	public List<Student> searchCYL(String whClass) {
		// TODO Auto-generated method stub
        List<Student> result = new ArrayList<Student>();
		
		if(!whClass.equals("")){
			List<Student> tempResult = new ArrayList<Student>();
			tempResult.addAll(studentDAO.select_by_class_id(whClass));
			
			Iterator<Student> iterator = tempResult.iterator();
			while(iterator.hasNext()){
				Student student = iterator.next();
				if(student.getIs_cyl() == 1){
					result.add(student);
				}
			}
		}
		
		return result;
	}

	@Override
	public void outputInfor(String classId, List<String> condition) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void releaseMessage(Message message, ModelDocument modelDocument) {
		if((message != null) && (modelDocument != null)){
			messageDAO.insertMessage(message);
			modelDocumentDAO.insertModelDocument(modelDocument);
		} else if (message != null && modelDocument == null) {
			messageDAO.insertMessage(message);
		} else {
			modelDocumentDAO.insertModelDocument(modelDocument);
		}
		
	}

	@Override
	public List<Student> searchRegisterNo(String whClass) {
		// TODO Auto-generated method stub
        List<Student> result = new ArrayList<Student>();
		
		if(!whClass.equals("")){
			List<Student> tempResult = new ArrayList<Student>();
			tempResult.addAll(studentDAO.select_by_class_id(whClass));
			
			Iterator<Student> iterator = tempResult.iterator();
			while(iterator.hasNext()){
				Student student = iterator.next();
				if(student.getRegister() == 0){
					result.add(student);
				}
			}
		}
		
		return result;
	}

	@Override
	public List<Student> searchAllClass(String whClass) {
		// TODO Auto-generated method stub
        List<Student> result = new ArrayList<Student>();
		
		if(!whClass.equals("")){
			result.addAll(studentDAO.select_by_class_id(whClass));
		}
		
		return result;
	}

	@Override
	public Assistant selectById(String id) {
		// TODO Auto-generated method stub
		Assistant assistant = new Assistant();
		
		if(!id.equals("")){
			assistant = assistantDAO.findByID(id);
		}
		
		return assistant;
	}

	@Override
	public ClassAdvicer selectByNum(String num) {
		ClassAdvicer classAdvicer;
		classAdvicer = classAdvicerDAO.findByNumber(num);
		return classAdvicer;
	}

	@Override
	public List<Memo> searchAllMemoById(String id) {
		// TODO Auto-generated method stub
		List<Memo> memos = new ArrayList<Memo>();
		memos = memoDAO.searchAllMemoByUser(id);
		return memos;
	}
}
