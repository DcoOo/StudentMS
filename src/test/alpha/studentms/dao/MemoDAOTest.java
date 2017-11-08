package test.alpha.studentms.dao;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import alpha.studentms.bean.Memo;
import alpha.studentms.dao.MemoDAO;

/**
 * MemoDAO的单元测试类
 * 
 * @author joker
 *
 */
public class MemoDAOTest {
	MemoDAO memoDAO;

	@Before
	public void init() {
		memoDAO = new MemoDAO();
	}

	@Test
	public void testInsertMeno() {
		Memo memo = new Memo();
		memo.setId("2");
		memo.setUser("2");
		memo.setTitle("Java");
		memo.setContent("Java");
		memoDAO.insertMeno(memo);
	}

	@Test
	public void testDeleteMemoById() {
		String id = "2";
		memoDAO.deleteMemoById(id);
	}

	@Test
	public void testSearchMemoById() {
		String id = "2";
		Memo memo = memoDAO.searchMemoById(id);
		System.out.println(memo);
	}

	@Test
	public void testUpdateMemoContentById() {
		String id = "2";
		String content = "C++";
		memoDAO.updateMemoContentById(id, content);
	}

	@Test
	public void testSearchAllMemoByUser() {
		String user = "2";
		List<Memo> list = memoDAO.searchAllMemoByUser(user);
		System.out.println(list);
	}

	@After
	public void close() {
		memoDAO.releaseConnection();
	}

}
