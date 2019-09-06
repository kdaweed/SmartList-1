package Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class MyFormatter extends Formatter {

    @Override
    public String format(LogRecord record) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");
        StringBuilder builder = new StringBuilder();
        builder.append(getColor(record.getMessage(), record.getLevel().toString()));
        builder.append("[").append(record.getLevel()).append("]");
        builder.append("[").append(df.format(new Date(record.getMillis()))).append("]");
        builder.append("[").append(record.getSourceClassName()).append(".");
        builder.append(record.getSourceMethodName()).append("] - ");

        builder.append(formatMessage(record));
        builder.append("\n");
        return builder.toString();
    }

    private String getColor(String msg, String level) {
        /* check level of log message */
        if (level.contains("SEVERE")) {
            return "\u001B[31m";    // red, SEVERE
        } else if (level.contains("WARNING")) {
            return "\u001B[33m";    // yellow, WARNING
        }

        /* check content of log message */
        String[] redWords = {"failed"};
        for (String str : Arrays.asList(redWords)) {
            if (msg.contains(str)) { return "\u001B[31m"; }
        }

        String[] yellowWords = {};
        for (String str : Arrays.asList(yellowWords)) {
            if (msg.contains(str)) { return "\u001B[33m"; }
        }

        String[] greenWords = {"success", "succeed", "successful", "successfully", "passed"};
        for (String str : Arrays.asList(greenWords)) {
            if (msg.contains(str)) { return "\u001B[32m"; }
        }

        return "\u001B[37m";    // white, INFO
    }
}
