package ch.fhnw.ds.akka.chat;

public class LoginMessage extends ChatMessage {
	private static final long serialVersionUID = 7562212777032563554L;

	public LoginMessage(String username) {
		super(username);
	}

}
