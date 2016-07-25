package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hsqldb.lib.StringUtil;

import service.CommentService;
import service.PostingService;
import beans.User;
import beans.UserComment;
import beans.UserPosting;

@WebServlet(urlPatterns = { "/home" })
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();

		List<String> messages = new ArrayList<String>();

		User loginUser = (User) request.getSession().getAttribute("loginUser");

		session.setAttribute("loginUser", loginUser);

		String category = request.getParameter("category");
		String startYear = request.getParameter("startYear");
		String startMonth = request.getParameter("startMonth");
		String startDay = request.getParameter("startDay");
		String endYear = request.getParameter("endYear");
		String endMonth = request.getParameter("endMonth");
		String endDay = request.getParameter("endDay");

		//デフォルト
//		String startDate = new PostingService().getStartDay();	//一番古い投稿の日付
//		String endDate = new PostingService().getEndDay();		//一番新しい投稿の日付
		String startDate;
		String endDate;

		if (!StringUtil.isEmpty(startYear) && !StringUtil.isEmpty(startMonth) && !StringUtil.isEmpty(startDay)) {
			startDate = startYear +'-'+ startMonth +'-'+ startDay;
		} else if (StringUtil.isEmpty(startYear) && StringUtil.isEmpty(startMonth) && StringUtil.isEmpty(startDay)) {
			startDate = new PostingService().getStartDay();
		} else {
			messages.add("不正な処理が実行されました。");
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("home");
			return;
		}

		if (checkDate(startDate) == false) {
			messages.add("不正な処理が実行されました。");
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("home");
			return;
		}

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(sdf.format(date));

		if (!StringUtil.isEmpty(endYear) && !StringUtil.isEmpty(endMonth) && !StringUtil.isEmpty(endDay)) {
			endDate = endYear +'-'+ endMonth +'-'+ endDay;
		} else if(StringUtil.isEmpty(endYear) && StringUtil.isEmpty(endMonth) && StringUtil.isEmpty(endDay)){
//			endDate = new PostingService().getEndDay();
			endDate = sdf.format(date);
		} else {
			messages.add("不正な処理が実行されました。");
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("home");
			return;
		}

		if (checkDate(endDate) == false) {
			messages.add("不正な処理が実行されました。");
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("home");
			return;
		}

		String[] startResult = startDate.split(" ");
		startResult = startResult[0].split("-");
		startYear = String.format("%4s", startResult[0]);
		startMonth = String.format("%2s", startResult[1]).replace(" ", "0");
		startDay = String.format("%2s", startResult[2]).replace(" ", "0");

		startDate = startYear + '-' + startMonth + '-' + startDay;

		String[] endResult = endDate.split(" ");
		endResult = endResult[0].split("-");
		endYear = String.format("%4s", endResult[0]);
		endMonth = String.format("%2s", endResult[1]).replace(" ", "0");
		endDay = String.format("%2s", endResult[2]).replace(" ", "0");

		endDate = endYear + '-' + endMonth + '-' + endDay;

		List<UserPosting> userPostings = new PostingService().getPostings(category, startDate, endDate);
		List<UserComment> userComments = new CommentService().getComment();
		List<UserPosting> categories = new PostingService().getCategory(category);

		String searchStartYear = startDate.substring(0,4);
		String searchStartMonth = startDate.substring(5,7);
		String searchStartDay = startDate.substring(8,10);
		String searchEndYear = endDate.substring(0,4);
		String searchEndMonth = endDate.substring(5,7);
		String searchEndDay = endDate.substring(8,10);

		request.setAttribute("startYear", searchStartYear);
		request.setAttribute("startMonth", searchStartMonth);
		request.setAttribute("startDay", searchStartDay);
		request.setAttribute("endYear", searchEndYear);
		request.setAttribute("endMonth", searchEndMonth);
		request.setAttribute("endDay", searchEndDay);

		request.setAttribute("userPostings", userPostings);
		request.setAttribute("userComments", userComments);
		request.setAttribute("categories", categories);
		request.setAttribute("category", category);
		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}


	public static boolean checkDate(String date) {
	    if (date == null || date.length() != 10) {
//	        throw new IllegalArgumentException("引数の文字列["+ date +"]" + "は不正です。");
	    }
	    date = date.replace('-', '/');
	    DateFormat format = DateFormat.getDateInstance();
	    // 日付/時刻解析を厳密に行うかどうかを設定する。
	    format.setLenient(false);
	    try {
	        format.parse(date);
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}

}
