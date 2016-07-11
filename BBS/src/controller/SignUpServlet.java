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

		List<Branches> branchList = new BranchService().select();
		List<Positions> positionList = new PositionService().select();

		request.setAttribute("branchList", branchList);
		request.setAttribute("positionList", positionList);

		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();

		HttpSession session = request.getSession();

		User user = new User();
		user.setUserId(request.getParameter("userId"));
		user.setPassword(request.getParameter("password"));
		user.setName(request.getParameter("name"));
		user.setBranchId(Integer.parseInt(request.getParameter("branch")));
		user.setPositionId(Integer.parseInt(request.getParameter("position")));

		if (isValid(request, messages) == true) {

			new UserService().register(user);

			List<String> complete = new ArrayList<String>();
			complete.add("正常に新規ユーザーが登録されました。");
			session.setAttribute("completeMessage", complete);

			response.sendRedirect("home");
		} else {
			session.setAttribute("errorMessages", messages);
			session.setAttribute("user", user);
			response.sendRedirect("signup");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		String passwordCheck = request.getParameter("passwordCheck");
		String name = request.getParameter("name");
		int branch = Integer.parseInt(request.getParameter("branch"));
		int position = Integer.parseInt(request.getParameter("position"));

		if (StringUtils.isEmpty(userId) == true) {
			messages.add("ログインIDを入力してください。");
		} else if (!userId.matches("^[0-9a-zA-Z]{6,20}")) {
			messages.add("ログインIDは半角英数字6桁以上20桁以下で入力してください。");
		}

		if (StringUtils.isEmpty(password) == true || StringUtils.isEmpty(passwordCheck) == true) {
			messages.add("パスワードを入力してください。");
		} else if (password.matches("^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]")) {
			messages.add("パスワードは半角文字のみで入力してください。");
		} else if (password.matches("{6,255}$")) {
			messages.add("パスワードは6文字以上255文字以下で入力してください。");
		} else if (password.equals(passwordCheck) == false) {
			messages.add("入力されたパスワードが一致しません。");
		}

		if (StringUtils.isEmpty(name) == true) {
			messages.add("ユーザー名を入力してください。");
		} else if (name.length() > 10) {
			messages.add("ユーザー名は10文字以下で入力してください。");
		}

		if (branch == 0){
			messages.add("所属支店を選択してください。");
		}

		if (position == 0){
			messages.add("所属部署・役職を選択してください。");
		}

		// TODO アカウントが既に利用されていないか、メールアドレスが既に登録されていないかなどの確認も必要
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
