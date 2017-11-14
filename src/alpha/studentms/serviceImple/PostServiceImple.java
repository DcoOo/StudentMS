package alpha.studentms.serviceImple;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import alpha.studentms.bean.Post;
import alpha.studentms.bean.Student;
import alpha.studentms.dao.AssistantDAO;
import alpha.studentms.dao.ClassAdvicerDAO;
import alpha.studentms.dao.PostDAO;
import alpha.studentms.dao.ReplyDAO;
import alpha.studentms.dao.StudentDAO;
import alpha.studentms.service.PostService;
import alpha.studentms.util.UUIDGenerater;

public class PostServiceImple implements PostService {
	
	PostDAO postDAO = new PostDAO();
	ReplyDAO replyDAO = new ReplyDAO();
	ClassAdvicerDAO classAdvicerDAO = new ClassAdvicerDAO();
	AssistantDAO assistantDAO = new AssistantDAO();
	StudentDAO studentDAO = new StudentDAO();

	@Override
	public void addPost(String user, String title, String content) {
		// TODO Auto-generated method stub
		if(!user.equals("")){
			Post post = new Post(UUIDGenerater.getUUID(),user,title,content,0);
			postDAO.insert(post);
		}
	}

	@Override
	public void deletePost(String user, String postId) {
		// TODO Auto-generated method stub
		if((!user.equals(""))&&(!postId.equals(""))){
			if((classAdvicerDAO.findByID(user) != null) || (assistantDAO.findByID(user) != null)){
				postDAO.delete_by_id(postId);
				
				//帖子删除，回复全部删除
				replyDAO.delete_by_id(postId, ReplyDAO.POST_ID_CODE);
				
			}
			else{
				Student student = studentDAO.select_by_id(user, StudentDAO.PRIMARY_ID_CODE);
				if(student != null){
					Post post = postDAO.select_by_id(postId);
					if(post.getUser_id().equals(student.getId())){
						postDAO.delete_by_id(postId);
						
						//帖子删除，回复全部删除
						replyDAO.delete_by_id(postId, ReplyDAO.POST_ID_CODE);
					}
				}
			}
		}
	}

	@Override
	public List<Post> searchPost(String condition) {
		// TODO Auto-generated method stub
		List<Post> result = new ArrayList<Post>();
		
		
		if(!condition.equals("")){
			/**
			 * 根据空格切割字符串
			 */
			String[] cuted;
			cuted = condition.split(" +");
			/**
			 * 查出所有帖子
			 */
			List<Post> temp = new ArrayList<Post>();
			temp = postDAO.select_all();
			Iterator<Post> iterator = temp.iterator();
			/**
			 * 记录帖子和包含关键字的数目
			 */
			List<Post> tempResultPost = new ArrayList<Post>();
			List<Integer> tempResultNum = new ArrayList<Integer>();
			/**
			 * 遍历帖子，比对关键字，记录数目
			 */
			while(iterator.hasNext()){
				int counter = 0;
				Post tempPost = iterator.next();
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
			int h = 0;
			int n = tempResultNum.size() / 8;
			while(h <= n){
				h = 3 * h + 1;
			}
			while(h >= 1){
				for(int i=h;i<n;i++){
					int j = i - h;
					int get = tempResultNum.get(i);
					Post getPost = tempResultPost.get(i);
					while(j >= 0 && tempResultNum.get(j) > get){
						tempResultNum.set(j + h, tempResultNum.get(j));
						tempResultPost.set(j + h, tempResultPost.get(j));
						j = j - h;
					}
					tempResultNum.set(j + h, get);
					tempResultPost.set(j + h, getPost);
				}
				h = (h - 1) / 3;
			}
			
			/**
			 * 将排序后的结果放入到返回列表中
			 */
			result.addAll(tempResultPost);
		}
		
		
		return result;
	}

		
}
