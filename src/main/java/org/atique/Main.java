package org.atique;

import org.atique.timetracker.TimeTrackerCommandProcessor;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author atiQue
 * @since 24'Mar 2024 at 12:36 PM
 */

public class Main {

    public static void main(String[] args) {

        Logger.log("Welcome to LLD cli");

        Processor timeTrackerProcessor = new TimeTrackerCommandProcessor();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.printf(">");
            String command = scanner.nextLine();

            String[] tokens = command.split("\\s+");
            if ("time-tracker".equals(tokens[0])) {
                try {
                    timeTrackerProcessor.process(command);
                } catch (RuntimeException e) {
                    Logger.log(e.getMessage());
                } catch (IOException e) {
                    Logger.log(e.getMessage());
                }
            }else {
                Logger.log("Invalid base command");
            }
        }
    }
}