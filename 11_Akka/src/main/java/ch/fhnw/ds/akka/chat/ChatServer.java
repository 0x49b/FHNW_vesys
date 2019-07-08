package ch.fhnw.ds.akka.chat;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class ChatServer {

	public static void main(String[] args) {
		Config config = ConfigFactory.load().getConfig("ChatConfig");
		ActorSystem system = ActorSystem.create("ChatApplication", config);
		system.actorOf(Props.create(ChatActor.class), "ChatServer");
		System.out.println("Started Chat Application");
	}

	static class ChatActor extends AbstractActor {

		private final Map<String, ActorRef> sessions = new HashMap<>();

		@Override
		public Receive createReceive() {
			return receiveBuilder()
				.match(LoginMessage.class, msg -> doLogin(msg))
				.match(TextMessage.class, msg -> {
					if("ex".equals(msg.getMessage())) {
						throw new RuntimeException("unsupported message");
					} else if("error".equals(msg.getMessage())) {
						throw new Error();
					} else {
						broadcastMessage(msg);
					}
				})
				.match(LogoutMessage.class, msg -> doLogout(msg))
				.matchAny(msg -> {
					System.out.println("UnHandled Message Received");
					unhandled(msg);
				})
				.build();
		}

		private void doLogin(LoginMessage login) {
			String username = login.getUsername();
			ActorRef sender = getSender();
			sessions.put(username, sender);
			System.out.println(username + " just logged in");
			broadcastMessage(username, "I just logged in");
		}

		private void broadcastMessage(TextMessage textMessage) {
			broadcastMessage(textMessage.getUsername(), textMessage.getMessage());
		}

		private void broadcastMessage(String sender, String message) {
			System.out.println(sender + " sent: " + message);
			for (Entry<String, ActorRef> entry : sessions.entrySet()) {
				if (!entry.getKey().equals(sender)) {
					entry.getValue().tell(sender + ": " + message, getSelf());
				}
			}
		}

		private void doLogout(LogoutMessage logout) {
			String username = logout.getUsername();
			sessions.remove(username);
			System.out.println(username + " just logged out");
			broadcastMessage(username, "I just logged out");
			getSender().tell(logout, getSelf());
		}

		@Override
		public void preStart() throws Exception {
			System.out.printf("preStart on %s [%s]\n", this.getClass().getSimpleName(), System.identityHashCode(this));
			super.preStart();
		}

		@Override
		public void preRestart(Throwable reason, Optional<Object> message) throws Exception {
			System.out.printf("preRestart on %s [%s]\n", this.getClass().getSimpleName(), System.identityHashCode(this));
			super.preRestart(reason, message);
		}

		@Override
		public void postRestart(Throwable reason) throws Exception {
			System.out.printf("postRestart on %s [%s]\n", this.getClass().getSimpleName(), System.identityHashCode(this));
			super.postRestart(reason);
		}

		@Override
		public void postStop() throws Exception {
			System.out.printf("postStop on %s [%s]\n", this.getClass().getSimpleName(), System.identityHashCode(this));
			super.postStop();
		}

	}

}
