package ch.fhnw.ds.rest.msg.server;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Server2 {

	public static void main(String[] args) throws Exception {

		URI baseUri = new URI("http://localhost:9998/");

		Map<String, Object> config = new HashMap<>();
		config.put("jersey.config.server.provider.packages", "ch.fhnw.ds.rest.msg.resources"); // package with resource classes
	             	
		// @Singleton annotations will be respected 
	    ResourceConfig rc = new ResourceConfig().setProperties(config);
	    
    	// Create and start the JDK HttpServer with the Jersey application
    	JdkHttpServerFactory.createHttpServer(baseUri, rc);
	}
	
}
