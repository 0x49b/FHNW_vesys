package ch.fhnw.ds.akka.chat;

public class TextMessage extends ChatMessage {
	private static final long serialVersionUID = 3405263852641974018L;

	private final String message;

	public TextMessage(String username, String message) {
		super(username);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
