package ui;

import logic.TaskScheduler;
import models.StudySession;
import models.UserProfile;
import models.Task;
import storage.JsonStorage;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.List;

public class ScheduleScreen extends JFrame {
    public ScheduleScreen(UserProfile profile, List<Task> tasks, LocalDateTime start) {
        setTitle("Schedule");
        setSize(600,400);
        setLocationRelativeTo(null);
        List<StudySession> plan = TaskScheduler.generateDailySchedule(profile, tasks, start);
        DefaultListModel<String> lm = new DefaultListModel<>();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm");
        for (StudySession s : plan) {
            String t = s.getStart().format(f) + " - " + s.getMinutes() + "m -> " + s.getTaskId();
            lm.addElement(t);
        }
        add(new JScrollPane(new JList<>(lm)), BorderLayout.CENTER);
    }
}
