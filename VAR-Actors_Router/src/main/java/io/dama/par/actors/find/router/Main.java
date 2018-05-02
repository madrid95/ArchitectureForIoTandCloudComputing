package io.dama.par.actors.find.router;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import io.dama.par.actors.find.router.Messages.FindMsg;

public class Main {

    public static void main(final String[] args) {
        final ActorSystem system = ActorSystem.create();
        final ActorRef master = system.actorOf(Props.create(MasterActor.class), "master");
        final String[] files = { "test1.txt", "test2.txt", "test3.txt", "test4.txt", "test5.txt", "test6.txt",
                "test7.txt", "test8.txt" };
        final FindMsg msg = new FindMsg(files, "Erlang");
        master.tell(msg, null);
    }
}
