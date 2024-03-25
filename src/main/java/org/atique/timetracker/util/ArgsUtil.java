package org.atique.timetracker.util;

import org.atique.timetracker.model.Args;
import org.atique.timetracker.model.Commands;

/**
 * @author atiQue
 * @since 25'Mar 2024 at 12:50 PM
 */

public class ArgsUtil {

    public static Args parse(String commandStr) {

        String[] tokens = commandStr.split("\\s+");

        if (tokens.length < 2) {
            throw new RuntimeException("Invalid time-tracker command");
        }

        String command = tokens[1];

        Args args;
        switch (command) {
            case "init" -> args = new Args(Commands.INIT);
            case "start" -> {
                if (tokens.length < 3) throw new RuntimeException("Invalid time-tracker command");
                args = new Args(Commands.START, tokens[2], tokens.length < 4 ? "NONE" : tokens[3]);
            }
            case "stop" -> {
                if (tokens.length < 3) throw new RuntimeException("Invalid time-tracker command");
                args = new Args(Commands.STOP, tokens[2]);
            }
            case "report-task" -> args = new Args(Commands.REPORT_TASK);
            case "report-category" -> args = new Args(Commands.REPORT_CATEGORY);
            default -> throw new RuntimeException(command + " not supported");
        }

        return args;
    }
}
