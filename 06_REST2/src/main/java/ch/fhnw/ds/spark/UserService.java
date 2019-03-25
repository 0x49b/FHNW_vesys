package ch.fhnw.ds.spark;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserService {
	private final Map<String, User> users = new HashMap<>();

	{
		createUser("Dominik", "dominik.gruntz@fhnw.ch");
	}

	public Collection<User> getAllUsers() {
		return users.values();
	}

	public User getUser(String id) {
		return users.get(id);
	}

	public User createUser(String name, String email) {
		String id = UUID.randomUUID().toString();
		users.put(id, new User(id, name, email));
		return users.get(id);
	}

	public User updateUser(String id, String name, String email) {
		users.put(id, new User(id, name, email));
		return users.get(id);
	}

	public User updateUser(User u) {
		users.put(u.getId(), u);
		return u;
	}
	
	public void deleteUser(User u) {
		users.remove(u.getId());
	}
}