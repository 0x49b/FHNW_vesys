package ch.fhnw.ds.rest.transport;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class Client2 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		WebTarget r = client.target("http://transport.opendata.ch/v1/connections?from=Brugg+AG&to=Zurich");

		// Access the connections from Brugg to Zürich and print Depature/Arrival/Time for each connection returned
		// Connections res = r.request().accept("application/json").get(Connections.class);
	}

}
