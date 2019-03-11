package ch.fhnw.ds.servlet.echo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EchoServlet extends HttpServlet {
	private static final long serialVersionUID = -718514557424640898L;
	private String title;

	public void init() {
		title = getServletConfig().getInitParameter("title");
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		System.out.println(">> " + getClass().getName() + " " + new Date());
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.printf("<html><body><b>%s</b></br><pre>%n", title);

		out.println("Properties:");
		out.println("getMethod:        " + request.getMethod());
		out.println("getContentLength: " + request.getContentLength());
		out.println("getContentType:   " + request.getContentType());
		out.println("getProtocol:      " + request.getProtocol());
		out.println("getRemoteAddr:    " + request.getRemoteAddr());
		out.println("getRemotePort:    " + request.getRemotePort());
		out.println("getRemoteHost:    " + request.getRemoteHost());
		out.println("getRemoteUser:    " + request.getRemoteUser());
		out.println("getServerName:    " + request.getServerName());
		out.println("getAuthType:      " + request.getAuthType());
		out.println("getQueryString:   " + request.getQueryString());
		out.println("getRequestURI:    " + request.getRequestURI());
		out.println("getRequestURL:    " + request.getRequestURL());
		out.println("getServletPath:   " + request.getServletPath());
		out.println("getContextPath:   " + request.getContextPath());

		out.println("\nHeaders:");
		Enumeration<String> e = request.getHeaderNames();
		while (e.hasMoreElements()) {
			String name = (String) e.nextElement();
			out.println(name + " = " + request.getHeader(name));
		}
		
		out.println("\nParameters:");
		e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String name = (String) e.nextElement();
			out.print(name + " = ");
			for (String value : request.getParameterValues(name)) {
				out.print(value + ", ");
			}
			out.println();
		}

		out.println("</pre></body></html>");
		System.out.println("<< " + getClass().getName());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
