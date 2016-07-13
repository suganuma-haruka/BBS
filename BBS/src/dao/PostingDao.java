package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.Posting;
import exception.NoRowsUpdatedRuntimeException;
import exception.SQLRuntimeException;

public class PostingDao {

	public void insert(Connection connection, Posting posting) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO postings (");
			sql.append("user_id,");
			sql.append("title,");
			sql.append("text,");
			sql.append("category,");
			sql.append("insert_date,");
			sql.append("update_date");
			sql.append(") VALUES (");
			sql.append("?,"); //user_id
			sql.append("?,"); //title
			sql.append("?,"); //text
			sql.append("?,"); //category
			sql.append("CURRENT_TIMESTAMP,"); //insert_date
			sql.append("CURRENT_TIMESTAMP"); //update_date
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, posting.getUserId());
			ps.setString(2, posting.getTitle());
			ps.setString(3, posting.getText());
			ps.setString(4, posting.getCategory());

			ps.executeUpdate();

		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}

	public void deletePosting(Connection connection, int id) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM postings WHERE id = ?");
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
