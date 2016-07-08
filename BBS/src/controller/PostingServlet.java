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

import org.apache.commons.lang.StringUtils;

import service.PostingService;
import beans.Posting;
import beans.User;

@WebServlet(urlPatterns = { "/posting" })
public class PostingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		request.getRequestDispatcher("posting.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("loginUser");

		List<String> messages = new ArrayList<String>();

		Posting posting = new Posting();
		posting.setTitle(request.getParameter("title"));
		posting.setText(request.getParameter("text"));
		posting.setCategory(request.getParameter("category"));
		posting.setUserId(user.getId());

		if (isValid(request, messages) == true) {
			new PostingService().register(posting);

			List<String> complete = new ArrayList<String>();
			complete.add("正常に新規投稿処理が完了しました。");

			session.setAttribute("completeMessage", complete);
			response.sendRedirect("home");
		} else {
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("posting");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String title = request.getParameter("title");
		String text = request.getParameter("text");
		String category = request.getParameter("category");

		if (StringUtils.isEmpty(title) == true) {
			messages.add("件名を入力してください。");
		}
		if (50 < title.length()) {
			messages.add("件名は50文字以下で入力してください。");
		}

		if (StringUtils.isEmpty(text) == true) {
			messages.add("本文を入力してください。");
		}
		if (1000 < text.length()) {
			messages.add("本文は1,000文字以下で入力してください。");
		}

		if (StringUtils.isEmpty(category) == true) {
			messages.add("カテゴリを入力してください。");
		}
		if (10 < category.length()) {
			messages.add("カテゴリは10文字以下で入力してください。");
		}

		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
