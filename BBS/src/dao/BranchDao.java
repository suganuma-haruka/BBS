package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Branches;
import exception.SQLRuntimeException;

public class BranchDao {

	public List<Branches> getBranches(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM branches");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<Branches> branchList = toBranchesList(rs);

			return branchList;

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}

	private List<Branches> toBranchesList(ResultSet rs) throws SQLException {

		List<Branches> BranchList = new ArrayList<Branches>();
		try {
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");

				Branches branch = new Branches();
				branch.setId(id);
				branch.setName(name);

				BranchList.add(branch);

			}
			return BranchList;

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}

}
