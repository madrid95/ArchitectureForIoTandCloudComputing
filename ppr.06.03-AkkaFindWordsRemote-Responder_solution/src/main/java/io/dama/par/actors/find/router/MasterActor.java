package io.dama.par.actors.find.router;

import java.util.ArrayList;
import java.util.List;

import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;
import io.dama.par.actors.find.router.Messages.FindMsg;
import io.dama.par.actors.find.router.Messages.PleaseCleanupAndStop;
import io.dama.par.actors.find.router.Messages.ResultMsg;
import io.dama.par.actors.find.router.Messages.WorkMsg;

public class MasterActor extends AbstractLoggingActor {
    private static final int   WORKER_NUM = 5;
    private int                numOfChild;
    private final List<String> result     = new ArrayList<>();
    private final List<Routee> routees;
    private final Router       router;
    private ActorRef           requester;

    public MasterActor() {
        this.routees = new ArrayList<>();
        for (int i = 0; i < MasterActor.WORKER_NUM; i++) {
            final ActorRef r = getContext().actorOf(Props.create(WorkerActor.class), "worker" + i);
            getContext().watch(r);
            this.routees.add(new ActorRefRoutee(r));
        }
        this.router = new Router(new RoundRobinRoutingLogic(), this.routees);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder() //
                .match(FindMsg.class, this::handleFindMsg) //
                .match(ResultMsg.class, this::handleResultMsg) //
                .match(PleaseCleanupAndStop.class, (m) -> {
                    getContext().getSystem().terminate();
                }) //
                .build();
    }

    private void handleFindMsg(final FindMsg msg) {
        final List<String> filenames = msg.getFilenames();
        final String searchword = msg.getSearchword();
        this.numOfChild = filenames.size();
        this.requester = getSender();
        for (final String filename : filenames) {
            final WorkMsg job = new WorkMsg(filename, searchword);
            this.router.route(job, getSelf());
        }

    }

    private void handleResultMsg(final ResultMsg msg) {
        this.numOfChild--;
        this.result.addAll(msg.getResult());
        if (this.numOfChild == 0) {
            this.requester.tell(new ResultMsg(this.result), getSelf());
        }
    }

}
