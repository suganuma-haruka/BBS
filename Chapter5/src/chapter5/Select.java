package chapter5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Select {
	public static void main(String[] args) throws Exception {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/localhost";
		String user = "root";
		String password = "root";

		Class.forName(driver);

		Connection connection = null;
		try{
			connection = DriverManager.getConnection(url, user, password);
			connection.setAutoCommit(false);

			select(connection);

			connection.commit();
		} finally {
			if(connection != null) {
				connection.close();
			}
		}
	}

	public static void select(Connection connection) throws SQLException {

		Statement statement = null;
		ResultSet rs =null;

		try{
			statement = connection.createStatement();

			String sql = "SELECT * FROM author";
			rs = statement.executeQuery(sql);

			System.out.println("SELECTの結果（ここから）");

			while (rs.next()) {
				int userId = rs.getInt("id");
				String userName = rs.getString("name");
				String userKana = rs.getString("kana");
				String userSex = rs.getString("sex");

				System.out.println(userId + ", " + userName + ", "
						+ userKana + ", " + userSex);
			}

			System.out.println("SELECTの結果（ここまで）");
		} finally {
			if(rs != null) {
				rs.close();
			}
			if(statement != null) {
				statement.close();
			}
		}
	}
}
