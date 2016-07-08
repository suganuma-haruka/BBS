package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.User;
import exception.NoRowsUpdatedRuntimeException;
import exception.SQLRuntimeException;

public class UserDao {

	public User getUser(Connection connection, String loginId,String password) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE login_id = ? AND password = ?";

			ps = connection.prepareStatement(sql);
			ps.setString(1, loginId);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true) {
				return null;
			} else if (2 <= userList.size()) {
				throw new IllegalStateException("2 <= userList.size()");
			} else {
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<User> toUserList(ResultSet rs) throws SQLException {

		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String loginId = rs.getString("login_id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				int branchId = rs.getInt("branch_id");
				int positionId = rs.getInt("position_id");

				User user = new User();
				user.setId(id);
				user.setLoginId(loginId);
				user.setPassword(password);
				user.setName(name);
				user.setBranchId(branchId);
				user.setPositionId(positionId);

				ret.add(user);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public void insert(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO users ( ");
			sql.append("login_id, ");
			sql.append("password, ");
			sql.append("name, ");
			sql.append("branch_id, ");
			sql.append("position_id, ");
			sql.append("state");
			sql.append(") VALUES (");
			sql.append("?, "); // login_id
			sql.append("?, "); // password
			sql.append("?, "); // name
			sql.append("?, "); // branch_id
			sql.append("?, "); // position_id
			sql.append("1"); // state
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getLoginId());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setInt(4, user.getBranchId());
			ps.setInt(5, user.getPositionId());

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void update(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE users SET");
			sql.append(" login_id = ?,");
			sql.append(" password= ?,");
			sql.append(" name= ?,");
			sql.append(" branch_id= ?,");
			sql.append(" position_id= ?");
			sql.append(" WHERE");
			sql.append(" id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getLoginId());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setInt(4, user.getBranchId());
			ps.setInt(5, user.getPositionId());
			ps.setInt(6, user.getId());

			int count = ps.executeUpdate();

			if (count == 0) {
				throw new NoRowsUpdatedRuntimeException();
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public User getUser(Connection connection, int id) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE id = ?";

			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true) {
				return null;
			} else if (2 <= userList.size()) {
				throw new IllegalStateException("2 <= userList.size()");
			} else {
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public List<User> getUserControlList(Connection connection, int num) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<User> ret = toUsersList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<User> toUsersList(ResultSet rs) throws SQLException {

		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String login_id = rs.getString("login_id");
//				String password = rs.getString("password");
				String name = rs.getString("name");
				int branchId = rs.getInt("branch_id");
				int positionId = rs.getInt("position_id");
//				boolean state = rs.getBoolean("state");
//				Timestamp insertDate = rs.getTimestamp("insert_date");
//				Timestamp updateDate = rs.getTimestamp("update_date");

				User users = new User();
				users.setId(id);
				users.setLoginId(login_id);
//				users.setPassword(password);
				users.setName(name);
				users.setBranchId(branchId);
				users.setPositionId(positionId);
//				users.setState(state);
//				users.setInsertDate(insertDate);
//				users.setUpdateDate(updateDate);

				ret.add(users);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

//	public int getState(Connection connection, int id){
//		PreparedStatement ps = null;
//		int ret = 0;
//		try{
//			String sql = "SELECT state FROM users WHERE id = ?";
//			ps = connection.prepareStatement(sql);
//			ps.setInt(1, id);
//
//			ResultSet rs = ps.executeQuery();
//			rs.next();
//			ret = rs.getInt("state");
//
//		}catch(Exception e){
//
//		}finally{
//			close(ps);
//		}
//		return ret;
//	}

	public void changeState(Connection connection, int id,int state){
		PreparedStatement ps = null;

		try{
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE users SET state = ? WHERE id = ?");
			ps = connection.prepareStatement(sql.toString());
			ps.setInt(1, state);
			ps.setInt(2, id);
			if((ps.executeUpdate()) == 0){
				throw new NoRowsUpdatedRuntimeException();
			}
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}

}