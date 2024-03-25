package org.atique.timetracker;

import org.atique.Processor;
import org.atique.timetracker.model.Args;
import org.atique.timetracker.model.Task;
import org.atique.timetracker.service.TaskService;
import org.atique.timetracker.service.TaskServiceImpl;
import org.atique.timetracker.util.ArgsUtil;

import java.io.IOException;

/**
 * @author atiQue
 * @since 25'Mar 2024 at 12:35 PM
 */

public class TimeTrackerCommandProcessor implements Processor {

    private final TaskService service = new TaskServiceImpl();

    @Override
    public void process(String commandStr) throws IOException {
        Args args = ArgsUtil.parse(commandStr);

        switch (args.getCommand()) {
            case INIT -> service.init();
            case START -> service.start(new Task(args.getTaskName(), args.getCategoryName()));
            case STOP -> service.stop(args.getTaskName());
            case REPORT_TASK -> service.reportTask();
            case REPORT_CATEGORY -> service.reportCategory();
        }
    }
}
