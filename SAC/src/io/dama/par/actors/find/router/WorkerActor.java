package io.dama.par.actors.find.router;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import akka.actor.AbstractActor;
import io.dama.par.actors.find.router.Messages.PleaseCleanupAndStop;
import io.dama.par.actors.find.router.Messages.WorkMsg;

public class WorkerActor extends AbstractActor {
    private Path    path;
    private Pattern searchPattern;

    @Override
    public Receive createReceive() {
        return receiveBuilder() //
                .match(WorkMsg.class, this::handleWorkMsg) //
                .match(PleaseCleanupAndStop.class, (m) -> getContext().stop(getSelf())) //
                .build();
    }

    private void handleWorkMsg(final WorkMsg msg) throws IOException {
        this.path = Paths.get(msg.getFilename());
        this.searchPattern = Pattern.compile(".*\\b" + msg.getSearchword() + "\\b.*");
        final List<String> result = search();
        getSender().tell(new Messages.ResultMsg(result), getSelf());
    }

    public List<String> search() throws IOException {
        final List<String> result = new ArrayList<>();
        final List<String> lines = Files.readAllLines(this.path, StandardCharsets.UTF_8);
        int count = 0;
        for (final String line : lines) {
            count++;
            if (this.searchPattern.matcher(line).matches()) {
                result.add(this.path + " " + count + " : " + line);
            }
        }
        return result;
    }
}
