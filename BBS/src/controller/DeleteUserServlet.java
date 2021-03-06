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

import service.UserService;

@WebServlet(urlPatterns = {"/deleteUser"})
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();

		int id = Integer.parseInt(request.getParameter("id"));
		new UserService().deleteUser(id);

		List<String> complete = new ArrayList<String>();
		complete.add("正常にユーザーアカウントの削除が完了しました。");
		session.setAttribute("completeMessage", complete);

		response.sendRedirect("userControl");
	}

}
