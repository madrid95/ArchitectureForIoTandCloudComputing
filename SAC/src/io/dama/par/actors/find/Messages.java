package io.dama.par.actors.find;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Messages {
    static class PleaseCleanupAndStop {
    }

    static class FindMsg {
        private final List<String> fileNames;
        private final String       searchword;

        public FindMsg(final String[] filenames, final String searchword) {
            this.fileNames = Collections.unmodifiableList(Arrays.asList(filenames));
            this.searchword = searchword;
        }

        public List<String> getFilenames() {
            return this.fileNames;
        }

        public String getSearchword() {
            return this.searchword;
        }
    }

    static class WorkMsg {
        private final String filename;
        private final String searchword;

        public WorkMsg(final String filename, final String seachword) {
            this.filename = filename;
            this.searchword = seachword;
        }

        public String getFilename() {
            return this.filename;
        }

        public String getSearchword() {
            return this.searchword;
        }
    }

    static class ResultMsg {
        private final List<String> result;

        public ResultMsg(final List<String> result) {
            this.result = Collections.unmodifiableList(result);
        }

        public List<String> getResult() {
            return this.result;
        }
    }
}
