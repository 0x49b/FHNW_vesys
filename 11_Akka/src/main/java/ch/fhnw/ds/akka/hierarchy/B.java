package ch.fhnw.ds.akka.hierarchy;

import akka.actor.AbstractActor;

public class B extends AbstractActor {

	@Override
	public Receive createReceive() {
		return receiveBuilder().matchAny(msg -> System.out.println("B received " + msg)).build();
	}

}