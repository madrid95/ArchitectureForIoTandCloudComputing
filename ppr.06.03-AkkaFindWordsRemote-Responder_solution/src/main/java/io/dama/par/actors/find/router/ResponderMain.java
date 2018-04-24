package io.dama.par.actors.find.router;

import akka.actor.ActorSystem;
import akka.actor.Props;

public class ResponderMain {

    public static void main(final String[] args) {
        ActorSystem.create("finder").actorOf(Props.create(MasterActor.class), "master");
    }
}
