package alpha.studentms.bean;

import java.sql.Timestamp;

public class Document {
	
	/*
	 * 实体文档的ID
	 */
	private String id;
	/*
	 * 上传实体文档的学生ID    
	 */
	private String student;
	/*
	 * 实体文档的存储地址
	 */
	private String address;
	/*
	 * 实体文档的名称
	 */
	private String name;
	/*
	 * 实体文档的上传的时间
	 */
	private Timestamp opTime;
	

	public Document(){
		
	}
	
	
    public Document(String id,String student,String address,String name){
		this.id = id;
		this.student = student;
		this.address = address;
		this.name = name;
	}
	
	
	/*
	 * get和set方法
	 */
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStudent() {
		return student;
	}
	public void setStudent(String student) {
		this.student = student;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getOpTime() {
		return opTime;
	}
	public void setOpTime(Timestamp opTime) {
		this.opTime = opTime;
	}
	

}
