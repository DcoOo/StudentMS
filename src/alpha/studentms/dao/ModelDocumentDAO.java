package alpha.studentms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alpha.studentms.bean.ModelDocument;
import alpha.studentms.util.JdbcUtils;

/**
 * 
 * @author joker 模板文档的数据访问操作
 * @see JdbcUtils
 * @see ModelDocument
 */
public class ModelDocumentDAO {
	private Connection connection = JdbcUtils.getConnection();

	/**
	 * 向通知中添加模板文档
	 * 
	 * @param modelDocument
	 *            模板文档
	 */
	public void insertModelDocument(ModelDocument modelDocument) {
		String sql = "insert into t_model_doc(pk_model_doc,fk_teacher,fk_message,name) values(?,?,?,?)";
		PreparedStatement insertStatement;
		try {
			insertStatement = connection.prepareStatement(sql);
			insertStatement.setString(1, modelDocument.getModelDoc());
			insertStatement.setString(2, modelDocument.getTeacher());
			insertStatement.setString(3, modelDocument.getMessage());
			insertStatement.setString(4, modelDocument.getName());
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 通过模板文档id删除通知中的模板文档
	 * 
	 * @param id
	 *            模板文档id
	 */
	public void deleteModelDocumentById(String id) {
		String sql = "delete from t_model_doc where pk_model_doc = ?";
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
	 * 
	 * @param id
	 *            模板文档id
	 * @return ModelDocument 与id对应的模板文档
	 */
	public ModelDocument searchModelDocById(String id) {
		String sql = "select * from t_model_doc where pk_model_doc = ?";
		PreparedStatement searchStatement;
		ModelDocument modelDocument = new ModelDocument();
		try {
			searchStatement = connection.prepareStatement(sql);
			searchStatement.setString(1, id);
			ResultSet set = searchStatement.executeQuery();
			set.next();
			
			modelDocument.setModelDoc(id);
			modelDocument.setTeacher(set.getString(2));
			modelDocument.setMessage(set.getString(3));
			modelDocument.setName(set.getString(4));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelDocument;
	}

	/**
	 * 释放数据库连接
	 */
	public void realseConenction() {
		JdbcUtils.releaseConnection(connection);
	}
}
