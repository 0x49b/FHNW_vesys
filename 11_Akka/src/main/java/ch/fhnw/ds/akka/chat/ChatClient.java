package ch.fhnw.ds.akka.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class ChatClient {

	public static void main(String[] args) {
		System.out.print("Username: ");
		String username = readInput();

		Config config = ConfigFactory
				.parseString("akka.remote.netty.tcp.port=0")
				.withFallback(ConfigFactory.load().getConfig("ChatConfig"));
		System.out.println(config);

		ActorSystem system = ActorSystem.create("ChatApplication", config);
		ActorRef client = system.actorOf(Props.create(ChatActor.class), "ChatActor");
		System.out.println("Started Chat Client");

		ActorSelection serverActor = system.actorSelection("akka.tcp://ChatApplication@127.0.0.1:2552/user/ChatServer");
		// ActorSelection serverActor = system.actorSelection("akka.tcp://ChatApplication@86.119.38.130:2552/user/ChatServer");
		serverActor.tell(new LoginMessage(username), client);
		while (true) {
			String message = readInput();
			if (message.trim().equals("bye")) {
				serverActor.tell(new LogoutMessage(username), client);
				break;
			}
			serverActor.tell(new TextMessage(username, message), client);
		}
	}

	static class ChatActor extends AbstractActor {

		@Override
		public Receive createReceive() {
			return receiveBuilder()
					.match(String.class, event -> System.out.println("> " + event))
					.match(LogoutMessage.class, event -> getContext().system().terminate())
					.matchAny(event -> {
				System.out.println("UnHandled Message Received");
				unhandled(event);
			}).build();
		}

	}

	private static String readInput() {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		try {
			line = input.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}

}
