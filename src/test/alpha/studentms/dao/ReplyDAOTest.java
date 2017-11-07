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
		LinkedList<Reply> list = replyDao.select_by_id("c", 2);
		System.out.println(list.size());
		for (Reply reply : list){
			System.out.println(reply.getStar_num());
		}
	}

}
