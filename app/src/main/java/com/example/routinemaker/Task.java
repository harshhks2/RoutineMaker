package com.example.routinemaker;

public class Task {
    private String startTime;
    private String endTime;
    private String startPeriod;
    private String endPeriod;
    private String taskAtHand;
    private String description;



    public Task(String startTime, String endTime, String startPeriod, String endPeriod, String taskAtHand, String description) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;
        this.taskAtHand = taskAtHand;
        this.description = description;
    }

    public String getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(String startPeriod) {
        this.startPeriod = startPeriod;
    }

    public String getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(String endPeriod) {
        this.endPeriod = endPeriod;
    }



    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setTaskAtHand(String taskAtHand) {
        this.taskAtHand = taskAtHand;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getTaskAtHand() {
        return taskAtHand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Task{" +
                "startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", startPeriod='" + startPeriod + '\'' +
                ", endPeriod='" + endPeriod + '\'' +
                ", taskAtHand='" + taskAtHand + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
