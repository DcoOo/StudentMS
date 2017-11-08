package alpha.studentms.bean;

import java.util.List;


public class Collection {
	
	
	/**
	 * 发表的帖子
	 */
	private List<Post> release_posts;
	
	/**
	 * 收藏的帖子
	 */
	private List<Post> collect_posts;
	
	/**
	 * 所有回复
	 */
	private List<Reply> replys;
	
	/**
	 * 学生的实体文档和老师的模板文档
	 */
	private List documents;

	/**
	 * 构造方法
	 * @param release_posts
	 * @param collect_posts
	 * @param replys
	 * @param documents
	 */
	public Collection(List<Post> release_posts, List<Post> collect_posts, List<Reply> replys, List documents) {
		super();
		this.release_posts = release_posts;
		this.collect_posts = collect_posts;
		this.replys = replys;
		this.documents = documents;
	}

	public List<Post> getRelease_posts() {
		return release_posts;
	}

	public void setRelease_posts(List<Post> release_posts) {
		this.release_posts = release_posts;
	}

	public List<Post> getCollect_posts() {
		return collect_posts;
	}

	public void setCollect_posts(List<Post> collect_posts) {
		this.collect_posts = collect_posts;
	}

	public List<Reply> getReplys() {
		return replys;
	}

	public void setReplys(List<Reply> replys) {
		this.replys = replys;
	}

	public List getDocuments() {
		return documents;
	}

	public void setDocuments(List documents) {
		this.documents = documents;
	}
	
	
	
}
