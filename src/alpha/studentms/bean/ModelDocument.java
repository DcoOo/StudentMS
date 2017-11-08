package alpha.studentms.bean;

/**
 * 
 * @author joker
 *	模板文档
 */
public class ModelDocument {
	/**
	 * 模板文档的id
	 */
	private String modelDoc;
	/**
	 * 与模板文档关联的教师id
	 */
	private String teacher;
	/**
	 * 与模板文档关联的通知id
	 */
	private String message;
	/**
	 * 模板文档的名字
	 */
	private String name;
	
	public ModelDocument() {}

	/**
	 * @param modelDoc 模板文档的id
	 * @param teacher 与模板文档关联的教师id
	 * @param message 与模板文档关联的通知id
	 * @param name 模板文档的名字
	 */
	public ModelDocument(String modelDoc, String teacher, String message, String name) {
		super();
		this.modelDoc = modelDoc;
		this.teacher = teacher;
		this.message = message;
		this.name = name;
	}

	/**
	 * @return the modelDoc
	 */
	public String getModelDoc() {
		return modelDoc;
	}

	/**
	 * @param modelDoc the modelDoc to set
	 */
	public void setModelDoc(String modelDoc) {
		this.modelDoc = modelDoc;
	}

	/**
	 * @return the teacher
	 */
	public String getTeacher() {
		return teacher;
	}

	/**
	 * @param teacher the teacher to set
	 */
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ModelDocument [modelDoc=" + modelDoc + ", teacher=" + teacher + ", message=" + message + ", name="
				+ name + "]";
	}
}
