package ui;

import storage.JsonStorage;
import models.UserProfile;

import javax.swing.*;
import java.awt.*;

public class ProfileScreen extends JDialog {
    private final JsonStorage storage = new JsonStorage();
    public ProfileScreen(Frame owner, UserProfile profile){
        super(owner, "Profile", true);
        setSize(400,300);
        setLocationRelativeTo(owner);
        setLayout(new GridLayout(0,2));
        JTextField name = new JTextField(profile.getName());
        JTextField sem = new JTextField(profile.getSemester());
        JTextField hours = new JTextField(String.valueOf(profile.getPreferredStudyHoursPerDay()));
        add(new JLabel("Name")); add(name);
        add(new JLabel("Semester")); add(sem);
        add(new JLabel("Study hours/day")); add(hours);
        JButton save = new JButton("Save");
        add(save);
        save.addActionListener(e -> {
            profile.setName(name.getText().trim());
            profile.setSemester(sem.getText().trim());
            try { profile.setPreferredStudyHoursPerDay(Integer.parseInt(hours.getText().trim())); } catch (Exception ex){}
            try { storage.saveProfile(profile); dispose(); } catch (Exception ex){ JOptionPane.showMessageDialog(this,"Save failed"); }
        });
    }
}
