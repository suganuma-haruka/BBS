package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.Comment;
import exception.NoRowsUpdatedRuntimeException;
import exception.SQLRuntimeException;

public class CommentDao {

	public void insert(Connection connection, Comment comment) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO comments ( ");
			sql.append("user_id, ");
			sql.append("posting_id, ");
			sql.append("text, ");
			sql.append("insert_date, ");
			sql.append("update_date");
			sql.append(") VALUES (");
			sql.append("?, "); // user_id
			sql.append("?, "); // posting_id
			sql.append("?, "); // text
			sql.append("CURRENT_TIMESTAMP, "); // insert_date
			sql.append("CURRENT_TIMESTAMP"); // update_date
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, comment.getUserId());
			ps.setInt(2, comment.getPostingId());
			ps.setString(3, comment.getText());

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void deleteComment(Connection connection, int id) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM comments WHERE id = ?");
			ps = connection.prepareStatement(sql.toString());
			ps.setInt(1, id);
			if ((ps.executeUpdate()) == 0) {

				throw new NoRowsUpdatedRuntimeException();
			}

		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

}
