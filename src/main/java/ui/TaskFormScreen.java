package ui;

import storage.JsonStorage;
import models.Task;
import utils.Validator;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

public class TaskFormScreen extends JDialog {
    private final JsonStorage storage = new JsonStorage();
    public TaskFormScreen(Frame owner){
        super(owner, "Task", true);
        setSize(400,300);
        setLocationRelativeTo(owner);
        setLayout(new GridLayout(0,2));
        JTextField title = new JTextField();
        JTextField subject = new JTextField();
        JTextField minutes = new JTextField("60");
        JComboBox<Task.Priority> pr = new JComboBox<>(Task.Priority.values());
        add(new JLabel("Title")); add(title);
        add(new JLabel("Subject")); add(subject);
        add(new JLabel("Est minutes")); add(minutes);
        add(new JLabel("Priority")); add(pr);
        JButton save = new JButton("Save");
        add(save);
        save.addActionListener(e -> {
            Task t = new Task();
            t.setTitle(title.getText().trim());
            t.setSubject(subject.getText().trim());
            try { t.setEstimatedMinutes(Integer.parseInt(minutes.getText().trim())); } catch (Exception ex){ t.setEstimatedMinutes(60); }
            t.setPriority((Task.Priority) pr.getSelectedItem());
            t.setDeadline(LocalDateTime.now().plusDays(7));
            if (!Validator.isValidTask(t)){ JOptionPane.showMessageDialog(this,"Invalid"); return; }
            try {
                List<Task> tasks = storage.loadTasks();
                tasks.add(t);
                storage.saveTasks(tasks);
                dispose();
            } catch (Exception ex){ JOptionPane.showMessageDialog(this,"Save failed"); }
        });
    }
}
