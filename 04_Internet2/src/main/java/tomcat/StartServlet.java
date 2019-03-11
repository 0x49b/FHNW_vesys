package tomcat;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

// Starts a single server accessible at http://localhost:8080/simple/go
public class StartServlet {

	public static void main(String[] args) throws LifecycleException {
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8080);

		Context context = tomcat.addContext("simple", new File(".").getAbsolutePath());

		@SuppressWarnings("serial")
		HttpServlet servlet = new HttpServlet() {
			@Override
			protected void doGet(HttpServletRequest req, HttpServletResponse resp)
					throws ServletException, IOException {

				PrintWriter writer = resp.getWriter();
				writer.println("<html><title>Welcome</title><body>");
				writer.println("<h1>Have a Great Day!</h1>");
				writer.println("</body></html>");
			}
		};

		String servletName = "SimpleServlet";
		String urlPattern = "/go";

		// Analog zur Definition in web.xml
		Tomcat.addServlet(context, servletName, servlet);
		context.addServletMappingDecoded(urlPattern, servletName);

		tomcat.start();
		tomcat.getConnector();	// http://tomcat.10.x6.nabble.com/Start-embedded-Tomcat-9-0-1-server-from-java-code-td5068985.html
		tomcat.getServer().await();
	}
}
