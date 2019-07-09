package ch.fhnw.ds.graphql.links;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class StartServlet {

	public static void main(String[] args) throws LifecycleException {
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8081);

        // adds a webapp directory under a particular root name
        String webappDirLocation = "src/main/webapp/";
        Context context = tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());

        String servletName = "ShopEndpoint";
		String urlPattern = "/graphql";

		// adds a servlet (comparable to web.xml)
		Tomcat.addServlet(context, servletName, new LinksEndpoint());
		context.addServletMappingDecoded(urlPattern, servletName);

		tomcat.start();
		tomcat.getConnector();	// http://tomcat.10.x6.nabble.com/Start-embedded-Tomcat-9-0-1-server-from-java-code-td5068985.html
		tomcat.getServer().await();
	}
}
