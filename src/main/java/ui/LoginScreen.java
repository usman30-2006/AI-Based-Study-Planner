package ui;

import storage.JsonStorage;
import models.UserProfile;

import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JFrame {
    private final JsonStorage storage = new JsonStorage();
    public LoginScreen(){
        setTitle("StudyPlanner - Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JPanel p = new JPanel(new GridLayout(3,1));
        JTextField name = new JTextField();
        JButton b = new JButton("Continue");
        p.add(new JLabel("Name:"));
        p.add(name);
        p.add(b);
        add(p, BorderLayout.CENTER);
        UserProfile profile = storage.loadProfile();
        name.setText(profile.getName());
        b.addActionListener(e -> {
            profile.setName(name.getText().trim());
            try { storage.saveProfile(profile); } catch (Exception ex) {}
            DashboardScreen d = new DashboardScreen(profile);
            d.setVisible(true);
            dispose();
        });
    }
}
