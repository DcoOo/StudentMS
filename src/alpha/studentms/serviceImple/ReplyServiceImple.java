package alpha.studentms.serviceImple;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import alpha.studentms.bean.Post;
import alpha.studentms.bean.Reply;
import alpha.studentms.bean.Student;
import alpha.studentms.dao.AssistantDAO;
import alpha.studentms.dao.ClassAdvicerDAO;
import alpha.studentms.dao.PostDAO;
import alpha.studentms.dao.ReplyDAO;
import alpha.studentms.dao.StudentDAO;
import alpha.studentms.service.ReplyService;
import alpha.studentms.util.UUIDGenerater;

public class ReplyServiceImple implements ReplyService {
	
	ReplyDAO replyDAO = new ReplyDAO();
	PostDAO postDAO = new PostDAO();
	ClassAdvicerDAO classAdvicerDAO = new ClassAdvicerDAO();
	AssistantDAO assistantDAO = new AssistantDAO();
	StudentDAO studentDAO = new StudentDAO();

	@Override
	public void reply(String user, String postId,String content) {
		// TODO Auto-generated method stub
		if((!user.equals(""))&&(!postId.equals(""))){
			
			Reply reply = new Reply(UUIDGenerater.getUUID(),user,postId,0,0,content);
			replyDAO.insert(reply);
			
			Post post = postDAO.select_by_id(postId);
			int tempNum = post.getReply_num();
			post.setReply_num(tempNum++);
			postDAO.update_by_Id(post);
		}
	}

	@Override
	public void like(String replyId) {
		// TODO Auto-generated method stub
		if(!replyId.equals("")){
			List<Reply> replies = replyDAO.select_by_id(replyId, ReplyDAO.PRIMARY_ID_CODE);
			
			Iterator<Reply> iterator = replies.iterator();
			while(iterator.hasNext()){
				Reply reply = iterator.next();
				int tempNum = reply.getStar_num();
				replyDAO.updateLikeByReplyId(replyId, tempNum++);
			}
		}
	}

	@Override
	public void oppose(String opposeId) {
		// TODO Auto-generated method stub
		if(!opposeId.equals("")){
			List<Reply> replies = replyDAO.select_by_id(opposeId, ReplyDAO.PRIMARY_ID_CODE);
			
			Iterator<Reply> iterator = replies.iterator();
			while(iterator.hasNext()){
				Reply reply = iterator.next();
				int tempNum = reply.getStar_num();
				replyDAO.updateOpposeByReplyId(opposeId, tempNum++);
			}
		}
	}

	@Override
	public List<Reply> searchPost(String condition) {
		// TODO Auto-generated method stub
        List<Reply> result = new ArrayList<Reply>();
		
		
		if(!condition.equals("")){
			/**
			 * 根据空格切割字符串
			 */
			String[] cuted;
			cuted = condition.split(" +");
			/**
			 * 查出所有帖子
			 */
			List<Reply> temp = new ArrayList<Reply>();
			temp = replyDAO.select_all();
			Iterator<Reply> iterator = temp.iterator();
			/**
			 * 记录帖子和包含关键字的数目
			 */
			List<Reply> tempResultReply = new ArrayList<Reply>();
			List<Integer> tempResultNum = new ArrayList<Integer>();
			/**
			 * 遍历帖子，比对关键字，记录数目
			 */
			while(iterator.hasNext()){
				int counter = 0;
				Reply tempReply = iterator.next();
				String rempS = tempReply.getContent();
				
				for(int i=0;i<cuted.length;i++){
					if(rempS.contains(cuted[i])){
						counter++;
					}
				}
				
				tempResultReply.add(tempReply);
				tempResultNum.add(counter);
			}
			
			
			/**
			 * 希尔排序
			 */
			int h = 0;
			int n = tempResultNum.size() / 8;
			while(h <= n){
				h = 3 * h + 1;
			}
			while(h >= 1){
				for(int i=h;i<n;i++){
					int j = i - h;
					int get = tempResultNum.get(i);
					Reply getReply = tempResultReply.get(i);
					while(j >= 0 && tempResultNum.get(j) > get){
						tempResultNum.set(j + h, tempResultNum.get(j));
						tempResultReply.set(j + h, tempResultReply.get(j));
						j = j - h;
					}
					tempResultNum.set(j + h, get);
					tempResultReply.set(j + h, getReply);
				}
				h = (h - 1) / 3;
			}
			
			/**
			 * 将排序后的结果放入到返回列表中
			 */
			result.addAll(tempResultReply);
		}
		
		
		return result;
	}

	@Override
	public void deleteReply(String user, String replyId) {
		// TODO Auto-generated method stub
		if((!user.equals(""))&&(!replyId.equals(""))){
			if((classAdvicerDAO.findByID(user) != null) || (assistantDAO.findByID(user) != null)){
				//回复删除之后，帖子回复数减一
				Reply reply = replyDAO.select_by_id(replyId, ReplyDAO.PRIMARY_ID_CODE).pop();
				Post post= postDAO.select_by_id(reply.getPost_id());
				int tempNum = post.getReply_num();
				post.setReply_num(tempNum--);
				postDAO.update_by_Id(post);
				
				replyDAO.delete_by_id(replyId, ReplyDAO.PRIMARY_ID_CODE);
				
			}
			else{
				Student student = studentDAO.select_by_id(user, StudentDAO.PRIMARY_ID_CODE);
				if(student != null){
					Reply reply = replyDAO.select_by_id(replyId, ReplyDAO.PRIMARY_ID_CODE).pop();
					if(reply.getUser_id().equals(student.getId())){
						//回复删除之后，帖子回复数减一
						Post post= postDAO.select_by_id(reply.getPost_id());
						int tempNum = post.getReply_num();
						post.setReply_num(tempNum--);
						postDAO.update_by_Id(post);
						
						replyDAO.delete_by_id(replyId, ReplyDAO.PRIMARY_ID_CODE);
						
					}
				}
			}
		}
	}
	
	
}
