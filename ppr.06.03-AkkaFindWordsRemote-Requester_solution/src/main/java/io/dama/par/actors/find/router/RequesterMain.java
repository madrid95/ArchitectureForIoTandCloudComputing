package io.dama.par.actors.find.router;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;
import io.dama.par.actors.find.router.Messages.FindMsg;

public class RequesterMain {

    public static void main(final String[] args) {
        final ActorSystem system = ActorSystem.create("finder");
        final ActorRef listener = system.actorOf(Props.create(ListenerActor.class), "listener");
        final String[] files = { "test5.txt", "test6.txt", "test7.txt", "test8.txt" };
        final FindMsg msg = new FindMsg(files, "Erlang");
        final ActorSelection remoteMaster = system.actorSelection("akka.tcp://finder@127.0.0.1:4711/user/master");
        remoteMaster.tell(msg, listener);
    }
}
