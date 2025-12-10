package logic;

import models.Task;
import models.StudySession;
import models.UserProfile;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class TaskScheduler {
    public static List<StudySession> generateDailySchedule(UserProfile profile, List<Task> tasks, LocalDateTime dayStart) {
        int available = profile.getPreferredStudyHoursPerDay() * 60;
        int pom = profile.getPomodoroMinutes();
        List<Task> pick = tasks.stream()
                .filter(t -> t.getDeadline() == null || !t.getDeadline().isBefore(dayStart))
                .sorted(Comparator.comparing((Task t) -> t.getPriority()).reversed()
                        .thenComparing(t -> t.getDeadline(), Comparator.nullsLast(Comparator.naturalOrder())))
                .collect(Collectors.toList());

        List<StudySession> plan = new ArrayList<>();
        LocalDateTime cur = dayStart;
        int pomCount = 0;
        for (Task t : pick) {
            int remain = t.getEstimatedMinutes();
            while (remain > 0 && available >= pom) {
                int use = Math.min(pom, remain);
                plan.add(new StudySession(t.getId(), cur, use));
                cur = cur.plusMinutes(use);
                available -= use;
                remain -= use;
                pomCount++;
                if (pomCount % profile.getLongBreakAfter() == 0) {
                    cur = cur.plusMinutes(profile.getLongBreak());
                    available -= profile.getLongBreak();
                } else {
                    cur = cur.plusMinutes(profile.getShortBreak());
                    available -= profile.getShortBreak();
                }
                if (available < pom) break;
            }
            if (available < pom) break;
        }
        return plan;
    }
}
