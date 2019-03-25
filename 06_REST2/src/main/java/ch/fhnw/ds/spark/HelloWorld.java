package ch.fhnw.ds.spark;

import static spark.Spark.get;
import static spark.Spark.port;
//import static spark.Spark.patch;

public class HelloWorld {
    public static void main(String[] args) {
    	port(8080);	// default port: 4567
    	
    	get("/hello", "text/plain", (req, res) -> "Hello World1");
    	get("/hello", "text/html", (req, res) -> "<h1>Hello World2</h1>");
        get("/hello", (req, res) -> req.pathInfo());
        
        get("/hello/:name", (req, res) -> "Hello " + req.params(":name"));
        get("say/*/to/*", (req, res) -> "number of splat params: " + req.splat().length);
        get("/redirect", (req,res) -> { res.redirect("/hello"); return null; });
    }
}

// curl -X GET localhost:8080/hello 
// => schickt die Anfrage mit Accept: */*