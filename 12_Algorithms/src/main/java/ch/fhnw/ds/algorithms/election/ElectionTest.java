package ch.fhnw.ds.algorithms.election;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.dispatch.Futures;
import akka.pattern.Patterns;
import akka.util.Timeout;
import scala.concurrent.Await;
import scala.concurrent.Future;

public class ElectionTest {
	
	static class Start {}
	static class Token {
		public final int value;
		public Token(int value) { this.value = value; }
	}
	static class Reset {
		public final int value;
		public Reset(int value) { this.value = value; }
	}
	
	static final int N = 1000;
	static final int K = 10;

	public static void main(String[] args) throws Exception {
		ActorSystem as = ActorSystem.create();
		Random r = new Random();
		Timeout  timeout = new Timeout(5, TimeUnit.SECONDS);
		
		List<ActorRef> actors = IntStream.range(0, N).mapToObj(n -> as.actorOf(Props.create(ElectionNode.class, n), "Node"+n)).collect(Collectors.toList());
		for(int i = 0; i < actors.size(); i++) actors.get(i).tell(actors.get((i+1) % N), null);
		
		List<Future<Object>> list = IntStream.range(0, K)
			.mapToObj(n -> Patterns.ask(actors.get(r.nextInt(N)), new Start(), timeout))
			.collect(Collectors.toList());
		
		Future<Object> res = Futures.firstCompletedOf(list, as.dispatcher());
		String result = (String) Await.result(res, timeout.duration());
		System.out.println(result);

		as.terminate();
	}

}
