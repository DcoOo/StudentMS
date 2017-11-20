package alpha.studentms.serviceImple;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import alpha.studentms.bean.Post;
import alpha.studentms.bean.Reply;
import alpha.studentms.bean.Student;
import alpha.studentms.dao.AssistantDAO;
import alpha.studentms.dao.ClassAdvicerDAO;
import alpha.studentms.dao.PostDAO;
import alpha.studentms.dao.ReplyDAO;
import alpha.studentms.dao.StudentDAO;
import alpha.studentms.service.ReplyService;
import alpha.studentms.service.TeacherService;
import alpha.studentms.util.UUIDGenerater;

public class ReplyServiceImple implements ReplyService {
	
	ReplyDAO replyDAO = new ReplyDAO();
	PostDAO postDAO = new PostDAO();
	ClassAdvicerDAO classAdvicerDAO = new ClassAdvicerDAO();
	AssistantDAO assistantDAO = new AssistantDAO();
	StudentDAO studentDAO = new StudentDAO();
	TeacherService teacherService = new TeacherServiceImple();

	@Override
	public void reply(String replyId, String user, String postId,String content) {
		// TODO Auto-generated method stub
		if((!user.equals(""))&&(!postId.equals(""))){
			
			Reply reply = new Reply(replyId,user,postId,0,0,content);
			replyDAO.insert(reply);
			
			Post post = postDAO.select_by_id(postId);
			int tempNum = post.getReply_num();
			post.setReply_num(++tempNum);
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
	public List<Reply> searchReply(String condition) {
		// TODO Auto-generated method stub
		List<Reply> result = new ArrayList<Reply>();
		
		
		if(!condition.equals("")){
			/**
			 * 根据空格切割字符串
			 */
			String[] cuted;
			cuted = condition.split(" +");
			/**
			 * 查出所有回复
			 */
			List<Reply> temp = new ArrayList<Reply>();
			temp = replyDAO.select_all();
			Iterator<Reply> iterator = temp.iterator();
			/**
			 * 记录帖子和包含关键字的数目
			 */
			List<Reply> tempResultPost = new ArrayList<Reply>();
			List<Integer> tempResultNum = new ArrayList<Integer>();
			/**
			 * 遍历帖子，比对关键字，记录数目
			 */
			while(iterator.hasNext()){
				int counter = 0;
				Reply tempPost = iterator.next();
				String rempS = tempPost.getContent();
				
				
				for(int i=0;i<cuted.length;i++){
					if(rempS.contains(cuted[i])){
						counter++;
					}
					
				}
				
				tempResultPost.add(tempPost);
				tempResultNum.add(counter);
			}
			
			
			/**
			 * 希尔排序
			 */
			int incrementNum = tempResultNum.size()/2;
			while(incrementNum >=1){
	            for(int i=0;i<tempResultNum.size();i++){
	                //进行插入排序
	                for(int j=i;j<tempResultNum.size()-incrementNum;j=j+incrementNum){
	                    if(tempResultNum.get(j)>tempResultNum.get(j+incrementNum)){
	                        int temple = tempResultNum.get(j);
	                        Reply tempPost = tempResultPost.get(j);
	                        
	                        tempResultNum.set(j, tempResultNum.get(j+incrementNum));
	                        tempResultPost.set(j, tempResultPost.get(j+incrementNum));
	                        
	                        tempResultNum.set(j+incrementNum, temple);
	                        tempResultPost.set(j+incrementNum, tempPost);
	                    }
	                }
	            }
	            //设置新的增量
	            incrementNum = incrementNum/2;
	        }
			
			
			/**
			 * 将排序后的结果去除不包含关键字的帖子，放入到返回列表中
			 */
			while(tempResultNum.get(0) == 0){
				tempResultNum.remove(0);
				tempResultPost.remove(0);
			}
			result.addAll(tempResultPost);
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
	
	@Override
	public void updateReplyOpposeNum(String replyId) {
		replyDAO.addReplyOppose(replyId);
		
	}
	
	@Override
	public void updateReplyStarNum(String replyId) {
		replyDAO.addReplyStar(replyId);
		
	}

	@Override
	public int getStarNum(String replyId) {
		System.out.println(replyId);
		Reply reply = replyDAO.select_by_id(replyId, ReplyDAO.PRIMARY_ID_CODE).get(0);
		return reply.getStar_num();
	}

	@Override
	public int getOpposeNum(String replyId) {
		Reply reply = replyDAO.select_by_id(replyId, ReplyDAO.PRIMARY_ID_CODE).get(0);
		return reply.getOppose_num();
	}
	
	@Override
	public void addReplyIdToPostOwner(String postOwnerId, String replyId) {
		String studentCollection = studentDAO.select_collection_by_id(postOwnerId);
		if (studentCollection != null) {
			// 向学生的collection字段中更新reply链表的值
			JSONObject obj = new JSONObject(studentCollection);
			Map<String, Object> map = obj.toMap();
			@SuppressWarnings("unchecked")
			ArrayList<String> list = (ArrayList<String>) map.get("reply");
			list.add(replyId);
			studentDAO.update_collection_by_id(new JSONObject(map).toString(), postOwnerId);
			return;
		}
		String teacherCollection = teacherService.getTeacherCollectionByUserId(postOwnerId);
		if (teacherCollection != null) {
			// 向老师的collection字段中更新reply链表的值
			JSONObject obj = new JSONObject(teacherCollection);
			Map<String, Object> map = obj.toMap();
			@SuppressWarnings("unchecked")
			ArrayList<String> list = (ArrayList<String>) map.get("reply");
			list.add(replyId);
			classAdvicerDAO.updateCollection(new JSONObject(map).toString(), postOwnerId);
			return;
		}
	}
	
	@Override
	public Reply getReplyByReplyId(String replyId) {
		List<Reply> replys = replyDAO.select_by_id(replyId, ReplyDAO.PRIMARY_ID_CODE); 
		if (replys.size() == 0) {
			return null;
		}else{
			return replys.get(0);
		}
	}
	
	@Override
	public void deleteReplyIdInCollection(String userId, String replyId) {
		String studentCollection = studentDAO.select_collection_by_id(userId);
		if (studentCollection != null) {
			// 向学生的collection字段中删除reply
			JSONObject obj = new JSONObject(studentCollection);
			Map<String, Object> map = obj.toMap();
			@SuppressWarnings("unchecked")
			ArrayList<String> list = (ArrayList<String>) map.get("reply");
			list.remove(replyId);
			studentDAO.update_collection_by_id(new JSONObject(map).toString(), userId);
			return;
		}
		String teacherCollection = teacherService.getTeacherCollectionByUserId(userId);
		if (teacherCollection != null) {
			// 向老师的collection字段中删除指定的replyid
			JSONObject obj = new JSONObject(teacherCollection);
			Map<String, Object> map = obj.toMap();
			@SuppressWarnings("unchecked")
			ArrayList<String> list = (ArrayList<String>) map.get("reply");
			list.remove(replyId);
			classAdvicerDAO.updateCollection(new JSONObject(map).toString(), userId);
			return;
		}
		
	}
	
	
}
