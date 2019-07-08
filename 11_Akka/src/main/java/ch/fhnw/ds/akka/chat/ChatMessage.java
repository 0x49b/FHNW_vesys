package ch.fhnw.ds.akka.chat;

import java.io.*;

public abstract class ChatMessage implements Serializable {
	private static final long serialVersionUID = 1643347027166884538L;

	private final String username;

	public ChatMessage(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
}
