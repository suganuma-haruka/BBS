package chapter5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Delete {
	public static void main(String[] args) throws Exception {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/localhost";
		String user = "root";
		String password = "root";

		Class.forName(driver);

		Connection connection = DriverManager.getConnection(url, user, password);
		connection.setAutoCommit(false);

		delete(connection);

		Select.select(connection);

		connection.commit();
		connection.close();
	}

	public static void delete(Connection connection) throws SQLException {

		Statement statement = connection.createStatement();

		String sql = "DELETE FROM author WHERE id = 0";
		int updateCount = statement.executeUpdate(sql);

		if (updateCount == 1) {
			System.out.println("削除成功");
		} else {
			System.out.println("削除失敗");
		}

		statement.close();
	}
}
