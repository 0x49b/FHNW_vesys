package ch.fhnw.ds.akka.sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class PrintTest {

	public static void main(String[] args) throws Exception {
		ActorSystem as = ActorSystem.create();
		ActorRef actor = as.actorOf(Props.create(PrintActor.class), "Printer");
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = in.readLine();
		while(line != null && !"".equals(line)) {
			if(line.equals("obj")) {
				actor.tell(new Object(), null); // null corresponds to ActorRef.noSender());
			} else {
				actor.tell(line, ActorRef.noSender());
			}
			line = in.readLine();
		}
		as.terminate();
	}

}
