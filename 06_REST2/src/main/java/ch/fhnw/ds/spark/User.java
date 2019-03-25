package ch.fhnw.ds.spark;

public class User {
	private final String id;
	private final String name;
	private final String email;

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