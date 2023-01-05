package com.backend.university.common.utils;

import com.backend.university.domain.enumx.SubjectSchedule;
import org.springframework.data.util.Pair;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ScheduleUtils {

    private ScheduleUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static Set<String> toSchedule(List<Pair<DayOfWeek, SubjectSchedule>> pairs) {
        Set<String> schedule = new HashSet<>();
        if (pairs.isEmpty()) {
            return schedule;
        }
        for (int i=1; i<=7; i++) {
            DayOfWeek day = DayOfWeek.of(i);
            Optional<Pair<DayOfWeek, SubjectSchedule>> pairOptional =
                    pairs.stream().filter(p -> p.getFirst().equals(day)).findFirst();
            pairOptional.ifPresent(pair -> schedule.add(day + ":" + pair.getSecond().getDescription()));
        }
        return schedule;
    }

    public static List<Pair<DayOfWeek, SubjectSchedule>> toPairs(Set<String> schedule) {
        List<Pair<DayOfWeek, SubjectSchedule>> pairs = new ArrayList<>();
        try {
            schedule.forEach(s -> {
                String dayString = s.split(":", 2)[0];
                String scheduleString = s.split(":", 2)[1];
                pairs.add(Pair.of(DayOfWeek.valueOf(dayString), SubjectSchedule.toSchedule(scheduleString)));
            });
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid set of schedules.");
        }
        return pairs;
    }

}
