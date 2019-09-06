package Logger;

import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

public class MyLogger {
    public static Logger logger;

    public static Logger getLogger(String name) {
        logger = Logger.getLogger(name);

        logger.setUseParentHandlers(false);

        MyFormatter formatter = new MyFormatter();
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(formatter);

        logger.addHandler(handler);
        return logger;
    }
}
