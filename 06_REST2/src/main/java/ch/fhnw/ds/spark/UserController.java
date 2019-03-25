package ch.fhnw.ds.spark;

import static ch.fhnw.ds.spark.JsonUtil.json;
import static spark.Spark.after;
import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.delete;

import com.google.gson.Gson;

public class UserController {

	public UserController(final UserService userService) {
		get("/users", (req, res) -> userService.getAllUsers(), json());

		post("/users", (req, res) -> {
			User u = userService.createUser(req.queryParams("name"), req.queryParams("email"));
			res.header("Location", "/users/"+u.getId());
			halt(201);
			return null;
		});
		
		get("/users/:id", (req, res) -> {
			String id = req.params(":id");
			User user = userService.getUser(id);
			if (user != null) {
				return json().render(user);
			} else {
				halt(404, String.format("<html><body><h1>No user with id %s found</h1></body></html>", id));
				return null;
			}
		});

		put("/users/:id", (req, res) -> {
			String id = req.params(":id");
			User u = new Gson().fromJson(req.body(), User.class);
			if(!id.equals(u.getId())) {
				halt(400, "Bad request");
				return null;
			} else if(userService.getUser(u.getId()) == null) {
				halt(404, String.format("<html><body><h1>No user with id %s found</h1></body></html>", id));
				return null;
			} else {
				return json().render(userService.updateUser(u));
			}
		});
		
		delete("/users/:id", (req, res) -> {
			var id = req.params(":id");
			var u = userService.getUser(id);
			if(u == null) {
				halt(404, String.format("<html><body><h1>No user with id %s found</h1></body></html>", id));
				return null;
			} else {
				userService.deleteUser(u);
				halt(204);
				return null;
			}
		});

		// executed after any matching route except for halt answers
		after((req, res) -> {
			System.out.println( "after: " + res.type());
			if(res.type() == null) res.type("application/json");
		});
	}

}