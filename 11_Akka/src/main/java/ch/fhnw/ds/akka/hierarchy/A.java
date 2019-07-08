package ch.fhnw.ds.akka.hierarchy;

import akka.actor.AbstractActor;
import akka.actor.Props;

public class A extends AbstractActor {

	@Override
	public void preStart() throws Exception {
		getContext().actorOf(Props.create(B.class), "b1");
		getContext().actorOf(Props.create(B.class), "b2");
		super.preStart();
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder().matchAny(msg -> System.out.println("A received " + msg)).build();
	}

}