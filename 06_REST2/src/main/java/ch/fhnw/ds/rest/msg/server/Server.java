package ch.fhnw.ds.rest.msg.server;

import java.net.URI;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Server {

	public static void main(String[] args) throws Exception {

		URI baseUri = new URI("http://localhost:9998/");

		// create a resource config that scans for JAX-RS resources and providers in ch.fhnw.ds.rest.msg.resources package
		// @Singleton annotations will be respected 
	    ResourceConfig rc = new ResourceConfig().packages("ch.fhnw.ds.rest.msg.server");
		
    	// Create and start the JDK HttpServer with the Jersey application
    	JdkHttpServerFactory.createHttpServer(baseUri, rc);
	}

}
