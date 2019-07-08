package ch.fhnw.ds.algorithms.gcd;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;

public class GcdActor extends AbstractActor {
	private BigInteger n;
	private final Set<ActorRef> neighbours = new HashSet<>();
	
	public GcdActor(BigInteger n) { 
		if(n.signum() <= 0) throw new IllegalArgumentException();
		this.n = n; 
		System.out.printf("%s Initial Value: %d%n", getSelf(), n);
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder()
			.match(ActorRef.class, actor -> {
				neighbours.add(actor);
				if(neighbours.size() == 2) {
					neighbours.forEach(a -> a.tell(n, getSelf()));
				}
			})
			.match(BigInteger.class, value -> {
				System.out.printf("%s received value %d from %s%n", getSelf(), value, getSender());
				if (n.subtract(value).signum() > 0) { // if(n > value)
					// n = ((n-1) % value) + 1	
					n = n.subtract(BigInteger.ONE).mod(value).add(BigInteger.ONE);
					System.out.printf("%s current value: %d%n", getSelf(), n);
					neighbours.stream().forEach(a -> a.tell(n, getSelf()));
				}
			})
			.matchAny(msg -> {
				System.out.println("UnHandled Message Received");
				unhandled(msg);
			})
			.build();
	}
	
	@SuppressWarnings("unused")
	private void tell(ActorRef to, Object msg, ActorRef from) {
		System.out.printf("%s => %s [%s]%n", from, to, msg);
		to.tell(msg, from);
	}

}