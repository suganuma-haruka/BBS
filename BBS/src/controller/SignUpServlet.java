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

import service.BranchService;
import service.PositionService;
import service.UserService;
import beans.Branches;
import beans.Positions;
import beans.User;

@WebServlet(urlPatterns = { "/signup" })
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();

		List<Branches> branchList = new BranchService().select();
		List<Positions> positionList = new PositionService().select();

		session.setAttribute("branchList", branchList);
		session.setAttribute("positionList", positionList);

		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();

		HttpSession session = request.getSession();

		User user = new User();
		user.setLoginId(request.getParameter("login_id"));
		user.setPassword(request.getParameter("password"));
		user.setName(request.getParameter("name"));
		user.setBranchId(Integer.parseInt(request.getParameter("branch_id")));
		user.setPositionId(Integer.parseInt(request.getParameter("position_id")));

		if (isValid(request, messages) == true) {

			new UserService().register(user);

			response.sendRedirect("./");
		} else {
			session.setAttribute("errorMessages", messages);
			request.setAttribute("user", user);
			request.getRequestDispatcher("signup.jsp").forward(request, response);
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String loginId = request.getParameter("login_id");
		String password = request.getParameter("password");
//		String name = request.getParameter("name");
//		int branchId = Integer.parseInt(request.getParameter("branch_id"));
//		int positionId = Integer.parseInt(request.getParameter("position_id"));


		if (StringUtils.isEmpty(loginId) == true) {
			messages.add("ログインIDを入力してください");
		}
//		if (!loginId.matches("~[a-zA-Z0-9]{6,20}")) {
//			messages.add("ログインIDは半角英数字6桁以上20桁以内で入力してください");
//		}

		if (StringUtils.isEmpty(password) == true) {
			messages.add("パスワードを入力してください");
		}
//		if (!password.matches("{6,255}")) {
//			messages.add("パスワードは6文字以上255文字以下で入力してください");
//		}

//		if (StringUtils.isEmpty(name) == true) {
//			messages.add("ユーザー名を入力してください");
//		}
//		if (name.length() > 10) {
//			messages.add("ユーザー名は10文字以内で入力してください");
//		}

		// TODO アカウントが既に利用されていないか、メールアドレスが既に登録されていないかなどの確認も必要
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
