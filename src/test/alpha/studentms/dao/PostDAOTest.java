package test.alpha.studentms.dao;

import static org.junit.Assert.*;

import java.util.LinkedList;

import alpha.studentms.bean.Post;
import alpha.studentms.dao.PostDAO;

import org.junit.Test;


public class PostDAOTest {

	private PostDAO postDao = new PostDAO();
	
	@Test
	public void test_select_by_id() {
		Post post = postDao.select_by_id("abc");
		assertTrue(post.getTitle().equals("今天是个好日子"));
	}
	
	@Test
	public void test_select_all(){
		LinkedList<Post> list = postDao.select_all();
		assertTrue(list.size() == 2);
	}
	
	@Test
	public void test_delete_by_Id(){
		assertTrue(postDao.delete_by_id("ddd"));
	}
	
	@Test
	public void test_delete_all(){
		assertTrue(postDao.delete_all());
	}
	
	@Test
	public void test_insert(){
		Post post = new Post("", "123", "abc", "cdfa", 500);
		postDao.insert(post);
		LinkedList<Post> list = postDao.select_all();
		assertTrue(list.get(0).getUser_id().equals("123"));
	}
	
	@Test
	public void test_update_by_id(){
		Post post = new Post("E180F91F0BC54A5BB34AF1865F411FFB", "123", "ccc", "content", 999);
		int i = postDao.update_by_Id(post);
		assertTrue(i == 1);
	}
	
	@Test
	public void test_select_by_userid(){
		LinkedList<Post> list = postDao.select_by_userid("123");
		assertTrue(list.size() == 4);
	}
}
