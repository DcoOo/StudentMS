package alpha.studentms.bean;

public class Reply {
	
	/**
	 * 回复id
	 */
	private String id;
	
	/**
	 * 回复用户id
	 */
	private String user_id;
	
	/**
	 * 该回复所属的帖子id
	 */
	private String post_id;
	
	/**
	 * 点赞数量
	 */
	private int star_num;

	/**
	 * 反对数量
	 */
	private int oppose_num;

	/**
	 * 回复内容
	 */
	private String content;

	private String optime;
	
	public Reply() {
	}
	
	/**
	 * 构造回复
	 * @param id
	 * @param user_id
	 * @param post_id
	 * @param star_num
	 * @param oppose_num
	 * @param content
	 */
	public Reply(String id, String user_id, String post_id, int star_num, int oppose_num, String content) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.post_id = post_id;
		this.star_num = star_num;
		this.oppose_num = oppose_num;
		this.content = content;
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

	public String getPost_id() {
		return post_id;
	}

	public void setPost_id(String post_id) {
		this.post_id = post_id;
	}

	public int getStar_num() {
		return star_num;
	}

	public void setStar_num(int star_num) {
		this.star_num = star_num;
	}

	public int getOppose_num() {
		return oppose_num;
	}

	public void setOppose_num(int oppose_num) {
		this.oppose_num = oppose_num;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOptime() {
		return optime;
	}

	public void setOptime(String optime) {
		this.optime = optime;
	}
	
	
	
	
}
