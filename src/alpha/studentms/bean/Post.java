package alpha.studentms.bean;

public class Post {
	
	/**
	 * 帖子id
	 */
	private String id;
	
	/**
	 * 发帖用户id
	 */
	private String user_id;
	
	/**
	 * 帖子标题
	 */
	private String title;
	
	/**
	 * 帖子内容
	 */
	private String content;
	
	/**
	 * 回复数量
	 */
	private int reply_num;
	
	/**
	 * 发帖时间
	 */
	private String optime;

	
	public Post() {
	}

	/**
	 * 构造帖子
	 * @param id
	 * @param user_id
	 * @param title
	 * @param content
	 * @param reply_num
	 */
	public Post(String id, String user_id, String title, String content, int reply_num) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.title = title;
		this.content = content;
		this.reply_num = reply_num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getReply_num() {
		return reply_num;
	}

	public void setReply_num(int reply_num) {
		this.reply_num = reply_num;
	}
	
	
	public String getOptime() {
		return optime;
	}

	public void setOptime(String optime) {
		this.optime = optime;
	}
	
}
