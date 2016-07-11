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

import service.BranchService;
import service.LoginService;
import service.PositionService;
import beans.Branches;
import beans.Positions;
import beans.User;

@WebServlet(urlPatterns = { "/userControl" })
public class UserControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<Branches> branchList = new BranchService().select();
		List<Positions> positionList = new PositionService().select();
		request.setAttribute("branchList", branchList);
		request.setAttribute("positionList", positionList);

		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("loginUser");

		LoginService loginService = new LoginService();
		List<User> userControlList =  loginService.UserCotrolList();

		request.setAttribute("userControlList", userControlList);

		int branchId = (user.getBranchId());
		int positionId = (user.getPositionId());

		if (branchId == 1 && positionId == 1) {

			request.getRequestDispatcher("userControl.jsp").forward(request, response);
//			response.sendRedirect("userControl");
		} else {

			List<String> messages = new ArrayList<String>();
			messages.add("本社所属の人事総務部ではない為、アクセス権限がありません。");
			session.setAttribute("errorMessages", messages);

			response.sendRedirect("home");
		}
	}
}