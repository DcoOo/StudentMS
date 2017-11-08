package alpha.studentms.util;
<<<<<<< HEAD
=======

>>>>>>> f1b205db8c64789052d0ce971e81d244e493acc7
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
<<<<<<< HEAD
 
public class JdbcUtils {
       /**
        * 释放连接
        * @param connection
        */
       public static void releaseConnection(Connection connection){
              try {
                     if(connection != null ) {
                            connection.close();
                     }
              }catch (Exception e) {
                     e.printStackTrace();
              }
       }
       private static DataSource dataSource = null;
       static{
              //dataSource资源只能初始化一次
              dataSource= new ComboPooledDataSource("studentms");
       }
       /**
        * 获取连接
        * @return
        * @throws SQLException
        */
       public static Connection getConnection() throws SQLException{
              return dataSource.getConnection();
       }
=======

public class JdbcUtils {
	/**
	 * 释放连接
	 * 
	 * @param connection
	 */
	public static void releaseConnection(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static DataSource dataSource = null;
	static {
		// dataSource资源只能初始化一次
		dataSource = new ComboPooledDataSource("studentms");
	}

	/**
	 * 获取连接
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
>>>>>>> f1b205db8c64789052d0ce971e81d244e493acc7
}
