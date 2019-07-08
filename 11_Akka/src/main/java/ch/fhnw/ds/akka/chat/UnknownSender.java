package ch.fhnw.ds.akka.chat;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class UnknownSender {

	public static void main(String[] args) throws Exception {
		Config config = ConfigFactory.parseString("akka.actor.provider=akka.remote.RemoteActorRefProvider");
		ActorSystem as = ActorSystem.create("ChatApplication", config);

		ActorSelection b = as.actorSelection("akka.tcp://ChatApplication@127.0.0.1:2552/user/ChatServer");
		b.tell(new TextMessage("Unknown", "Hello"), ActorRef.noSender());

		System.in.read();
		as.terminate();
	}

}
