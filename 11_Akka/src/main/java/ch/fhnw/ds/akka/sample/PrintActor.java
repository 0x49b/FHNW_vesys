package ch.fhnw.ds.akka.sample;

import java.util.concurrent.TimeUnit;

import akka.actor.AbstractActor;
import akka.actor.ReceiveTimeout;
import scala.concurrent.duration.Duration;

public class PrintActor extends AbstractActor {
	private int requestCounter = 0;

	@Override
	public void preStart() throws Exception {
		getContext().setReceiveTimeout(Duration.create(5, TimeUnit.SECONDS));
		super.preStart();
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder().matchAny(t -> onReceive(t)).build();
	}

	private void onReceive(Object msg) {
		requestCounter++;
		if (msg instanceof String) {
			String txt = (String) msg;
			System.err.printf("%d: received message `%s` from %s%n", requestCounter, txt, getSender());
			if (txt.equals("echo")) {
				getSelf().tell(txt + txt, getSelf());
			} else if (txt.startsWith("forward")) {
				getSelf().forward(txt.substring("forward".length()), getContext());
			}
		} else if (msg instanceof ReceiveTimeout) {
			System.out.printf("%d: received timeout message from %s%n", requestCounter, getSender());
		} else {
			System.err.printf("%d: received unknown message from %s%n", requestCounter, getSender());
		}
	}

}