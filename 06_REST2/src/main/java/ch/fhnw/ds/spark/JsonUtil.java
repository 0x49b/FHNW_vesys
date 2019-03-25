package ch.fhnw.ds.spark;

import com.google.gson.Gson;

import spark.ResponseTransformer;

public class JsonUtil {
	private static final Gson gson = new Gson();
	
	public static ResponseTransformer json() {
		return x -> gson.toJson(x);
	}
}