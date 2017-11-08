package alpha.studentms.bean;

/**
 * 
 * @author joker
 *	备忘录 
 */
public class Memo {
	/**
	 * 备忘录的id
	 */
	private String id;
	/**
	 * 与备忘录关联的用户id
	 */
	private String user;
	/**
	 * 备忘录的标题
	 */
	private String title;
	/**
	 * 备忘录内容
	 */
	private String content;
	
	public Memo() {	}

	/**
	 * @param id 备忘录id
	 * @param user 与备忘录关联的用户id
	 * @param title 备忘录标题
	 * @param content 备忘录内容
	 */
	public Memo(String id, String user, String title, String content) {
		super();
		this.id = id;
		this.user = user;
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
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
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
		return "Memo [id=" + id + ", user=" + user + ", title=" + title + ", content=" + content + "]";
	}
}
