package com.oczeretko.rtmwidget;

public class RtmTask {
    private String title;
    private String due;
    private String priority;

    public RtmTask(String title, String due, String priority) {
        this.title = title;
        this.due = due;
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public String getDue() {
        return due;
    }

    public String getPriority() {
        return priority;
    }
}
