package ch.fhnw.ds.rest.msg.server;

import java.net.URI;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import ch.fhnw.ds.rest.msg.resources.MsgResource;

public class Server0 {

	public static void main(String[] args) throws Exception {
		URI baseUri = new URI("http://localhost:9998/");
		
		// @Singleton annotations will be respected 
	    ResourceConfig rc = new ResourceConfig(MsgResource.class);

		// Create and start the JDK HttpServer with the Jersey application
		JdkHttpServerFactory.createHttpServer(baseUri, rc);
	}

}
