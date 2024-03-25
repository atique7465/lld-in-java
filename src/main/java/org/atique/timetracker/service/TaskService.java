package org.atique.timetracker.service;

import org.atique.timetracker.model.Task;

import java.io.IOException;

/**
 * @author atiQue
 * @since 25'Mar 2024 at 1:18 PM
 */

public interface TaskService {

    void init() throws IOException;
    Task start(Task task) throws IOException;

    void stop(String taskName) throws IOException;

    void reportTask() throws IOException;

    void reportCategory() throws IOException;
}
