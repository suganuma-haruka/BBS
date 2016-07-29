package chapter4;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/inputs")
public class InputsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");

		Map<String, String[]> parameterMap = request.getParameterMap();
		Set<Entry<String, String[]>> parameters = parameterMap.entrySet();
		for(Entry<String, String[]> parameter : parameters) {
			String key = parameter.getKey();
			String[] values = parameter.getValue();

			System.out.print(key + " : ");
			for(String value : values) {
				System.out.print(value);
				System.out.print(", ");
			}
		System.out.println();
		}

		InputValues inputsResult = createInputValues(request);

		request.setAttribute("inputsResult", inputsResult);

		RequestDispatcher dispatcher = request.getRequestDispatcher("inputs.jsp");
		dispatcher.forward(request, response);
	}

	private InputValues createInputValues(HttpServletRequest request) {
		InputValues inputResult = new InputValues();
		inputResult.setName(request.getParameter("name"));
		inputResult.setPassword(request.getParameter("password"));
		inputResult.setHidden(request.getParameter("hidden"));
		inputResult.setSex(request.getParameter("sex"));
		inputResult.setHobby(request.getParameterValues("hobby")); //checkboxはgetParameterValuesを使う
		inputResult.setNationality(request.getParameter("nationality"));
		inputResult.setLanguage(request.getParameterValues("language")); //複数選択可能なselectboxはgetParameterValuesを使う
		inputResult.setMemo(request.getParameter("memo"));

		inputResult.setSubmit1(request.getParameter("submit1"));
		inputResult.setSubmit2(request.getParameter("submit2"));
		inputResult.setImage_x(request.getParameter("image_x"));
		inputResult.setImage_y(request.getParameter("image_y"));
		return inputResult;
	}
}