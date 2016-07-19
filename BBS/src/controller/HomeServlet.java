package controller;

import java.io.IOException;
import java.util.ArrayList;
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

		try {
			User user = (User) request.getSession().getAttribute("loginUser");

			session.setAttribute("loginUser", user);

			String category = request.getParameter("category");
			String startYear = request.getParameter("startYear");
			String startMonth = request.getParameter("startMonth");
			String startDay = request.getParameter("startDay");
			String endYear = request.getParameter("endYear");
			String endMonth = request.getParameter("endMonth");
			String endDay = request.getParameter("endDay");

			//デフォルト
			String startDate = new PostingService().getStartDay();	//一番古い投稿の日付
			String endDate = new PostingService().getEndDay();		//一番新しい投稿の日付

			if(!StringUtil.isEmpty(startYear) && !StringUtil.isEmpty(startMonth) && !StringUtil.isEmpty(startDay)) {
				startDate = startYear +'-'+ startMonth +'-'+ startDay;
			}

			if(!StringUtil.isEmpty(endYear) && !StringUtil.isEmpty(endMonth) && !StringUtil.isEmpty(endDay)) {
				endDate = endYear +'-'+ endMonth +'-'+ endDay;
			}

			String[] startResult = startDate.split(" ");
			startResult = startResult[0].split("-");
			startYear = String.format("%4s", startResult[0]);
			startMonth = String.format("%2s", startResult[1]).replace(" ", "0");
			startDay = String.format("%2s", startResult[2]).replace(" ", "0");

			startDate = startResult[0] + '-' + startMonth + '-' + startDay;

			String[] endResult = endDate.split(" ");
			endResult = endResult[0].split("-");
			endYear = String.format("%4s", endResult[0]);
			endMonth = String.format("%2s", endResult[1]).replace(" ", "0");
			endDay = String.format("%2s", endResult[2]).replace(" ", "0");

			endDate = endResult[0] + '-' + endMonth + '-' + endDay;

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

		} catch(Exception e) {
			messages.add("不正な処理が実行されました。");
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("home");
			return;
		}
	}

}
