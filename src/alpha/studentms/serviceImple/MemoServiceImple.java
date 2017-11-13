package alpha.studentms.serviceImple;

import java.util.List;

import alpha.studentms.bean.Memo;
import alpha.studentms.bean.Message;
import alpha.studentms.dao.MemoDAO;
import alpha.studentms.dao.StableMemoDAO;
import alpha.studentms.service.MemoService;

public class MemoServiceImple implements MemoService{
	
	/**
	 * DAO
	 */
	private MemoDAO memoDAO;
	private StableMemoDAO stableMemoDAO;
	
	/**
	 * constructor
	 */
	public MemoServiceImple() {
		memoDAO = new MemoDAO();
		stableMemoDAO = new StableMemoDAO();
		
	}

	@Override
	public List<Message> getMemoeByUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void addMemoByUserId(String userId, int type, Memo memo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMemoByMemoId(String memoId, String userId, int type) {
		// TODO Auto-generated method stub
		switch (type) {
		case MemoService.CUSTOM_MEMO_CODE:
			memoDAO.deleteMemoById(memoId);
			break;
		case MemoService.OPTION_MEMO_CODE:
			stableMemoDAO.deleteStableMemoById(memoId, userId);
			break;
		case MemoService.MUST_MEMO_CODE:
			stableMemoDAO.deleteStableMemoById(memoId, userId);
			
			break;
		case MemoService.TEACHER_LOG:
			memoDAO.deleteMemoById(memoId);
			break;

		}
		
	}

}
