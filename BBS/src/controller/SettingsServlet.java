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
import exception.NoRowsUpdatedRuntimeException;

@WebServlet(urlPatterns = { "/settings" })
public class SettingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<String> messages = new ArrayList<String>();

		HttpSession session = request.getSession();

		try {
			if (request.getParameter("userId") != null) {

				int userId = (Integer.parseInt(request.getParameter("userId")));

				User editUser = new UserService().getUser(userId);
				session.setAttribute("editUser", editUser);
			} else {

				messages.add("不正なIDが選択されました。");
				session.setAttribute("errorMessages", messages);
				response.sendRedirect("userControl");
				return;
			}
		} catch (NumberFormatException e) {

			messages.add("不正なIDが選択されました。");
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("userControl");
			return;
		}

		List<Branches> branchList = new BranchService().select();
		List<Positions> positionList = new PositionService().select();
		request.setAttribute("branchList", branchList);
		request.setAttribute("positionList", positionList);

		request.getRequestDispatcher("settings.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<String> messages = new ArrayList<String>();

		List<Branches> branchList = new BranchService().select();
		List<Positions> positionList = new PositionService().select();

		HttpSession session = request.getSession();

		User editUser = new User();
		editUser.setUserId(request.getParameter("userId"));
		editUser.setPassword(request.getParameter("password"));
		editUser.setName(request.getParameter("name"));
		editUser.setBranchId(Integer.parseInt(request.getParameter("branch")));
		editUser.setPositionId(Integer.parseInt(request.getParameter("position")));
		editUser.setId(Integer.parseInt(request.getParameter("id")));

		if (isValid(request, messages) == true) {

			try {

				new UserService().update(editUser);

			} catch (NoRowsUpdatedRuntimeException e) {

				session.removeAttribute("editUser");
				messages.add("他の人によって更新されています。最新のデータを表示しました。データを確認してください。");
				session.setAttribute("errorMessages", messages);
				response.sendRedirect("settings");
				return;
			}

			List<String> complete = new ArrayList<String>();
			complete.add("正常にユーザー編集が完了しました。");
			session.setAttribute("completeMessage", complete);

			User loginUser = (User) request.getSession().getAttribute("loginUser");

			if ( loginUser.getId() == editUser.getId() ) {
				session.setAttribute("loginUser", editUser);
			}

			session.setAttribute("editUser", editUser);
			response.sendRedirect("userControl");
		} else {

			session.setAttribute("errorMessages", messages);
			request.setAttribute("editUser", editUser);
			request.setAttribute("branchList", branchList);
			request.setAttribute("positionList", positionList);
			request.getRequestDispatcher("settings.jsp").forward(request, response);
//			System.out.println(editUser);
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		String passwordCheck = request.getParameter("passwordCheck");
		String name = request.getParameter("name");
		int branch = Integer.parseInt(request.getParameter("branch"));
		int position = Integer.parseInt(request.getParameter("position"));

		int id = Integer.valueOf(request.getParameter("id"));
		UserService userCheck = new UserService();
		User user = userCheck.userCheck(userId, id);

		if (StringUtils.isEmpty(userId) == true) {
			messages.add("ログインIDを入力してください。");
		} else if (!userId.matches("^[0-9a-zA-Z]{6,20}")) {
			messages.add("ログインIDは半角英数字6文字以上20文字以下で入力してください。");
		}
		if(user != null){
			messages.add("このログインIDは既に使用されています");
		}

		if(!StringUtils.isEmpty(password)) {
			if (password.matches("^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]")) {
				messages.add("パスワードは半角文字のみで6文字以上255文字以下で入力してください。");
			} else if (password.matches("{6,255}$")) {
				messages.add("パスワードは半角文字のみで6文字以上255文字以下で入力してください。");
			} else if (password.equals(passwordCheck) == false) {
				messages.add("入力されたパスワードが一致しません。");
			}
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

		if (branch == 1 && position !=1 && position != 2) {
			messages.add("支店コードと所属部署・役職の組み合わせが不正です。");
		}

		if (branch != 1 && position != 3 && position != 4) {
			messages.add("支店コードと所属部署・役職の組み合わせが不正です。");
		}

		// TODO アカウントが既に利用されていないか、メールアドレスが既に登録されていないかなどの確認も必要
		if(messages.size() == 0) {
			return true;
		}else{
			return false;
		}
	}

}
