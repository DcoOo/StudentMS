package alpha.studentms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alpha.studentms.bean.Memo;
import alpha.studentms.util.JdbcUtils;

/**
 * 
 * @author joker 备忘录的数据访问操作
 * @see Memo
 * @see JdbcUtils
 */
public class MemoDAO {
	private static Connection connection = JdbcUtils.getConnection();

	/**
	 * 插入一个备忘录
	 * 
	 * @param memo
	 *            备忘录对象
	 */
	public void insertMeno(Memo memo) {
		String sql = "insert into t_memo values(?,?,?,?)";
		PreparedStatement insertStatement;
		try {
			insertStatement = connection.prepareStatement(sql);
			insertStatement.setString(1, memo.getId());
			insertStatement.setString(2, memo.getUser());
			insertStatement.setString(3, memo.getTitle());
			insertStatement.setString(4, memo.getContent());
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 通过备忘录的id删除与之匹配的那条备忘录
	 * 
	 * @param id
	 *            备忘录的id
	 */
	public void deleteMemoById(String id) {
		String sql = "delete from t_memo where pk_id = ?";
		PreparedStatement deleteStatement;
		try {
			deleteStatement = connection.prepareStatement(sql);
			deleteStatement.setString(1, id);
			deleteStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 通过备忘录的id更改备忘录的content
	 * 
	 * @param id
	 *            备忘录的id
	 * @param content
	 *            备忘录名字
	 */
	public void updateMemoContentById(String id, String content) {
		String sql = "update t_memo set content = ? where pk_id = ?";
		PreparedStatement updateStatement;
		try {
			updateStatement = connection.prepareStatement(sql);
			updateStatement.setString(1, content);
			updateStatement.setString(2, id);
			updateStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param id
	 *            备忘录id
	 * @return Memo 备忘录 通过备忘录id查询备忘录
	 * 
	 */
	public Memo searchMemoById(String id) {
		String sql = "select * from t_memo where pk_id = ?";
		Memo memo = new Memo();
		PreparedStatement searchStatement;
		try {
			searchStatement = connection.prepareStatement(sql);
			searchStatement.setString(1, id);
			ResultSet set = searchStatement.executeQuery();
			while (set.next()) {
				memo.setId(id);
				memo.setUser(set.getString(2));
				memo.setTitle(set.getString(3));
				memo.setContent(set.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return memo;
	}

	/**
	 * 
	 * @param user
	 *            用户的id
	 * @return List<Memo>存放备忘录的链表 查询用户id对应的所有的备忘录
	 */
	public List<Memo> searchAllMemoByUser(String user) {
		String sql = "select * from t_memo where fk_user = ?";
		List<Memo> list = new ArrayList<>();
		PreparedStatement searchAllStatement;
		try {
			searchAllStatement = connection.prepareStatement(sql);
			searchAllStatement.setString(1, user);
			ResultSet set = searchAllStatement.executeQuery();
			while (set.next()) {
				Memo memo = new Memo();
				memo.setId(set.getString(1));
				memo.setUser(user);
				memo.setTitle(set.getString(3));
				memo.setContent(set.getString(4));
				list.add(memo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 释放数据库的连接
	 */
	public void releaseConnection() {
		JdbcUtils.releaseConnection(connection);
	}
}
