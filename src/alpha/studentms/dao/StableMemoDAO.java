package alpha.studentms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import alpha.studentms.bean.Memo;
import alpha.studentms.util.JdbcUtils;

/**
 * 学生必做和选做待办任务的数据访问操作
 * 
 * @author joker
 * @see JdbcUtils
 * @see Memo
 */
public class StableMemoDAO {
	private static Connection connection = JdbcUtils.getConnection();

	/**
	 * 增加必做或选做的备忘录
	 * 
	 * @param id
	 *            用户和备忘录关系的id
	 * @param user
	 *            用户的id
	 * @param stableMemoId
	 *            必做或选做备忘录的id
	 */
	public void insertStableMemo(String id, String user, String stableMemoId) {
		String sql = "insert into t_user_stable_memo values(?,?,?)";
		PreparedStatement insertStament;
		try {
			insertStament = connection.prepareStatement(sql);
			insertStament.setString(1, id);
			insertStament.setString(2, user);
			insertStament.setString(3, stableMemoId);
			insertStament.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 通过备忘录和用户关系的id与用户id删除用户的备忘录内容
	 * 
	 * @param id
	 *            用户和备忘录关系的id
	 * @param user
	 *            用户的id
	 */
	public void deleteStableMemoById(String id, String user) {
		String sql = "delete from t_user_stable_memo where pk_id = ? and fk_user = ?";
		PreparedStatement deleteStament;
		try {
			deleteStament = connection.prepareStatement(sql);
			deleteStament.setString(1, id);
			deleteStament.setString(2, user);
			deleteStament.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 获得用户的所有必做备忘录内容
	 * 
	 * @param user
	 *            用户的id
	 * @return List<Memo>一个存储备忘录的列表
	 */
	public List<Memo> getAllCompulsoryMemo(String user) {
		String sql = "select * from t_user_stable_memo,t_stable_memo t where "
				+ "fk_stable_memo=t.pk_id and fk_user = ? and docType = ?";
		PreparedStatement searchStatement;
		List<Memo> list = new ArrayList<>();
		try {
			searchStatement = connection.prepareStatement(sql);
			searchStatement.setString(1, user);
			searchStatement.setInt(2, 0);
			ResultSet set = searchStatement.executeQuery();
			while (set.next()) {
				Memo memo = new Memo();
				memo.setId(set.getString(1));
				memo.setUser(user);
				memo.setTitle(set.getString(5));
				memo.setContent(set.getString(6));
				list.add(memo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 获得用户所有选做的备忘录内容
	 * 
	 * @param user
	 *            用户的id
	 * @return List<Memo>一个存储备忘录的列表
	 */
	public List<Memo> getAllOptionalMemo(String user) {
		String sql = "select * from t_user_stable_memo,t_stable_memo t where "
				+ "fk_stable_memo=t.pk_id and fk_user = ? and docType = ?";
		PreparedStatement searchStatement;
		List<Memo> list = new ArrayList<>();
		try {
			searchStatement = connection.prepareStatement(sql);
			searchStatement.setString(1, user);
			searchStatement.setInt(2, 1);
			ResultSet set = searchStatement.executeQuery();
			while (set.next()) {
				Memo memo = new Memo();
				memo.setId(set.getString(1));
				memo.setUser(user);
				memo.setTitle(set.getString(5));
				memo.setContent(set.getString(6));
				list.add(memo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 获取所有的选做任务
	 * @return
	 */
	public List<Memo> getAllOptionalMemo(){
		List<Memo> list = new LinkedList<>();
		String all_option_list = "SELECT * FROM t_stable_memo WHERE docType = 1";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(all_option_list);
			ResultSet set = preparedStatement.executeQuery();
			while (set.next()) {
				Memo memo = new Memo();
				memo.setId(set.getString("pk_id"));
				memo.setTitle(set.getString("title"));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 释放数据库连接
	 */
	public void close() {
		JdbcUtils.releaseConnection(connection);
	}
}