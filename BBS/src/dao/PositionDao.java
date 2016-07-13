package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Positions;
import exception.SQLRuntimeException;

public class PositionDao {

	public List<Positions> getPositions(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM positions");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<Positions> positionList = toPositionList(rs);

			return positionList;

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}

	private List<Positions> toPositionList(ResultSet rs) throws SQLException {

		List<Positions> PositionList = new ArrayList<Positions>();
		try {
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");

				Positions position = new Positions();
				position.setId(id);
				position.setName(name);

				PositionList.add(position);
			}
			return PositionList;

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}

}
