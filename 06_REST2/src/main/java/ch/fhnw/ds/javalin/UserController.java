package ch.fhnw.ds.javalin;

import io.javalin.Context;
import io.javalin.apibuilder.CrudHandler;

public class UserController implements CrudHandler {
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}

	public void getAllUsers(Context ctx) { getAll(ctx); }
	public void createUser(Context ctx) { create(ctx); }
	public void getUser(Context ctx) { getOne(ctx, ctx.pathParam("id")); }
	public void updateUser(Context ctx) { update(ctx, ctx.pathParam("id")); }
	public void deleteUser(Context ctx) { delete(ctx, ctx.pathParam("id")); }

	@Override
	public void create(Context ctx) {
		User u = userService.createUser(ctx.queryParam("name"), ctx.queryParam("email"));
		ctx.header("Location", "/users/"+u.getId());
		ctx.status(201);
	}

	@Override
	public void delete(Context ctx, String id) {
		var u = userService.getUser(id);
		if(u == null) {
			ctx.html(String.format("<html><body><h1>No user with id %s found</h1></body></html>", id));
			ctx.status(404);
		} else {
			userService.deleteUser(u);
			ctx.status(204);
		}
	}

	@Override
	public void getAll(Context ctx) {
		ctx.json(userService.getAllUsers());
	}

	@Override
	public void getOne(Context ctx, String id) {
		var user = userService.getUser(id);
		if(user == null) {
			ctx.status(404);
			ctx.html(String.format("<html><body><h1>No user with id %s found</h1></body></html>", id));
		} else {
			ctx.json(user);
		}
	}

	@Override
	public void update(Context ctx, String id) {
		var u = ctx.bodyAsClass(User.class);
		if(!id.equals(u.getId())) {
			ctx.status(400);
		} else if(userService.getUser(u.getId()) == null) {
			ctx.html(String.format("<html><body><h1>No user with id %s found</h1></body></html>", id));
			ctx.status(404);
		} else {
			ctx.json(userService.updateUser(u));
		}
	}

}