package ch.fhnw.ds.rest.msg.server;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import ch.fhnw.ds.rest.msg.resources.MsgResource;


public class Server3 {

	public static void main(String[] args) throws Exception {

		URI baseUri = new URI("http://localhost:9998/");

		// @Singleton annotation is respected, but classes without a @Singleton annotation can also 
		// be returned as singletons over the application definition
		ResourceConfig rc = ResourceConfig.forApplication(new MsgApplication());

    	// Create and start the JDK HttpServer with the Jersey application
    	JdkHttpServerFactory.createHttpServer(baseUri, rc);
	}

	static public class MsgApplication extends Application {
		private final Set<Object> singletons = new HashSet<Object>();
		private final Set<Class<?>> classes = new HashSet<Class<?>>();

		public MsgApplication() {
			singletons.add(new MsgResource());
//			classes.add(MsgResource.class);
		}

		@Override
		public Set<Class<?>> getClasses() {
			return classes;
		}

		@Override
		public Set<Object> getSingletons() {
			return singletons;
		}
	}

}
