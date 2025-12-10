package models;

import java.time.LocalDateTime;
import java.util.UUID;

public class Task {
    public enum Priority { LOW, MEDIUM, HIGH }
    private String id = UUID.randomUUID().toString();
    private String title;
    private String subject;
    private String description;
    private LocalDateTime deadline;
    private Priority priority = Priority.MEDIUM;
    private int estimatedMinutes = 60;
    private boolean studyTask = true;

    public String getId(){return id;}
    public String getTitle(){return title;}
    public void setTitle(String t){title=t;}
    public String getSubject(){return subject;}
    public void setSubject(String s){subject=s;}
    public String getDescription(){return description;}
    public void setDescription(String d){description=d;}
    public LocalDateTime getDeadline(){return deadline;}
    public void setDeadline(LocalDateTime d){deadline=d;}
    public Priority getPriority(){return priority;}
    public void setPriority(Priority p){priority=p;}
    public int getEstimatedMinutes(){return estimatedMinutes;}
    public void setEstimatedMinutes(int m){estimatedMinutes=m;}
    public boolean isStudyTask(){return studyTask;}
    public void setStudyTask(boolean s){studyTask=s;}
}
