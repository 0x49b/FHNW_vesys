package ch.fhnw.ds.algorithms.gcd;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class GcdTest {

	public static void main(String[] args) throws Exception {
		ActorSystem as = ActorSystem.create();
		
		List<Integer> values = List.of(108, 76, 12, 60, 36);
		List<ActorRef> actors = IntStream.range(0, values.size())
				.mapToObj(n -> as.actorOf(Props.create(GcdActor.class, BigInteger.valueOf(values.get(n))), "GCD"+n))
				.collect(Collectors.toList());
		
		final int size = actors.size();
		for(int i = 0; i < size; i++) {
			actors.get(i).tell(actors.get((i+1) % size), null);
			actors.get(i).tell(actors.get((size+i-1) % size), null);
		}
		
	}

}
