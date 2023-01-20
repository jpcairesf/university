package com.backend.university.utils;

import com.backend.university.subject.domain.enumx.SubjectSchedule;
import org.junit.jupiter.api.Test;
import org.springframework.data.util.Pair;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.backend.university.common.utils.ScheduleUtils.toPairs;
import static com.backend.university.common.utils.ScheduleUtils.toSchedule;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ScheduleUtilsTest {

    @Test
    void should_Create_andConvert_Correctly() {
        List<Pair<DayOfWeek, SubjectSchedule>> pairs = new ArrayList<>();
        pairs.add(Pair.of(DayOfWeek.MONDAY, SubjectSchedule.AM_7H00_8H50));
        pairs.add(Pair.of(DayOfWeek.WEDNESDAY, SubjectSchedule.AM_7H00_8H50));
        List<String> scheduleFromPairs = toSchedule(pairs);

        List<String> schedule = new ArrayList<>();
        schedule.add(DayOfWeek.MONDAY + ":" + SubjectSchedule.AM_7H00_8H50.getDescription());
        schedule.add(DayOfWeek.WEDNESDAY + ":" + SubjectSchedule.AM_7H00_8H50.getDescription());
        List<Pair<DayOfWeek, SubjectSchedule>> pairsFromSchedule = toPairs(schedule);

        pairs.sort(Comparator.comparing(Pair::getFirst));
        pairsFromSchedule.sort(Comparator.comparing(Pair::getFirst));

        assertEquals(schedule, scheduleFromPairs);
        assertEquals(pairs, pairsFromSchedule);
    }

    @Test
    void shouldNot_Convert_toSchedule() {
        List<String> schedule = new ArrayList<>();

        assert toPairs(schedule).isEmpty();

        schedule.add(DayOfWeek.MONDAY + SubjectSchedule.AM_7H00_8H50.getDescription());
        assertThrows(IllegalArgumentException.class,
                () -> toPairs(schedule));

        schedule.clear();
        schedule.add(DayOfWeek.WEDNESDAY + ";" + SubjectSchedule.AM_7H00_8H50.getDescription());
        assertThrows(IllegalArgumentException.class,
                () -> toPairs(schedule));

        schedule.clear();
        schedule.add("WENSDAY" + ":" + SubjectSchedule.AM_7H00_8H50.getDescription());
        assertThrows(IllegalArgumentException.class,
                () -> toPairs(schedule));

        schedule.clear();
        schedule.add(DayOfWeek.WEDNESDAY + ":" + "7h00-8h50-AM");
        assertThrows(IllegalArgumentException.class,
                () -> toPairs(schedule));
    }

}
