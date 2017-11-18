package alpha.studentms.util;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import alpha.studentms.bean.Student;

public class ExcelCYLUtil {
public static Workbook creatExcelWithRegisterInfo(List<Student> infoList){
		
		Workbook wb=new HSSFWorkbook(); // 定义一个新的工作簿
        Sheet sheet=wb.createSheet();  // 创建第一个Sheet页
        
        Row row1=sheet.createRow(0); // 创建一个行
        Cell cell1=row1.createCell(0); // 创建一个单元格  第1列
        cell1.setCellValue("姓名");  // 给单元格设置值
        Cell cell2=row1.createCell(1); // 创建一个单元格  第1列
        cell2.setCellValue("是否团员");  // 给单元格设置值
        
        for(int i=0;i<infoList.size();i++){
        	Row row=sheet.createRow(i+1); // 创建一个行
        	
        	
        	
        	Student tempStudent = new Student();
        	tempStudent = infoList.get(i);
        	
        	Cell cell3=row.createCell(0); // 创建一个单元格  第1列
            cell3.setCellValue(tempStudent.getName());  // 给单元格设置值
            if(tempStudent.getIs_cyl() == Student.IS_CYL){
            	Cell cell4=row.createCell(1); // 创建一个单元格  第1列
                cell4.setCellValue("是");  // 给单元格设置值
            }
            else{
            	Cell cell4=row.createCell(1); // 创建一个单元格  第1列
                cell4.setCellValue("否");  // 给单元格设置值
            }
            
        }
		
		return wb;
		
	}
	
	
public static Workbook creatExcelWithStudentInfo(List<Student> infoList,List<String> header,List<Boolean> choiceList){
		
		Workbook wb=new HSSFWorkbook(); // 定义一个新的工作簿
        Sheet sheet=wb.createSheet();  // 创建第一个Sheet页
        
        //表头
        Row row1=sheet.createRow(0); // 创建一个行
        for(int j=0;j<header.size();j++){
        	Cell cell3=row1.createCell(j); // 创建一个单元格  第1列
            cell3.setCellValue(header.get(j));  // 给单元格设置值
        }
        
        
        
        
        //表的内容
        for(int i=0;i<infoList.size();i++){
        	Row row=sheet.createRow(i+1); // 创建一个行
        	
        	
        	
        	Student student = new Student();
        	student = infoList.get(i);
        	int counterForCell = 0;
        	int counterForList = 0;
        	if(choiceList.get(counterForList)){
        		Cell cell3=row.createCell(counterForCell); // 创建一个单元格  第1列
                cell3.setCellValue(student.getClass_id());  // 给单元格设置值
                counterForCell++;
        	}
        	counterForList++;
        	
        	if(choiceList.get(counterForList)){
        		Cell cell3=row.createCell(counterForCell); // 创建一个单元格  第1列
                cell3.setCellValue(student.getId_num());  // 给单元格设置值
                counterForCell++;
        	}
        	counterForList++;
        	
        	if(choiceList.get(counterForList)){
        		Cell cell3=row.createCell(counterForCell); // 创建一个单元格  第1列
                cell3.setCellValue(student.getNation());  // 给单元格设置值
                counterForCell++;
        	}
        	counterForList++;
        	
        	if(choiceList.get(counterForList)){
        		Cell cell3=row.createCell(counterForCell); // 创建一个单元格  第1列
                cell3.setCellValue(student.getNumber());  // 给单元格设置值
                counterForCell++;
        	}
        	counterForList++;
        	
        	if(choiceList.get(counterForList)){
        		Cell cell3=row.createCell(counterForCell); // 创建一个单元格  第1列
                cell3.setCellValue(student.getName());  // 给单元格设置值
                counterForCell++;
        	}
        	counterForList++;
        	
        	if(choiceList.get(counterForList)){
        		Cell cell3=row.createCell(counterForCell); // 创建一个单元格  第1列
                if(student.getSex() == 0){
                	cell3.setCellValue("男");  // 给单元格设置值
                }
                else{
                	cell3.setCellValue("女");  // 给单元格设置值
                }
                counterForCell++;
        	}
        	counterForList++;
        	
        	
        	if(choiceList.get(counterForList)){
        		Cell cell3=row.createCell(counterForCell); // 创建一个单元格  第1列
                cell3.setCellValue(student.getAge());  // 给单元格设置值
                counterForCell++;
        	}
        	counterForList++;
        	
        	if(choiceList.get(counterForList)){
        		Cell cell3=row.createCell(counterForCell); // 创建一个单元格  第1列
                cell3.setCellValue(student.getEmail());  // 给单元格设置值
                counterForCell++;
        	}
        	counterForList++;
        	
        	if(choiceList.get(counterForList)){
        		Cell cell3=row.createCell(counterForCell); // 创建一个单元格  第1列
                cell3.setCellValue(student.getWechat());  // 给单元格设置值
                counterForCell++;
        	}
        	counterForList++;
        	
        	if(choiceList.get(counterForList)){
        		Cell cell3=row.createCell(counterForCell); // 创建一个单元格  第1列
                cell3.setCellValue(student.getQq());  // 给单元格设置值
                counterForCell++;
        	}
        	counterForList++;
        	
        	if(choiceList.get(counterForList)){
        		Cell cell3=row.createCell(counterForCell); // 创建一个单元格  第1列
                cell3.setCellValue(student.getPhone());  // 给单元格设置值
                counterForCell++;
        	}
        	counterForList++;
        	
        	
        	if(choiceList.get(counterForList)){
        		Cell cell3=row.createCell(counterForCell); // 创建一个单元格  第1列
                cell3.setCellValue(student.getAddress());  // 给单元格设置值
                counterForCell++;
        	}
        	counterForList++;
        	
        	if(choiceList.get(counterForList)){
        		Cell cell3=row.createCell(counterForCell); // 创建一个单元格  第1列
        		if(student.getRegister() == 0){
        			cell3.setCellValue("未报到");  // 给单元格设置值
        		}
        		else{
        			cell3.setCellValue("已报到");  // 给单元格设置值
        		}
                counterForCell++;
        	}
        	counterForList++;
        	
        	if(choiceList.get(counterForList)){
        		Cell cell3=row.createCell(counterForCell); // 创建一个单元格  第1列
                cell3.setCellValue(student.getEnglish_grade());  // 给单元格设置值
                counterForCell++;
        	}
        	counterForList++;
        	
        	if(choiceList.get(counterForList)){
        		Cell cell3=row.createCell(counterForCell); // 创建一个单元格  第1列
                cell3.setCellValue(student.getClass_id());  // 给单元格设置值
                counterForCell++;
        	}
        	counterForList++;
        	        	
        }
		
		return wb;
		
	} 
}
