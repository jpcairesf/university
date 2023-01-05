package com.backend.university.utils;

import org.junit.jupiter.api.Test;

import static com.backend.university.common.utils.SemesterUtils.getDescription;
import static com.backend.university.common.utils.SemesterUtils.toSemester;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SemesterUtilsTest {

    @Test
    void should_Create_andConvert_Correctly() {
        int semester = 20231;
        String descriptionFromSemester = getDescription(semester);

        String description = "2023.1";
        int semesterFromDescription = toSemester(description);

        assertEquals(semester, semesterFromDescription);
        assertEquals(description, descriptionFromSemester);
    }

    @Test
    void shouldNot_Convert() {
        int semesterLessThen = 2031;
        assertThrows(IllegalArgumentException.class,
                () -> getDescription(semesterLessThen));

        int semesterMoreThen = 200231;
        assertThrows(IllegalArgumentException.class,
                () -> getDescription(semesterMoreThen));

        String description = "20231";
        assertThrows(IllegalArgumentException.class,
                () -> toSemester(description));
    }

}
