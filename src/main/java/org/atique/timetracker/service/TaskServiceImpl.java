package org.atique.timetracker.service;

import org.atique.Logger;
import org.atique.timetracker.model.Task;
import org.atique.timetracker.model.TaskStatus;
import org.atique.timetracker.util.FileUtil;

import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author atiQue
 * @since 25'Mar 2024 at 1:19 PM
 */

public class TaskServiceImpl implements TaskService {

    FileUtil fileUtil = new FileUtil();

    private Map<String, Task> TASK_MAP = new HashMap<>();

    private boolean initialRefresh = false;

    @Override
    public void init() throws IOException {
        fileUtil.truncateFile();
        Logger.log("Time Tracker file cleared");
    }

    @Override
    public Task start(Task task) throws IOException {

        ensure();

        Task old = TASK_MAP.get(task.getName());

        if (old != null) {
            throw new RuntimeException("Task already exists with name: " + old.getName() + "and status: " + old.getStatus());
        }

        TASK_MAP.put(task.getName(), task);

        fileUtil.saveTasksToFile(TASK_MAP);

        Logger.log("Task created:");
        Logger.log(task.toString());

        return task;
    }

    private void ensure() throws IOException {
        if (!initialRefresh) {
            TASK_MAP = fileUtil.getSavedTasksFromFIle();
            initialRefresh = true;
        }
    }

    @Override
    public void stop(String taskName) throws IOException {

        ensure();

        Task task = TASK_MAP.get(taskName);

        if (task == null) {
            throw new RuntimeException("No Task exists with name: " + taskName + "to stop");
        }

        task.setStatus(TaskStatus.COMPLETED);
        task.setEndTime(new Date());

        fileUtil.saveTasksToFile(TASK_MAP);

        Logger.log("Task stopped:");
        Logger.log(task.toString());
    }

    @Override
    public void reportTask() throws IOException {

        ensure();

        Logger.log("Task Report:");

        TASK_MAP.values()
                .stream()
                .forEach(v -> {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Name: ").append(v.getName()).append(" | ").append("Status: ").append(v.getStatus()).append(" | ");
                    sb.append("Duration: ").append(v.getDuration().getSeconds()).append(" sec");
                    Logger.log(sb.toString());
                });
    }

    @Override
    public void reportCategory() throws IOException {

        ensure();

        Map<String, Duration> catReport = new HashMap<>();
        TASK_MAP.values()
                .stream()
                .forEach(v -> {
                    Duration duration = catReport.getOrDefault(v.getCategory(), Duration.ZERO);
                    catReport.put(v.getCategory(), duration.plus(v.getDuration()));
                });

        Logger.log("Category Report:");
        for (Map.Entry<String, Duration> entry : catReport.entrySet()) {
            Logger.log("Name: " + entry.getKey() + "| Duration: " + entry.getValue().getSeconds() + " sec");
        }
    }
}
