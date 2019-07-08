package ch.fhnw.ds.akka.echo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.pattern.Patterns;
import akka.util.Timeout;
import scala.concurrent.Await;
import scala.concurrent.Future;

public class EchoClient {

	public static void main(String[] args) throws Exception {
		Config config = ConfigFactory.load().getConfig("EchoClient");
		System.out.println(config);

		ActorSystem system = ActorSystem.create("EchoApplication", config);

		ActorSelection serverActor = system.actorSelection("akka.tcp://EchoApplication@127.0.0.1:2553/user/EchoServer");
		// ActorSelection serverActor = system.actorSelection("akka.tcp://EchoApplication@86.119.38.130:2553/user/EchoServer");

		Timeout  timeout = new Timeout(5, TimeUnit.SECONDS);
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String message = input.readLine();
			if (message.trim().equals("")) break;
			Future<Object> res = Patterns.ask(serverActor, message, timeout);
			String result = (String) Await.result(res, timeout.duration());
			System.out.println(result);
		}
		
		system.terminate();
	}

}
