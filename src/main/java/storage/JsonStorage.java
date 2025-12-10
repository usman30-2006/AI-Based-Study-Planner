package storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import models.Task;
import models.UserProfile;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JsonStorage {
    private final Gson gson;

    public JsonStorage() {
        gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).setPrettyPrinting().create();
    }

    public UserProfile loadProfile() {
        try {
            if (!Files.exists(Paths.get(FilePaths.PROFILE))) return new UserProfile();
            try (Reader r = new FileReader(FilePaths.PROFILE)) { return gson.fromJson(r, UserProfile.class); }
        } catch (Exception e) { return new UserProfile(); }
    }

    public void saveProfile(UserProfile p) throws IOException {
        Files.createDirectories(Paths.get(FilePaths.DATA_DIR));
        try (Writer w = new FileWriter(FilePaths.PROFILE)) { gson.toJson(p, w); }
    }

    public List<Task> loadTasks() {
        try {
            if (!Files.exists(Paths.get(FilePaths.TASKS))) return new ArrayList<>();
            try (Reader r = new FileReader(FilePaths.TASKS)) {
                Type t = new TypeToken<List<Task>>(){}.getType();
                return gson.fromJson(r, t);
            }
        } catch (Exception e) { return new ArrayList<>(); }
    }

    public void saveTasks(List<Task> tasks) throws IOException {
        Files.createDirectories(Paths.get(FilePaths.DATA_DIR));
        try (Writer w = new FileWriter(FilePaths.TASKS)) { gson.toJson(tasks, w); }
    }
}
