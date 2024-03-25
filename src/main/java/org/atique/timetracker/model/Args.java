package org.atique.timetracker.model;

/**
 * @author atiQue
 * @since 25'Mar 2024 at 12:47 PM
 */

public class Args {
    private Commands command;
    private String taskName;
    private String categoryName;

    public Args() {
    }

    public Args(Commands command) {
        this.command = command;
    }

    public Args(Commands command, String taskName) {
        this.command = command;
        this.taskName = taskName;
    }

    public Args(Commands command, String taskName, String categoryName) {
        this.command = command;
        this.taskName = taskName;
        this.categoryName = categoryName;
    }

    public Commands getCommand() {
        return command;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCommand(Commands command) {
        this.command = command;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
