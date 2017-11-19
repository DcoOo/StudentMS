package alpha.studentms.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;

import alpha.studentms.bean.Assistant;
import alpha.studentms.bean.Student;
import alpha.studentms.service.StudentService;
import alpha.studentms.service.TeacherService;
import alpha.studentms.serviceImple.StudentServiceImple;
import alpha.studentms.serviceImple.TeacherServiceImple;
import alpha.studentms.util.ExcelCYLUtil;
import alpha.studentms.util.ExcelUtils;

public class AssistantOutputCYLInfoController extends HttpServlet{

	public StudentService studentService = new StudentServiceImple();
	public TeacherService teacherService = new TeacherServiceImple();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String assistantID = (String) request.getSession().getAttribute("userId");
		Assistant assistant = teacherService.selectById(assistantID);
		String whClass = assistant.getClassOfTeacher();
		List<Student> result = teacherService.searchAllClass(whClass);
		
		Workbook webBook ;
		OutputStream out = null; 
		
		String fileName = "导出文档";
		
		//System.out.println(request.getParameter("check"));
		
		//是那种？（全部2，团员0，非团员1）  Parameter名称是什么？
		String which = request.getParameter("check");
		if(which.equals("all")){
			webBook = ExcelCYLUtil.creatExcelWithRegisterInfo(result);
			fileName = "所有新生团员信息";
		}
		else if(which.equals("isCheck")){
			List<Student> tempList = new ArrayList<Student>();
			for(int i =0;i<result.size();i++){
				Student tempStudent = new Student();
				tempStudent = result.get(i);
				if(tempStudent.getIs_cyl() == Student.IS_CYL){
					tempList.add(tempStudent);
				}
			}
			webBook = ExcelCYLUtil.creatExcelWithRegisterInfo(tempList);
			fileName = "团员新生";
		}
		else{
			List<Student> tempList = new ArrayList<Student>();
			for(int i =0;i<result.size();i++){
				Student tempStudent = new Student();
				tempStudent = result.get(i);
				if(tempStudent.getIs_cyl() == Student.NOT_CYL){
					tempList.add(tempStudent);
				}
			}
			webBook = ExcelCYLUtil.creatExcelWithRegisterInfo(tempList);
			fileName = "非团员新生";
		}
		
		
		try {  
            out = response.getOutputStream();  
            response.setContentType("application/ms-excel;charset=UTF-8");  
            response.setHeader("Content-Disposition", "attachment;filename="  
                    .concat(String.valueOf(URLEncoder.encode(fileName + ".xls", "UTF-8"))));  
            webBook.write(out);  
        } catch (IOException e) {  
            System.out.println("输出流错误");  
            e.printStackTrace();  
        } finally {  
            out.close();  
        }  
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	
}
