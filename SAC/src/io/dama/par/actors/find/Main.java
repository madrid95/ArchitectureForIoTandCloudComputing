package io.dama.par.actors.find;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import io.dama.par.actors.find.Messages.FindMsg;

public class Main {

    public static void main(final String[] args) {
        final ActorSystem system = ActorSystem.create();
        final ActorRef master = system.actorOf(Props.create(MasterActor.class), "worker");
        final String[] files = { "test1.txt", "test2.txt", "test3.txt", "test4.txt" };
        final FindMsg msg = new FindMsg(files, "Erlang");
        master.tell(msg, null);
    }
}
