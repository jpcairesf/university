package com.backend.university.common.utils;

import com.backend.university.subject.domain.enumx.SubjectSchedule;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.util.Pair;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScheduleUtils {

    public static List<String> toSchedule(List<Pair<DayOfWeek, SubjectSchedule>> pairs) {
        List<String> schedule = new ArrayList<>();
        if (pairs.isEmpty()) {
            return schedule;
        }
        pairs.forEach(p -> schedule.add(p.getFirst() + ":" + p.getSecond().getDescription()));
        return schedule;
    }

    public static List<Pair<DayOfWeek, SubjectSchedule>> toPairs(List<String> schedule) {
        List<Pair<DayOfWeek, SubjectSchedule>> pairs = new ArrayList<>();
        try {
            schedule.forEach(s -> {
                String dayString = s.split(":", 2)[0];
                String scheduleString = s.split(":", 2)[1];
                pairs.add(Pair.of(DayOfWeek.valueOf(dayString), SubjectSchedule.toSchedule(scheduleString)));
            });
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid list of schedules.");
        }
        return pairs;
    }

}
