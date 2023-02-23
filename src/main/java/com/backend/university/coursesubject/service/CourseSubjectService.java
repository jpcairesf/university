package com.backend.university.coursesubject.service;

import com.backend.university.coursesubject.action.CourseSubjectCreateAction;
import com.backend.university.coursesubject.action.CourseSubjectDeleteAction;
import com.backend.university.coursesubject.action.CourseSubjectGetAction;
import com.backend.university.coursesubject.dto.CourseSubjectInputDTO;
import com.backend.university.coursesubject.dto.CourseSubjectOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseSubjectService {

    private final CourseSubjectGetAction getAction;

    private final CourseSubjectCreateAction createAction;

    private final CourseSubjectDeleteAction deleteAction;

    @Transactional(readOnly = true)
    public CourseSubjectOutputDTO findById(Long id) {
        return getAction.findById(id);
    }

    @Transactional(readOnly = true)
    public List<CourseSubjectOutputDTO> findAll() {
        return getAction.findAll();
    }

    @Transactional
    public CourseSubjectOutputDTO create(CourseSubjectInputDTO input) {
        return createAction.create(input);
    }

    @Transactional
    public void delete(Long id) {
        deleteAction.delete(id);
    }

}
