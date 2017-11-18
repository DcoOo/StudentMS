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
				String rempTitle = tempPost.getTitle();
				
				for(int i=0;i<cuted.length;i++){
					if(rempS.contains(cuted[i])){
						counter++;
					}
					if(rempTitle.contains(cuted[i])){
						counter++;
					}
					
				}
				
				//查询帖子的所有回复，查找帖子回复中的关键字并计数
				List<Reply> tempReplyList = new ArrayList<Reply>();
				tempReplyList = replyDAO.select_by_id(tempPost.getId(), ReplyDAO.POST_ID_CODE);
				
				Iterator<Reply> iteratorR = tempReplyList.iterator();
				System.out.println(iteratorR.hasNext());
				while(iteratorR.hasNext()){
					Reply tempReply = iteratorR.next();
					for(int j=0;j<cuted.length;j++){
						if(tempReply.getContent().contains(cuted[j])){
							counter++;
							
						}
					}
				}
				System.out.println(counter);
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
	                        Post tempPost = tempResultPost.get(j);
	                        
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

		
}
