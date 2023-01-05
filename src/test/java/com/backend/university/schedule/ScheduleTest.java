package com.backend.university.schedule;

import com.backend.university.domain.enumx.SubjectSchedule;
import org.junit.jupiter.api.Test;
import org.springframework.data.util.Pair;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.backend.university.common.utils.ScheduleUtils.toPairs;
import static com.backend.university.common.utils.ScheduleUtils.toSchedule;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ScheduleTest {

    @Test
    void should_Create_andConvert_Correctly() {
        List<Pair<DayOfWeek, SubjectSchedule>> pairs = new ArrayList<>();
        pairs.add(Pair.of(DayOfWeek.MONDAY, SubjectSchedule.AM_7H00_8H50));
        pairs.add(Pair.of(DayOfWeek.WEDNESDAY, SubjectSchedule.AM_7H00_8H50));
        Set<String> scheduleFromPairs = toSchedule(pairs);

        Set<String> schedule = new HashSet<>();
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
        Set<String> schedule = new HashSet<>();

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
