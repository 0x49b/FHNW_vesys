package ch.fhnw.ds.rest.transport;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class Client1 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Client c = ClientBuilder.newClient();
		WebTarget r = c.target("http://transport.opendata.ch/v1/locations?x=47.482&y=8.211785");

		// Access all nearby Stations around the given coordinates and print their names
		// Stations res = r.request().accept("application/json").get(Stations.class);
	}

}
