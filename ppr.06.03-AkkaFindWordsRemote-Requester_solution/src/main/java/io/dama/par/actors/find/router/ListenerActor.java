package io.dama.par.actors.find.router;

import akka.actor.AbstractActor;
import io.dama.par.actors.find.router.Messages.PleaseCleanupAndStop;
import io.dama.par.actors.find.router.Messages.ResultMsg;

public class ListenerActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder() //
                .match(ResultMsg.class, (msg) -> {
                    getSender().tell(new PleaseCleanupAndStop(), getSelf());
                    msg.getResult().forEach(System.out::println);
                    getContext().getSystem().terminate();
                }).build();
    }

}
