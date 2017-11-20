package test.alpha.studentms.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import alpha.studentms.bean.Memo;
import alpha.studentms.dao.StableMemoDAO;
import alpha.studentms.util.UUIDGenerater;

/**
 * StableMemoDAO的测试类
 * 
 * @author joker
 *
 */
public class StableMemoDAOTest {

	StableMemoDAO stableMemoDAO;

	@Before
	public void init() {
		stableMemoDAO = new StableMemoDAO();
	}


	@Test
	public void testInsertStableMemo() {
		System.out.println(UUIDGenerater.getUUID());
//		stableMemoDAO.insertStableMemo("2", "1", "2");
	}

	@Test
	public void testDeleteStableMemoById() {
		stableMemoDAO.deleteStableMemoById("2", "1");
	}

	@Test
	public void testGetAllCompulsoryMemo() {
		List<Memo> list = new ArrayList<>();
		list = stableMemoDAO.getAllCompulsoryMemo("2");
		System.out.println(list);
	}

	@Test
	public void testGetAllOptionalMemo() {
		List<Memo> list = new ArrayList<>();
		list = stableMemoDAO.getAllOptionalMemo("2");
		System.out.println(list);
	}

	@After
	public void close() {
		stableMemoDAO.close();
	}
}