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

import service.CommentService;
import beans.Comment;
import beans.User;

@WebServlet(urlPatterns = { "/comment" })
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();

		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("loginUser");

		Comment comment = new Comment();
		comment.setText(request.getParameter("text"));
		comment.setPostingId(Integer.parseInt(request.getParameter("posting_id")));
		comment.setUserId(user.getId());

		if (isValid(request, messages) == true) {

			new CommentService().register(comment);

			List<String> complete = new ArrayList<String>();
			complete.add("正常に新規コメントが投稿されました。");
			session.setAttribute("completeMessage", complete);

			response.sendRedirect("home");

		} else {

			session.setAttribute("errorMessages", messages);
			session.setAttribute("comments", comment);
			response.sendRedirect("home");
		}

	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String text = request.getParameter("text");

		if (StringUtils.isEmpty(text) == true) {
			messages.add("コメントを入力してください。");
		}
		if (text.length() > 500) {
			messages.add("コメントは500文字以下で入力してください。");
		}

		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
