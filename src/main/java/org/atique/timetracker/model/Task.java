package org.atique.timetracker.model;

import java.time.Duration;
import java.util.Date;

/**
 * @author atiQue
 * @since 25'Mar 2024 at 1:08 PM
 */

public class Task {
    private String name;
    private String category;
    private Date startTime;
    private Date endTime;
    private TaskStatus status;

    public Task(String name, String category) {
        this.name = name;
        this.category = category;
        this.startTime = new Date();
        this.status = TaskStatus.IN_PROGRESS;
    }

    public Task(String name, String category, Date startTime, Date endTime, TaskStatus status) {
        this.name = name;
        this.category = category;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public Duration getDuration() {
        return Duration.between(this.startTime.toInstant(), this.getEndTime() == null ? new Date().toInstant() : this.getEndTime().toInstant());
    }

    public String toCsvFormat() {
        return new StringBuilder()
                .append(name).append(",")
                .append(category).append(",")
                .append(startTime.getTime()).append(",")
                .append(endTime != null ? endTime.getTime() : null).append(",")
                .append(status).toString();
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status=" + status +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
