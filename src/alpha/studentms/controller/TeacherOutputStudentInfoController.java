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
import alpha.studentms.serviceImple.StudentServiceImple;
import alpha.studentms.serviceImple.TeacherServiceImple;
import alpha.studentms.util.ExcelUtils;

public class TeacherOutputStudentInfoController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public StudentService studentService = new StudentServiceImple();
	public TeacherServiceImple teacherService = new TeacherServiceImple();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String assistantID = (String) request.getSession().getAttribute("userId");
		//记得改过来Id
		Assistant assistant = teacherService.selectById("123");
		String whClass = assistant.getClassOfTeacher();
		List<Student> result = teacherService.searchAllClass(whClass);
		
		String[] head = request.getParameterValues("");
		
		
		Workbook webBook ;
		OutputStream out = null; 
		
		//Parameter名称是什么？
		String[] class_id = request.getParameterValues("class_id");
		String[] id_num = request.getParameterValues("id_num");
		String[] nation = request.getParameterValues("nation");
		String[] number = request.getParameterValues("number");
		String[] name = request.getParameterValues("name");
		String[] sex = request.getParameterValues("sex");
		String[] age = request.getParameterValues("age");
		String[] email = request.getParameterValues("email");
		String[] wechat = request.getParameterValues("wechat");
		String[] qq = request.getParameterValues("qq");
		String[] phone = request.getParameterValues("phone");
		String[] address = request.getParameterValues("address");
		
		
		List<Boolean> which = new ArrayList<Boolean>();
		List<String> info = new ArrayList<String>();
		
		if(class_id != null){
			which.add(true);
			info.add("班级");
		}
		else{
			which.add(false);
		}
		
		if(id_num != null){
			which.add(true);
			info.add("身份证号");
		}
		else{
			which.add(false);
		}
		
		if(nation != null){
			which.add(true);
			info.add("民族");
		}
		else{
			which.add(false);
		}
		
		if(number != null){
			which.add(true);
			info.add("学号");
		}
		else{
			which.add(false);
		}
		
		if(name != null){
			which.add(true);
			info.add("姓名");
		}
		else{
			which.add(false);
		}
		
		if(sex != null){
			which.add(true);
			info.add("性别");
		}
		else{
			which.add(false);
		}
		
		if(age != null){
			which.add(true);
			info.add("年龄");
		}
		else{
			which.add(false);
		}
		
		if(email != null){
			which.add(true);
			info.add("电子邮箱");
		}
		else{
			which.add(false);
		}
		
		if(wechat != null){
			which.add(true);
			info.add("微信");
		}
		else{
			which.add(false);
		}
		
		if(qq != null){
			which.add(true);
			info.add("QQ");
		}
		else{
			which.add(false);
		}
		
		if(phone != null){
			which.add(true);
			info.add("电话");
		}
		else{
			which.add(false);
		}
		
		if(address != null){
			which.add(true);
			info.add("地址");
		}
		else{
			which.add(false);
		}
		
		which.add(false);
		
		which.add(false);
		
		which.add(false);
		
		
		
		webBook = ExcelUtils.creatExcelWithStudentInfo(result, info, which);
		
		try {  
            out = response.getOutputStream();  
            response.setContentType("application/ms-excel;charset=UTF-8");  
            response.setHeader("Content-Disposition", "attachment;filename="  
                    .concat(String.valueOf(URLEncoder.encode("新生信息" + ".xls", "UTF-8"))));  
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
