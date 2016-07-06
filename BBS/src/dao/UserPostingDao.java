package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import beans.UserPosting;
import exception.SQLRuntimeException;

public class UserPostingDao {

	public List<UserPosting> getUserPosting(Connection connection, int num) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM user_posting ");
			sql.append("ORDER BY insert_date ASC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UserPosting> userPosting = toUserPostingList(rs);
			return userPosting;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<UserPosting> toUserPostingList(ResultSet rs)
			throws SQLException {

		List<UserPosting> userPostingList = new ArrayList<UserPosting>();
		try {
			while (rs.next()) {
				String name = rs.getString("name");
				String title = rs.getString("title");
				String text = rs.getString("text");
				Timestamp insertDate = rs.getTimestamp("insert_date");

				UserPosting posting = new UserPosting();
				posting.setName(name);
				posting.setTitle(title);
				posting.setText(text);
				posting.setInsertDate(insertDate);

				userPostingList.add(posting);
			}
			return userPostingList;
		} finally {
			close(rs);
		}
	}
}