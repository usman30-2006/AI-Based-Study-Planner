package utils;

import models.Task;

public class Validator {
    public static boolean isValidTask(Task t){
        if (t==null) return false;
        if (t.getTitle()==null || t.getTitle().trim().isEmpty()) return false;
        if (t.getEstimatedMinutes() <= 0) return false;
        return true;
    }
}
