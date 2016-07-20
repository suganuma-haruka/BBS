package filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;

@WebFilter(filterName="AccessFilter", urlPatterns = {"/settings", "/signup", "/userControl"})
public class AccessFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpSession session = ((HttpServletRequest) request).getSession();
		if(session.getAttribute("loginUser") != null) {

			User user = (User) session.getAttribute("loginUser");

			int positionId = user.getPositionId();
			int branchId = user.getBranchId();

			if(positionId != 1 && branchId != 1) {
				List<String> messages = new ArrayList<String>();
				messages.add("本社所属の人事総務部ではない為、アクセス権限がありません。");
				session.setAttribute("errorMessages", messages);
				((HttpServletResponse) response).sendRedirect("home");
				return;
			}
		} else {
			List<String> errorMessages = new ArrayList<String>();
			errorMessages.add("ログインしてください。");
			session.setAttribute("errorMessages", errorMessages);
			((HttpServletResponse) response).sendRedirect("login");
			return;
		}

		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

	@Override
	public void destroy() {
	}

}
