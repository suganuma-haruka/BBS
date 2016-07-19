package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Posting;
import beans.UserPosting;
import dao.PostingDao;
import dao.UserPostingDao;

public class PostingService {

	public void register(Posting posting) {

		Connection connection = null;
		try {
			connection = getConnection();

			PostingDao postingDao = new PostingDao();
			postingDao.insert(connection, posting);

			commit(connection);

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

	private static final int LIMIT_NUM = 1000;

	public List<UserPosting> getPosting() {

		Connection connection = null;
		try {
			connection = getConnection();

			UserPostingDao postingDao = new UserPostingDao();
			List<UserPosting> posting = postingDao.getUserPosting(connection, LIMIT_NUM);

			commit(connection);

			return posting;

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

	public void deletePosting(int id) {

		Connection connection = getConnection();
		try {
			new PostingDao().deletePosting(connection, id);

			commit(connection);

		} catch(RuntimeException e) {
			rollback(connection);
			throw e;
		} catch(Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public List<UserPosting> getPostings(String category, String startDay, String endDay) {

		Connection connection = null;
		try {
			connection = getConnection();

			UserPostingDao postingDao = new UserPostingDao();
			List<UserPosting> ret = postingDao.getPostings(connection, LIMIT_NUM,  category,  startDay,  endDay);

			commit(connection);

			return ret;

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

	public String getStartDay(){
		Connection connection = null;
		try{
			connection = getConnection();

			UserPostingDao postingDao = new UserPostingDao();
			String ret = postingDao.getStartDay(connection);

			commit(connection);

			return ret ;

		}catch (RuntimeException e){
			rollback(connection);
			throw e;
		}catch(Error e){
			rollback(connection);
			throw e;
		}finally{
			close(connection);
		}
	}

	public String getEndDay(){
		Connection connection = null;
		try{
			connection = getConnection();

			UserPostingDao postingDao = new UserPostingDao();
			String ret = postingDao.getEndDay(connection);

			commit(connection);

			return ret ;

		}catch (RuntimeException e){
			rollback(connection);
			throw e;
		}catch(Error e){
			rollback(connection);
			throw e;
		}finally{
			close(connection);
		}
	}

	public List<UserPosting> getCategory(String category) {

		Connection connection = null;
		try {
			connection = getConnection();

			UserPostingDao userPostingDao = new UserPostingDao();
			List<UserPosting> ret = userPostingDao.getCategory(connection, category);

			commit(connection);

			return ret;

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
