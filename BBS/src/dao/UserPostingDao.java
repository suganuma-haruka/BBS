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
				int postingId = rs.getInt("posting_id");
				int userId = rs.getInt("user_id");
				int branchId = rs.getInt("branch_id");
				int positionId = rs.getInt("position_id");
				String name = rs.getString("name");
				String title = rs.getString("title");
				String text = rs.getString("text");
				String category = rs.getString("category");
				Timestamp insertDate = rs.getTimestamp("insert_date");

				UserPosting posting = new UserPosting();
				posting.setPostingId(postingId);
				posting.setUserId(userId);
				posting.setBranchId(branchId);
				posting.setPositionId(positionId);
				posting.setName(name);
				posting.setTitle(title);
				posting.setText(text);
				posting.setCategory(category);
				posting.setInsertDate(insertDate);

				userPostingList.add(posting);
			}
			return userPostingList;

		} finally {
			close(rs);
		}
	}

	public List<UserPosting> getPostings(Connection connection, int num,
			String category, String startDay, String endDay) {

		PreparedStatement ps = null;
		StringBuilder sql = new StringBuilder();
		try {
			sql.append("SELECT * FROM user_posting ");
			sql.append(" WHERE insert_date BETWEEN ? ");
			sql.append(" AND ? ");
			if (category != null && !category.isEmpty()) {

				sql.append(" AND category = ? ");
			}

			sql.append(" ORDER BY insert_date DESC ");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, startDay +  " 00:00:00");
			ps.setString(2, endDay + " 23:59:59");
			if (category != null && !category.isEmpty()) {
				ps.setString(3, category);
			}

			ResultSet rs = ps.executeQuery();
			List<UserPosting> userPostings = toUserPostingList(rs);

			return userPostings;

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public String getStartDay(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();

			sql.append("SELECT insert_date FROM user_posting ");
			sql.append(" WHERE insert_date LIMIT 1 ");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			String startDay = null;

			while (rs.next()) {
				startDay = rs.getString("insert_date");
			}
			return startDay;

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public String getEndDay(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT insert_date FROM user_posting ");
			sql.append(" WHERE insert_date  ORDER BY insert_date DESC LIMIT 1 ");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			String startDay = null;

			while (rs.next()) {
				startDay = rs.getString("insert_date");
			}
			return startDay;

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public List<UserPosting> getCategory(Connection connection, String category) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT DISTINCT category FROM user_posting ");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UserPosting> ret = toCategoryList(rs);

			return ret;

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<UserPosting> toCategoryList(ResultSet rs) throws SQLException {

		List<UserPosting> categoryList = new ArrayList<UserPosting>();
		try {
			while(rs.next()) {
				String category = rs.getString("category");

				UserPosting categories = new UserPosting();
				categories.setCategory(category);

				categoryList.add(categories);
			}
			return categoryList;

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}

}
