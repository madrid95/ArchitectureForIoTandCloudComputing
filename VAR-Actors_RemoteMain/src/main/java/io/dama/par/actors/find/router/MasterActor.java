package io.dama.par.actors.find.router;

import java.util.ArrayList;
import java.util.List;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.routing.FromConfig;
import io.dama.par.actors.find.router.Messages.FindMsg;
import io.dama.par.actors.find.router.Messages.ResultMsg;
import io.dama.par.actors.find.router.Messages.WorkMsg;

public class MasterActor extends AbstractActor {
	private int numOfChild;
	private final List<String> result = new ArrayList<>();
	private final ActorRef listener;
	private final ActorRef router;

	public MasterActor() {
		this.listener = getContext().actorOf(Props.create(ListenerActor.class), "listener");
		this.router = getContext().actorOf(FromConfig.getInstance().props(Props.create(WorkerActor.class)), "routerGroup");
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder() //
				.match(FindMsg.class, this::handleFindMsg) //
				.match(ResultMsg.class, this::handleResultMsg) //
				.build();
	}

	private void handleFindMsg(final FindMsg msg) {
		final List<String> filenames = msg.getFilenames();
		final String searchword = msg.getSearchword();
		this.numOfChild = msg.getFilenames().size();
		for (final String filename : filenames) {
			final WorkMsg job = new WorkMsg(filename, searchword);
			this.router.tell(job, getSelf());
		}

	}

	private void handleResultMsg(final ResultMsg msg) {
		this.numOfChild--;
		this.result.addAll(msg.getResult());
		if (this.numOfChild == 0) {
			this.listener.tell(new ResultMsg(this.result), getSelf());
			getContext().getSystem().terminate();
		}

	}

}
