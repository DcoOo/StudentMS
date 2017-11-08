package test.alpha.studentms.dao;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import alpha.studentms.bean.Reply;
import alpha.studentms.dao.ReplyDAO;

public class ReplyDAOTest {

	
	private ReplyDAO replyDao = new ReplyDAO();
	
	@Test
	public void test_select_by_id() {
		LinkedList<Reply> list = replyDao.select_by_id("a", ReplyDAO.PRIMARY_ID_CODE);
		System.out.println(list.size());
		for (Reply reply : list){
			System.out.println(reply.getStar_num());
		}
	}
	
	@Test
	public void test_delete_by_id(){
		replyDao.delete_by_id("c", ReplyDAO.POST_ID_CODE);
	}

	@Test
	public void test_select_all(){
		LinkedList<Reply> list = replyDao.select_all();
		assertTrue(list.size() == 5);
	}
	
	@Test
	public void test_delete_all(){
		replyDao.delete_all();
	}
	
	@Test
	public void test_insert(){
		Reply reply = new Reply("a", "b", "c", 123, 34, "content");
		replyDao.insert(reply);
	}
}
