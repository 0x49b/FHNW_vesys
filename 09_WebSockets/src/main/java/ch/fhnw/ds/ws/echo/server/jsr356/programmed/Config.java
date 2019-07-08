package ch.fhnw.ds.ws.echo.server.jsr356.programmed;

import java.util.HashSet;
import java.util.Set;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;

public class Config implements ServerApplicationConfig {

	@Override
	public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> scanned) {
		Set<ServerEndpointConfig> result = new HashSet<>();
		result.add(ServerEndpointConfig.Builder.create(EchoServer.class, "/echo")
				// .subprotocols(subprotocols)
				// .configurator(new MyServerConfigurator())
				.build());

		return result;
	}

	@Override
	public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned) {
		Set<Class<?>> results = new HashSet<>();
		return results;
	}
}
