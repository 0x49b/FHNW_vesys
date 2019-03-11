package ch.fhnw.ds.jsonrpc;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.jsonrpc4j.JsonRpcServer;

public class Server {

	@SuppressWarnings("serial")
	public static void main(String[] args) throws LifecycleException {
		Service service = new ServiceImpl();
		JsonRpcServer server = new JsonRpcServer(new ObjectMapper(), service, Service.class);

		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8080);

		Context context = tomcat.addContext("json-rpc", new File(".").getAbsolutePath());

		String servletName = "JSON-RPC-Servlet";
		String urlPattern = "/*";

		Tomcat.addServlet(context, servletName, new HttpServlet() {
			@Override
			protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				ServletOutputStream out = resp.getOutputStream();
				out.write("JSON-RPC endpoint for Bank Service".getBytes());
			}

			@Override
			protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
				server.handle(request, response);
			}
		});
		context.addServletMappingDecoded(urlPattern, servletName);

		tomcat.start();
		tomcat.getConnector();	// http://tomcat.10.x6.nabble.com/Start-embedded-Tomcat-9-0-1-server-from-java-code-td5068985.html
		tomcat.getServer().await();
	}
	
}
