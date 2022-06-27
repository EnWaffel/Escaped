package de.projectp.escaped;

import de.enwaffel.randomutils.file.FileOrPath;
import de.projectp.escaped.game.client.Escaped;
import de.projectp.escaped.resource.ResourceManager;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class Main {

    public static void main(String[] args) {
        G.logger.addHandler(new LogHandler());
        new ResourceManager().loadPack(FileOrPath.path("src/main/resources/pack"));
        new Escaped().run();
    }

    private static class LogHandler extends Handler {

        @Override
        public void publish(LogRecord record) {

        }

        @Override
        public void flush() {

        }

        @Override
        public void close() throws SecurityException {

        }

    }

}
