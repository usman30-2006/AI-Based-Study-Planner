package ui;

import storage.JsonStorage;
import models.UserProfile;
import models.Task;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

public class DashboardScreen extends JFrame {
    private final JsonStorage storage = new JsonStorage();
    private final UserProfile profile;
    public DashboardScreen(UserProfile profile){
        this.profile = profile;
        setTitle("StudyPlanner - Dashboard");
        setSize(800,600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JPanel top = new JPanel();
        top.add(new JLabel("Welcome, " + (profile.getName()==null?"":profile.getName())));
        add(top, BorderLayout.NORTH);

        JPanel buttons = new JPanel();
        JButton add = new JButton("Add Task");
        JButton schedule = new JButton("View Schedule");
        JButton profileBtn = new JButton("Profile");
        buttons.add(add); buttons.add(schedule); buttons.add(profileBtn);
        add(buttons, BorderLayout.SOUTH);

        DefaultListModel<String> lm = new DefaultListModel<>();
        JList<String> list = new JList<>(lm);
        add(new JScrollPane(list), BorderLayout.CENTER);
        refreshTasks(lm);

        add.addActionListener(e -> {
            TaskFormScreen f = new TaskFormScreen(this);
            f.setVisible(true);
            refreshTasks(lm);
        });

        schedule.addActionListener(e -> {
            List<Task> tasks = storage.loadTasks();
            ScheduleScreen s = new ScheduleScreen(profile, tasks, LocalDateTime.now().withHour(18).withMinute(0));
            s.setVisible(true);
        });

        profileBtn.addActionListener(e -> {
            ProfileScreen p = new ProfileScreen(this, profile);
            p.setVisible(true);
        });
    }

    private void refreshTasks(DefaultListModel<String> lm){
        lm.clear();
        List<Task> tasks = storage.loadTasks();
        for (Task t: tasks) lm.addElement(t.getTitle() + " [" + t.getSubject() + "]");
    }
}
