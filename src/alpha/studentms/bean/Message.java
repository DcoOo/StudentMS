package alpha.studentms.bean;

/**
 * 
 * @author joker
 *	通知
 */
public class Message {
	private String id;
	private String teacher;
	private String title;
	private String content;
	
	public Message() {}

	/**
	 * @param id
	 * @param teacher
	 * @param title
	 * @param cntent
	 */
	public Message(String id, String teacher, String title, String content) {
		super();
		this.id = id;
		this.teacher = teacher;
		this.title = title;
		this.content = content;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Message [id=" + id + ", teacher=" + teacher + ", title=" + title + ", cntent=" + content + "]";
	}
}
