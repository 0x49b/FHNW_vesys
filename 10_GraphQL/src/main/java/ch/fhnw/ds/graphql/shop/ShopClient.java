package ch.fhnw.ds.graphql.shop;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ShopClient {
	
	static String query1 = "query {" + 
			"  getProduct(productId: 2) {" + 
			"    id" + 
			"    title" + 
			"    ratings {" + 
			"      customer {" + 
			"      	 name" + 
			"        ratings {" + 
			"          id" + 
			"        }" + 
			"      }" + 
			"    }" + 
			"  }" + 
			"}";

	public static void main(String[] args) throws Exception {
		HttpClient client = HttpClient.newHttpClient();
		
		ObjectMapper objectMapper = new ObjectMapper();
	    String requestBody = objectMapper
	          .writerWithDefaultPrettyPrinter()
	          .writeValueAsString(new Query(query1));
	    
		BodyPublisher body = HttpRequest.BodyPublishers.ofString(requestBody);
		HttpRequest request = HttpRequest.newBuilder()
				.uri(new URI("http://localhost:8080/graphql"))
				.header("Content-Type", "application/json")
				.header("Accept", "application/json")
				.POST(body)
				.build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println("Statuscode: " + response.statusCode());
		System.out.println("Headers:");
		response.headers().map().forEach((k,v) -> System.out.println(k + ": " + v));
		System.out.println("Body:");
		System.out.println(response.body());
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		System.out.println("\n"+gson.toJson(gson.fromJson(response.body(), Object.class)));
	}
	
	static class Query {
		private final String query;
		private final String variables;

		public Query(String query, String variables) {
			this.query = query;
			this.variables = variables;
		}

		public Query(String query) {
			this(query, null);
		}

		public String getQuery() {
			return query;
		}

		public String getVariables() {
			return variables;
		}
	}

}
