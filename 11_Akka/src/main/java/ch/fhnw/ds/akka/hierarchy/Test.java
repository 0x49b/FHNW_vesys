package ch.fhnw.ds.akka.hierarchy;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Test {

	public static void main(String[] args) throws Exception {
		ActorSystem as = ActorSystem.create("as");
		ActorRef a = as.actorOf(Props.create(A.class), "a");

		ActorSelection b1 = as.actorSelection("akka://as/user/a/b1");
		a.tell("A", ActorRef.noSender());
		b1.tell("B", ActorRef.noSender());

		as.terminate();
	}

}
