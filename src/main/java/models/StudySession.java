package models;

import java.time.LocalDateTime;

public class StudySession {
    private String taskId;
    private LocalDateTime start;
    private int minutes;
    public StudySession(String taskId, LocalDateTime start, int minutes){
        this.taskId=taskId; this.start=start; this.minutes=minutes;
    }
    public String getTaskId(){return taskId;}
    public LocalDateTime getStart(){return start;}
    public int getMinutes(){return minutes;}
}
