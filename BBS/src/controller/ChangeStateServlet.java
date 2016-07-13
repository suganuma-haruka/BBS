package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.LoginService;
import service.UserService;
import beans.User;

@WebServlet(urlPatterns = {"/changeState"})
public class ChangeStateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		int state = Integer.parseInt(request.getParameter("state"));

		new UserService().changeState(id,state);
		LoginService loginService = new LoginService();
		List<User> userControlList =  loginService.UserCotrolList();

		request.setAttribute("userControlList", userControlList);
//		List<User> userList =  new UserService().getUserList();
//		HttpSession session = request.getSession();
//
//		session.setAttribute("userList", userList);
		response.sendRedirect("userControl");
	}

}
