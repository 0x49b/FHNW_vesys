package ch.fhnw.ds.akka.chat;

public class LogoutMessage extends ChatMessage {
	private static final long serialVersionUID = -2695534311288332L;

	public LogoutMessage(String username) {
		super(username);
	}
}
