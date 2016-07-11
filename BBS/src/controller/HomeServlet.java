package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

		User user = (User) request.getSession().getAttribute("loginUser");

		HttpSession session = request.getSession();
		session.setAttribute("loginUser", user);

		boolean isShowPostingForm;
		if (user != null) {
			isShowPostingForm = true;
		} else {
			isShowPostingForm = false;
		}

		List<UserPosting> userPostings = new PostingService().getPosting();
		List<UserComment> userComments =new CommentService().getComment();

		request.setAttribute("userPostings", userPostings);
		request.setAttribute("userComments", userComments);
		request.setAttribute("isShowPostingForm", isShowPostingForm);

		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}
}
