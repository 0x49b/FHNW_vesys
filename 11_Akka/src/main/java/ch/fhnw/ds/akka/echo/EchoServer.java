package ch.fhnw.ds.akka.echo;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.AbstractActor;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class EchoServer {

	public static void main(String[] args) {
		Config config = ConfigFactory.load().getConfig("EchoServer");
		ActorSystem system = ActorSystem.create("EchoApplication", config);
		system.actorOf(Props.create(EchoActor.class), "EchoServer");
		System.out.println("Started Echo Server");
	}

	static class EchoActor extends AbstractActor {

		@Override
		public Receive createReceive() {
			return receiveBuilder()
				.match(String.class, msg -> {
					System.out.println(getSender());
					getSender().tell("Echo: " + msg, getSelf());
				})
				.matchAny(msg -> {
					System.out.println("UnHandled Message Received");
					unhandled(msg);
				})
				.build();
		}

	}

}
