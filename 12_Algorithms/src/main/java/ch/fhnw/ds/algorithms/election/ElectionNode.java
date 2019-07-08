package ch.fhnw.ds.algorithms.election;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import ch.fhnw.ds.algorithms.election.ElectionTest.Reset;
import ch.fhnw.ds.algorithms.election.ElectionTest.Start;
import ch.fhnw.ds.algorithms.election.ElectionTest.Token;

public class ElectionNode extends AbstractActor {
	private final int id;

	private ActorRef next;
	private ActorRef initiator;
	private int master = Integer.MIN_VALUE;
	private int messageCount = 0;

	public ElectionNode(int id) { this.id = id; }

	@Override
	public Receive createReceive() {
		return receiveBuilder()
			.match(ActorRef.class, actor -> {
				next = actor;
			})
			.match(Start.class, value -> {
				if(master >= 0) {
					System.out.println("start is no longer possible");
				} else {
					initiator = getSender();
					master = id;
					next.tell(new Token(master), getSelf());
					messageCount++;
				}
			})
			.match(Token.class, token -> {
				if(token.value > master) {
					master = token.value;
					next.tell(new Token(master), getSelf());
					messageCount++;
				} else if(token.value == master) {
					System.out.println("hurray, I got elected " + getSelf());
					next.tell(new Reset(id), getSelf());
				}
			})
			.match(Reset.class, token -> {
				master = Integer.MIN_VALUE;
				if(token.value == id) {
					messageCount++;
					initiator.tell("its me " + id, getSelf());
					System.out.println("Message Count " + messageCount);
				} else {
					next.tell(token, getSelf());
				}
			})
			.matchAny(msg -> {
				messageCount++;
				System.out.println("UnHandled Message Received");
				unhandled(msg);
			})
			.build();
	}

}