package tomcat;

import java.io.File;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

// Starts a tomcat server with a single web app accessible at http://localhost:8080/ds
public class StartServer {

    public static void main(String[] args) throws Exception {
    	int webPort = 8080;

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(webPort);

        // adds a webapp directory under a particular root name
        String webappDirLocation = "src/main/webapp/";
        StandardContext ctx = (StandardContext) tomcat.addWebapp("/ds", new File(webappDirLocation).getAbsolutePath());

        // adds  an alternative location for your "WEB-INF/classes" directory
        File additionWebInfClasses = new File("build/classes/java/main");
        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(
        		new DirResourceSet(
        				resources, 
        				"/WEB-INF/classes",	// /WEB-INF/lib/ for jars
        				 additionWebInfClasses.getAbsolutePath(), 
        				 "/"));
        ctx.setResources(resources);

        tomcat.start();
		tomcat.getConnector();	// http://tomcat.10.x6.nabble.com/Start-embedded-Tomcat-9-0-1-server-from-java-code-td5068985.html
        tomcat.getServer().await();
    }
}