package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Positions;
import dao.PositionDao;

public class PositionService {

	public List<Positions> select() {

		Connection connection = null;
		try {
			connection = getConnection();

			PositionDao positionDao = new PositionDao();
			List<Positions> position = positionDao.getPositions(connection);

			commit(connection);

			return position;

		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

}
