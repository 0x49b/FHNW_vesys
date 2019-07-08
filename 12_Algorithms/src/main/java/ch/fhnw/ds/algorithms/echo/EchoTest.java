package ch.fhnw.ds.algorithms.echo;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.util.Timeout;
import scala.concurrent.Await;
import scala.concurrent.Future;

public class EchoTest {
	
	static class Start{}
	static class Token{}

	public static void main(String[] args) throws Exception {
		ActorSystem as = ActorSystem.create();
		
		List<ActorRef> actors = IntStream.range(0, 8)
				.mapToObj(n -> as.actorOf(Props.create(EchoNode.class), "Node"+n))
				.collect(Collectors.toList());

		addEdge(actors, 0, 1);
		addEdge(actors, 0, 7);
		addEdge(actors, 1, 2);
		addEdge(actors, 1, 3);
		addEdge(actors, 2, 4);
		addEdge(actors, 3, 4);
		addEdge(actors, 4, 5);
		addEdge(actors, 5, 6);
		addEdge(actors, 7, 5);
		
		Timeout  timeout = new Timeout(5, TimeUnit.SECONDS);
		Future<Object> res = Patterns.ask(actors.get(0), new Start(), timeout);
		Object result = Await.result(res, timeout.duration());
		System.out.println(result);

		as.terminate();
	}
	
	static void addEdge(List<ActorRef> actors, int from, int to) {
		actors.get(from).tell(actors.get(to), null);
		actors.get(to).tell(actors.get(from), null);
	}

}
