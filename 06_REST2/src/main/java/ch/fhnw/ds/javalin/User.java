package ch.fhnw.ds.javalin;

public class User {
	private String id;
	private String name;
	private String email;

	public User() {}
	public User(String id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}
	
	public String toString() {
		return String.format("User(id=%s, name=%s, email=%s)", id, name, email);
	}
}