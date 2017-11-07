package alpha.studentms.bean;

public class Student {
	
	/**
	 * 学生id
	 */
	private String id;
	
	/**
	 * 所属班级id
	 */
	private String class_id;
	
	/**
	 * 学生身份证号
	 */
	private String id_num;
	
	/**
	 * 民族
	 */
	private String nation;
	
	/**
	 * 学号
	 */
	private String number;
	
	/**
	 * 学生姓名
	 */
	private String name;

	/**
	 * 性别 
	 */
	private String sex;

	/**
	 * 年龄 
	 */

	private String age;

	/**
	 * 邮箱 
	 */
	private String email;
	
	/**
	 * 微信
	 */
	private String wechat;
	
	/**
	 * qq
	 */
	private String qq;
	
	/**
	 * 手机号
	 */
	private String phone;
	
	/**
	 * 家庭住址
	 */
	private String address;
	
	/**
	 * 收藏夹(Json)字段
	 */
	private String collection;
	
		
	/**
	 * 构造学生实体
	 * @param id
	 * @param class_id
	 * @param id_num
	 * @param nation
	 * @param number
	 * @param name
	 * @param sex
	 * @param age
	 * @param email
	 * @param wechat
	 * @param qq
	 * @param phone
	 * @param address
	 * @param collection
	 */
	public Student(String id, String class_id, String id_num, String nation, String number, String name, String sex,
			String age, String email, String wechat, String qq, String phone, String address, String collection) {
		super();
		this.id = id;
		this.class_id = class_id;
		this.id_num = id_num;
		this.nation = nation;
		this.number = number;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.email = email;
		this.wechat = wechat;
		this.qq = qq;
		this.phone = phone;
		this.address = address;
		this.collection = collection;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClass_id() {
		return class_id;
	}

	public void setClass_id(String class_id) {
		this.class_id = class_id;
	}

	public String getId_num() {
		return id_num;
	}

	public void setId_num(String id_num) {
		this.id_num = id_num;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCollection() {
		return collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}
	
	
}
