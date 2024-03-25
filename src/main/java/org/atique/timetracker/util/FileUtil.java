package org.atique.timetracker.util;

import org.atique.timetracker.model.Task;
import org.atique.timetracker.model.TaskStatus;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author atiQue
 * @since 25'Mar 2024 at 1:53 PM
 */

public class FileUtil {

    private static final String FILE_PATH = "task-log.csv";

    public void truncateFile() throws IOException {
        Path path = Paths.get(FILE_PATH);

        if (Files.exists(path)) {
            Files.write(path, new byte[0]);
        }
    }

    public Map<String, Task> getSavedTasksFromFIle() throws IOException {
        Path path = Paths.get(FILE_PATH);

        if (Files.notExists(path)) {
            Files.createFile(path);
        }

        Map<String, Task> taskMap = Files.lines(path)
                .map(l -> l.split(","))
                .filter(tokens -> tokens.length == 5)
                .map(tokens -> new Task(
                        tokens[0],
                        tokens[1],
                        tokens[2] == null || tokens[2].isBlank() || "null" == tokens[2] ? null : new Date(Long.parseLong(tokens[2])),
                        tokens[3] == null || tokens[3].isBlank() || "null" == tokens[3] ? null : new Date(Long.parseLong(tokens[3])),
                        TaskStatus.valueOf(tokens[4])
                ))
                .collect(Collectors.toMap(t -> t.getName(), Function.identity()));

        return taskMap;
    }

    public void saveTasksToFile(Map<String, Task> taskMap) throws IOException {
        Path path = Paths.get(FILE_PATH);

        if (Files.notExists(path)) {
            Files.createFile(path);
        }

        List<String> lines = taskMap.values()
                .stream()
                .map(Task::toCsvFormat)
                .collect(Collectors.toList());

        Files.write(path, lines);
    }
}
