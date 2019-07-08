package ch.fhnw.ds.akka.sample.remote;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.ActorSystem;
import akka.actor.Props;
import ch.fhnw.ds.akka.sample.PrintActor;

public class PrintServer {

	public static void main(String[] args) {
		Config config = ConfigFactory.load().getConfig("PrintConfig"); 
		ActorSystem sys = ActorSystem.create("PrintApplication", config);
		sys.actorOf(Props.create(PrintActor.class), "PrintServer");
		System.out.println("Started Print Application");
	}

}

