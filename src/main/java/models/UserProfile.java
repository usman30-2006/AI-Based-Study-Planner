package models;

import java.util.List;

public class UserProfile {
    private String name = "";
    private String semester = "";
    private int preferredStudyHoursPerDay = 2;
    private int pomodoroMinutes = 25;
    private int shortBreak = 5;
    private int longBreak = 15;
    private int longBreakAfter = 4;
    private List<String> courses;

    public String getName(){return name;}
    public void setName(String n){name=n;}
    public String getSemester(){return semester;}
    public void setSemester(String s){semester=s;}
    public int getPreferredStudyHoursPerDay(){return preferredStudyHoursPerDay;}
    public void setPreferredStudyHoursPerDay(int h){preferredStudyHoursPerDay=h;}
    public int getPomodoroMinutes(){return pomodoroMinutes;}
    public void setPomodoroMinutes(int m){pomodoroMinutes=m;}
    public int getShortBreak(){return shortBreak;}
    public void setShortBreak(int b){shortBreak=b;}
    public int getLongBreak(){return longBreak;}
    public void setLongBreak(int b){longBreak=b;}
    public int getLongBreakAfter(){return longBreakAfter;}
    public void setLongBreakAfter(int n){longBreakAfter=n;}
    public List<String> getCourses(){return courses;}
    public void setCourses(List<String> c){courses=c;}
}
