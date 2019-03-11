package ch.fhnw.ds.jsonrpc;

import java.net.URL;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.ProxyUtil;

public class Client {
	
	public static void main(String[] args) throws Exception {
		String path = "http://localhost:8080/json-rpc";
		JsonRpcHttpClient client = new JsonRpcHttpClient(new URL(path));
		Service service = ProxyUtil.createClientProxy(Client.class.getClassLoader(), Service.class, client);
		System.out.println(service.echo("Hello"));
		System.out.println(service.echo("ex"));
	}

}

