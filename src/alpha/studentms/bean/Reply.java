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
}
