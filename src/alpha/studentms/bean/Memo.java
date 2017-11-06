package alpha.studentms.bean;

/**
 * 
 * @author joker
 *	备忘录 
 */
public class Memo {
	private String id;
	private String user;
	private String title;
	private String content;
	
	public Memo() {	}

	/**
	 * @param id
	 * @param user
	 * @param title
	 * @param content
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
