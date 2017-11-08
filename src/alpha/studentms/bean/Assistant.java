package alpha.studentms.bean;

public class Assistant {
	
	
	/*
	 * 辅导员ID
	 */
	private String id;
	/*
	 * 辅导员负责的班级    
	 */
	private String classOfTeacher;
	/*
	 * 教师角色（辅导员， 0 辅导员 ，1 班主任)
	 */
	private int roleOfTeacher = 0;
	/*
	 * 辅导员工号
	 */
	private String number;
	/*
	 * 辅导员收藏夹，放包含帖子ID的json串
	 */
	private String collection;
	
	
	
	/*
	 * get和set方法
	 */
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClassOfTeacher() {
		return classOfTeacher;
	}
	public void setClassOfTeacher(String classOfTeacher) {
		this.classOfTeacher = classOfTeacher;
	}
	public int getRoleOfTeacher() {
		return roleOfTeacher;
	}
	public void setRoleOfTeacher(int roleOfTeacher) {
		this.roleOfTeacher = roleOfTeacher;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getCollection() {
		return collection;
	}
	public void setCollection(String collection) {
		this.collection = collection;
	}
	
	
	
	

}
